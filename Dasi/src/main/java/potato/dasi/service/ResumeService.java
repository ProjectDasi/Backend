package potato.dasi.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import potato.dasi.domain.Certification;
import potato.dasi.domain.Education;
import potato.dasi.domain.LearningProgram;
import potato.dasi.domain.Resume;
import potato.dasi.domain.Training;
import potato.dasi.domain.Work;
import potato.dasi.domain.WorkExperience;
import potato.dasi.dto.LearningDetailDTO;
import potato.dasi.dto.PersonalEduRecommendDTO;
import potato.dasi.dto.PersonalJobRecommendDTO;
import potato.dasi.dto.PersonalRecommendDTO;
import potato.dasi.dto.WorkDetailDTO;
import potato.dasi.persistence.CertificationRepository;
import potato.dasi.persistence.EducationRepository;
import potato.dasi.persistence.MemberRepository;
import potato.dasi.persistence.PreferenceRepository;
import potato.dasi.persistence.ResumeRepository;
import potato.dasi.persistence.TrainingRepository;
import potato.dasi.persistence.WorkExperienceRepository;

@Service
@RequiredArgsConstructor
public class ResumeService {
	private final ResumeRepository resumeRepository;
	private final MemberRepository memberRepository;
	private final WorkExperienceRepository workExperienceRepository;
	private final CertificationRepository certificationRepository;
	private final TrainingRepository trainingRepository;
	private final EducationRepository educationRepository;
	private final PreferenceRepository preferenceRepository;
	private final RestTemplate restTemplate;

//	public String scanResume(Long id, MultipartFile file) throws Exception {
	public String scanResume(Long id, String res) throws Exception {
//		// 파일이 비어 있는지 확인
//		if (file.isEmpty()) {
//			return "파일이 비어 있습니다.";
//		}

		// id 값 로그 출력
		System.out.println("수신한 ID 값: " + id);

		// 파일 이름 및 크기 로그 출력
//		System.out.println("수신한 파일 이름: " + file.getOriginalFilename());
//		System.out.println("수신한 파일 크기: " + file.getSize() + " bytes");

		// Flask 서버 URL 설정
//		String flaskUrl = "http://172.21.50.55:5000/upload";
//        String flaskUrl = "http://localhost:5000/upload-file";

		// HTTP 요청 헤더 및 바디 준비
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//
//		// MultipartBodyBuilder를 사용하여 파일 추가
//		MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
//		bodyBuilder.part("file", new ByteArrayResource(file.getBytes()) {
//			@Override
//			public String getFilename() {
//				return file.getOriginalFilename(); // 원본 파일 이름 반환
//			}
//		}).header("Content-Disposition", "form-data; name=file; filename=" + file.getOriginalFilename());
//
//		HttpEntity<?> requestEntity = new HttpEntity<>(bodyBuilder.build(), headers);
//
//		// Flask 서버로 POST 요청 전송
//		ResponseEntity<String> response = restTemplate.exchange(flaskUrl, HttpMethod.POST, requestEntity, String.class);

		// 응답확인
		// JSON 문자열을 JsonNode로 변환
//		String res = response.getBody();

		System.out.println(res);

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(res);
		JsonNode itemsNodeResume = root.path("resume");
		JsonNode itemsNodeWorkExp = root.path("work_experience");
		JsonNode itemsNodeCertification = root.path("certification");
		JsonNode itemsNodeTraining = root.path("training");
		JsonNode itemsNodeEducation = root.path("education");

		Resume resume = objectMapper.readValue(itemsNodeResume.toString(), Resume.class);

		List<WorkExperience> recommendWorkExpList = objectMapper.readValue(itemsNodeWorkExp.toString(),
				new TypeReference<List<WorkExperience>>() {
				});
		List<Certification> recommendCertList = objectMapper.readValue(itemsNodeCertification.toString(),
				new TypeReference<List<Certification>>() {
				});
		List<Training> recommendTrainList = objectMapper.readValue(itemsNodeTraining.toString(),
				new TypeReference<List<Training>>() {
		});
		List<Education> recommendEduList = objectMapper.readValue(itemsNodeEducation.toString(),
				new TypeReference<List<Education>>() {
		});

		
		resume.setWorkExperienceList(recommendWorkExpList);
		resume.setCertificationList(recommendCertList);
		resume.setTrainingList(recommendTrainList);
		resume.setEducationList(recommendEduList);
		
		System.out.println(resume);
		
		// 응답 본문 반환
		return res;
	}

	// 파일 해시(MD5) 계산 메소드
	private String getFileHash(File file) throws IOException {
		try (FileInputStream fis = new FileInputStream(file)) {
			return DigestUtils.md5DigestAsHex(fis);
		}
	}

//	public ResumeReqDTO scanResume(ResumeReqDTO resumeDTO) {
//		// 파일 준비
//		File file = new File(resumeDTO.getFilePath());
//		if (!file.exists()) {
//			throw new IllegalArgumentException("파일을 찾을 수 없습니다.");
//		}
//		
//		
//		// Flask 서버 URL 설정
//		String flaskUrl = "http://localhost:5000/upload-file";
//		
//		// HTTP 요청 헤더 및 바디 준비
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//		
//		// MultipartBodyBuilder를 사용하여 파일 추가
//		MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
//		bodyBuilder.part("file", new FileSystemResource(file));
//		
//		HttpEntity<?> requestEntity = new HttpEntity<>(bodyBuilder.build(), headers);
//		
//		// Flask 서버로 POST 요청 전송
//		ResponseEntity<String> response = restTemplate.exchange(flaskUrl, HttpMethod.POST, requestEntity, String.class);
//		
////        return response.getBody();
//		return resumeDTO;
//	}
}
