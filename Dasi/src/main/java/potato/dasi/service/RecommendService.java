package potato.dasi.service;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import potato.dasi.domain.Certification;
import potato.dasi.domain.Education;
import potato.dasi.domain.LearningProgram;
import potato.dasi.domain.Member;
import potato.dasi.domain.Preference;
import potato.dasi.domain.Resume;
import potato.dasi.domain.Training;
import potato.dasi.domain.Work;
import potato.dasi.domain.WorkExperience;
import potato.dasi.dto.LearningDetailDTO;
import potato.dasi.dto.PersonalEduRecommendDTO;
import potato.dasi.dto.PersonalJobRecommendDTO;
import potato.dasi.dto.PersonalRecommendDTO;
import potato.dasi.dto.PersonalRecommendReqDTO;
import potato.dasi.dto.WorkDetailDTO;
import potato.dasi.persistence.CertificationRepository;
import potato.dasi.persistence.EducationRepository;
import potato.dasi.persistence.LearningProgramRepository;
import potato.dasi.persistence.MemberRepository;
import potato.dasi.persistence.PreferenceRepository;
import potato.dasi.persistence.ResumeRepository;
import potato.dasi.persistence.TrainingRepository;
import potato.dasi.persistence.WorkExperienceRepository;
import potato.dasi.persistence.WorkRepository;

@Service
@RequiredArgsConstructor
public class RecommendService {

	private final ResumeRepository resumeRepository;
	private final MemberRepository memberRepository;
	private final WorkExperienceRepository workExperienceRepository;
	private final CertificationRepository certificationRepository;
	private final TrainingRepository trainingRepository;
	private final EducationRepository educationRepository;
	private final PreferenceRepository preferenceRepository;
	private final LearningProgramRepository learningProgramRepository;
	private final WorkRepository workRepository;
	private final ObjectMapper objectMapper;
	private final RestTemplate restTemplate;

	public PersonalRecommendDTO getPersonalRecommend(String id) throws Exception {
		Long memId = Long.parseLong(id);

		Resume resume = resumeRepository.findByMemberId(memId).orElse(null);
		Member member = memberRepository.findById(memId).orElse(null);

		if (resume == null || member == null)
			return null;

		// WorkExperience 관련 문자열
		List<WorkExperience> workExpList = workExperienceRepository.findByResumeId(resume.getId());
		StringBuilder workExpStr = new StringBuilder();
		if (!workExpList.isEmpty()) {
			for (WorkExperience workExp : workExpList) {
				workExpStr.append(workExp.getWorkDescription()); // 원하는 필드를 getSpecificField로 가정
				workExpStr.append(", "); // 각 항목을 ,로 구분
			}
			// 마지막 , 제거
			workExpStr.setLength(workExpStr.length() - 2);
		}

		// Certification 관련 문자열
		List<Certification> certList = certificationRepository.findByResumeId(resume.getId());
		StringBuilder certStr = new StringBuilder();
		if (!certList.isEmpty()) {
			for (Certification cert : certList) {
				certStr.append(cert.getCertificationName());
				certStr.append(", ");
			}
			certStr.setLength(certStr.length() - 2);
		}

		// Training 관련 문자열
		List<Training> trainList = trainingRepository.findByResumeId(resume.getId());
		StringBuilder trainStr = new StringBuilder();
		if (!trainList.isEmpty()) {
			for (Training train : trainList) {
				trainStr.append(train.getTrainingName());
				trainStr.append(", ");
			}
			trainStr.setLength(trainStr.length() - 2);
		}

		// Education 관련 문자열
		List<Education> eduList = educationRepository.findByResumeId(resume.getId());
		StringBuilder eduStr = new StringBuilder();
		if (!eduList.isEmpty()) {
			for (Education edu : eduList) {
				eduStr.append(edu.getMajor());
				eduStr.append(", ");
			}
			eduStr.setLength(eduStr.length() - 2);
		}

		// PreferenceType 관련 문자열
		List<Preference> preferList = preferenceRepository.findAllByMemberId(member.getId());
		StringBuilder preferStr = new StringBuilder();
		System.out.println(preferList);
		if (!preferList.isEmpty()) {
//			for (Preference prefer : preferList) {
//				preferStr.append(prefer.getPreferenceType().getType());
//				preferStr.append(", ");
//			}
//			preferStr.setLength(preferStr.length() - 2);
			preferStr.append(preferList.get(0).getPreferenceType().getType());
			
		}

		PersonalRecommendReqDTO req = PersonalRecommendReqDTO.builder().address(resume.getAddress())
				.workDescription(workExpStr.toString()).certificationName(certStr.toString())
				.trainingName(trainStr.toString()).major(eduStr.toString()).preferenceType(preferStr.toString())
				.build();

		System.out.println(req);

		// JSON 문자열로 변환
		String jsonPayload = objectMapper.writeValueAsString(req);
		System.out.println(jsonPayload);

		// HTTP 헤더 설정
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// HTTP 엔터티 생성
		HttpEntity<String> requestEntity = new HttpEntity<>(jsonPayload, headers);

		// POST 요청 보내기
		ResponseEntity<String> response = restTemplate.exchange("http://172.21.37.241:5000/recommend", HttpMethod.POST,
				requestEntity, String.class);

		// 응답확인
		String res = response.getBody();
		System.out.println(res);

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(res);
		JsonNode itemsNodeEdu = root.path("education_recommendations");
		JsonNode itemsNodeJob = root.path("job_recommendations");
		
		List<PersonalEduRecommendDTO> recommendEduList = objectMapper.readValue(itemsNodeEdu.toString(), new TypeReference<List<PersonalEduRecommendDTO>>() {});
		List<PersonalJobRecommendDTO> recommendJobList = objectMapper.readValue(itemsNodeJob.toString(), new TypeReference<List<PersonalJobRecommendDTO>>() {});
		
		for(PersonalEduRecommendDTO edu : recommendEduList) {
			LearningProgram learning = learningProgramRepository.findById((long) edu.getId()).orElse(null);
			LearningDetailDTO dto = LearningDetailDTO.convertToDTO(learning);
			edu.setDetail(dto);
		}
		
		for(PersonalJobRecommendDTO job : recommendJobList) {
			Work work = workRepository.findById((long) job.getId()).orElse(null);
			WorkDetailDTO dto = WorkDetailDTO.convertToDTO(work);
			job.setDetail(dto);
		}
		// PersonalRecommendDTO 객체 생성
		PersonalRecommendDTO personalRecommendDTO = PersonalRecommendDTO.builder()
											    .educationRecommend(recommendEduList)  // 교육 추천 리스트 할당
											    .jobRecommend(recommendJobList)        // 직업 추천 리스트 할당
											    .build();

		return personalRecommendDTO;
	}

}
