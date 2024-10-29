package potato.dasi.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
import potato.dasi.domain.Member;
import potato.dasi.domain.Resume;
import potato.dasi.domain.Training;
import potato.dasi.domain.WorkExperience;
import potato.dasi.dto.CertificationDTO;
import potato.dasi.dto.EducationDTO;
import potato.dasi.dto.ResumeDTO;
import potato.dasi.dto.ResumeReqDTO;
import potato.dasi.dto.TrainingDTO;
import potato.dasi.dto.WorkExperienceDTO;
import potato.dasi.persistence.CertificationRepository;
import potato.dasi.persistence.EducationRepository;
import potato.dasi.persistence.MemberRepository;
import potato.dasi.persistence.PreferenceRepository;
import potato.dasi.persistence.ResumeRepository;
import potato.dasi.persistence.TrainingRepository;
import potato.dasi.persistence.WorkExperienceRepository;
import potato.dasi.util.DataConverter;

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

	public ResumeReqDTO scanResume(Long id, MultipartFile file) throws Exception {
		// 파일이 비어 있는지 확인
		if (file.isEmpty()) {
			return null;
		}

		// id 값 로그 출력
		System.out.println("수신한 ID 값: " + id);

		// 파일 이름 및 크기 로그 출력
		System.out.println("수신한 파일 이름: " + file.getOriginalFilename());
		System.out.println("수신한 파일 크기: " + file.getSize() + " bytes");
		
		Member member = memberRepository.findById(id).orElse(null);
		if(member == null)
			return null;
		
		Resume oldResume = resumeRepository.findByMemberId(id).orElse(null);
		if(oldResume != null)
			resumeRepository.delete(oldResume);

		// Flask 서버 URL 설정
		String flaskUrl = "http://172.21.42.253:5000/upload";
//        String flaskUrl = "http://localhost:5000/upload-file";

		// HTTP 요청 헤더 및 바디 준비
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		// MultipartBodyBuilder를 사용하여 파일 추가
		MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
		bodyBuilder.part("file", new ByteArrayResource(file.getBytes()) {
			@Override
			public String getFilename() {
				return file.getOriginalFilename(); // 원본 파일 이름 반환
			}
		}).header("Content-Disposition", "form-data; name=file; filename=" + file.getOriginalFilename());

		HttpEntity<?> requestEntity = new HttpEntity<>(bodyBuilder.build(), headers);

		// Flask 서버로 POST 요청 전송
		ResponseEntity<String> response = restTemplate.exchange(flaskUrl, HttpMethod.POST, requestEntity, String.class);

		// 응답확인
		// JSON 문자열을 JsonNode로 변환
		String res = response.getBody();

		System.out.println(res);

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(res);
		JsonNode itemsNodeResume = root.path("data").path("resume");
		JsonNode itemsNodeWorkExp = root.path("data").path("workExperience");
		JsonNode itemsNodeCertification = root.path("data").path("certification");
		JsonNode itemsNodeTraining = root.path("data").path("training");
		JsonNode itemsNodeEducation = root.path("data").path("education");

		Resume resume = objectMapper.readValue(itemsNodeResume.toString(), Resume.class);
		

		List<WorkExperience> workExpList = objectMapper.readValue(itemsNodeWorkExp.toString(),
				new TypeReference<List<WorkExperience>>() {
				});
		List<Certification> certList = objectMapper.readValue(itemsNodeCertification.toString(),
				new TypeReference<List<Certification>>() {
				});
		List<Training> trainList = objectMapper.readValue(itemsNodeTraining.toString(),
				new TypeReference<List<Training>>() {
		});
		List<Education> eduList = objectMapper.readValue(itemsNodeEducation.toString(),
				new TypeReference<List<Education>>() {
		});

		
		
		resume.setMember(member);
		
		// 우선 이력서 객체만 먼저 저장해서 ID 생성
		resume = resumeRepository.save(resume);

		// 이제 각 엔티티에 resume 설정
		if(!workExpList.isEmpty()) {
			for (WorkExperience workExperience : workExpList) {
			    workExperience.setResume(resume);
			}
		}
		

		if(!certList.isEmpty()) {			
			for (Certification cert : certList) {
				cert.setResume(resume);
			}
		}
		
		if(!trainList.isEmpty()) {			
			for (Training training : trainList) {
				training.setResume(resume);
			}
		}
		
		if(!eduList.isEmpty()) {			
			for (Education education : eduList) {
				education.setResume(resume);
			}
		}

		resume.setWorkExperienceList(workExpList);
		resume.setCertificationList(certList);
		resume.setTrainingList(trainList);
		resume.setEducationList(eduList);
		
		resume = resumeRepository.save(resume);
		System.out.println(resume);
		
		ResumeReqDTO resumeDTO = ResumeReqDTO.convertToDTO(resume);
		// 응답 본문 반환
		return resumeDTO;
	}

	// 파일 해시(MD5) 계산 메소드
	private String getFileHash(File file) throws IOException {
		try (FileInputStream fis = new FileInputStream(file)) {
			return DigestUtils.md5DigestAsHex(fis);
		}
	}
	

	public ResumeReqDTO getResume(Long id) {
		Resume resume = resumeRepository.findByMemberId(id).orElse(null);
		
		if(resume == null)
			return null;
		
		return ResumeReqDTO.convertToDTO(resume);
	}

	public ResumeReqDTO updateResume(Long id, ResumeReqDTO resumeDTO) {
	    // 1. 기존 Resume 엔티티 조회
	    Resume resume = resumeRepository.findById(resumeDTO.getResume().getId()).orElseThrow(() -> new RuntimeException("Resume not found"));
	    
	    // 2. WorkExperience 리스트 업데이트 (삭제 로직 포함)
	    List<WorkExperience> updatedWorkExperienceList = processWorkExperienceList(resume, resumeDTO.getWorkExperience());

	    // 3. Education 리스트 업데이트 (삭제 로직 포함)
	    List<Education> updatedEducationList = processEducationList(resume, resumeDTO.getEducation());

	    // 4. Certification 리스트 업데이트 (삭제 로직 포함)
	    List<Certification> updatedCertificationList = processCertificationList(resume, resumeDTO.getCertification());

	    // 5. Training 리스트 업데이트 (삭제 로직 포함)
	    List<Training> updatedTrainingList = processTrainingList(resume, resumeDTO.getTraining());

	    // 6. 업데이트된 리스트를 Resume에 설정
	    resume.setPhoto(resumeDTO.getResume().getPhoto());
	    resume.setName(resumeDTO.getResume().getName());
	    resume.setAddress(resumeDTO.getResume().getAddress());
	    resume.setPhone(resumeDTO.getResume().getPhone());
	    resume.setEmail(resumeDTO.getResume().getEmail());
	    resume.setEmergencyContact(resumeDTO.getResume().getEmergencyContact());
	    resume.setEmergencyRelationship(resumeDTO.getResume().getEmergencyRelationship());
	    resume.setBirthDate(DataConverter.convertStringToDate(resumeDTO.getResume().getBirthDate())); // String을 Date로 변환
	    resume.setUpdateDate(DataConverter.convertStringToDate(resumeDTO.getResume().getUpdateDate())); // String을 Date로 변환
	    resume.setWorkExperienceList(updatedWorkExperienceList);
	    resume.setEducationList(updatedEducationList);
	    resume.setCertificationList(updatedCertificationList);
	    resume.setTrainingList(updatedTrainingList);

	    // 7. Resume 저장 (cascade로 인해 관련 엔티티도 자동 저장됨)
	    Resume result = resumeRepository.save(resume);

	    return ResumeReqDTO.convertToDTO(result); // 저장된 후 Resume 엔티티를 DTO로 변환 후 반환 (필요시)
	}

	// WorkExperience 리스트 처리 메서드 (삭제 로직 포함)
	private List<WorkExperience> processWorkExperienceList(Resume resume, List<WorkExperienceDTO> workExperienceDTOs) {
	    List<WorkExperience> existingWorkExperience = resume.getWorkExperienceList(); // 기존 WorkExperience 리스트 조회
	    List<WorkExperience> updatedList = new ArrayList<>();
	    
	    // DTO로 받은 ID 목록을 추출
	    List<Long> dtoIds = workExperienceDTOs.stream()
	        .map(WorkExperienceDTO::getId)
	        .filter(Objects::nonNull) // ID가 없는 새 항목을 제외
	        .collect(Collectors.toList());


	    // 클라이언트에서 보내지 않은 기존 항목 삭제
	    List<WorkExperience> toDelete = existingWorkExperience.stream()
	        .filter(workExp -> !dtoIds.contains(workExp.getId())) // 클라이언트에서 보내지 않은 항목 필터링
	        .collect(Collectors.toList());

	    // 삭제된 항목 처리
	    if (!toDelete.isEmpty()) {
	        workExperienceRepository.deleteAll(toDelete); // 삭제
	        workExperienceRepository.flush(); // 즉시 데이터베이스에 반영
	    }


	    // DTO 리스트를 순회하면서 업데이트 또는 새 항목 추가
	    for (WorkExperienceDTO dto : workExperienceDTOs) {
	        WorkExperience workExperience;
	        if (dto.getId() != null) {
	            // 수정: ID가 있을 경우 기존 항목을 가져와서 업데이트
	            workExperience = workExperienceRepository.findById(dto.getId())
	                    .orElseThrow(() -> new RuntimeException("WorkExperience not found"));
	        } else {
	            // 새 항목: ID가 없을 경우 새 객체 생성
	            workExperience = new WorkExperience();
	            workExperience.setResume(resume); // 새 항목의 경우 Resume와 연관
	        }

	        workExperience.setWorkStart(DataConverter.convertStringToDate(dto.getWorkStart()));
	        workExperience.setWorkEnd(DataConverter.convertStringToDate(dto.getWorkEnd()));
	        workExperience.setCompany(dto.getCompany());
	        workExperience.setWorkDescription(dto.getWorkDescription());

	        updatedList.add(workExperience);
	    }
	    
	    return updatedList;
	}

	private List<Education> processEducationList(Resume resume, List<EducationDTO> educationDTOs) {
	    List<Education> existingEducation = resume.getEducationList();
	    List<Education> updatedList = new ArrayList<>();
	    
	    List<Long> dtoIds = educationDTOs.stream()
	        .map(EducationDTO::getId)
	        .filter(Objects::nonNull)
	        .collect(Collectors.toList());

	    // 클라이언트에서 보내지 않은 기존 항목 삭제
//	    List<Education> toDelete = existingEducation.stream()
//	        .filter(edu -> !dtoIds.contains(edu.getId())) // 클라이언트에서 보내지 않은 항목 필터링
//	        .collect(Collectors.toList());
//
//	    for(Education edu: toDelete) {
//	    	System.out.println(edu);
//	    }
	    
	    // 기존 항목 중 클라이언트에서 보내지 않은 항목 삭제
	    existingEducation.removeIf(edu -> !dtoIds.contains(edu.getId()));
	    
//	 // 삭제할 항목 출력 확인
//	    if (!toDelete.isEmpty()) {
//	        toDelete.forEach(edu -> System.out.println("삭제할 항목: " + edu));
//
//	        // 삭제 처리
//	        educationRepository.deleteAll(toDelete); // 삭제 처리
//	        educationRepository.flush(); // 데이터베이스에 즉시 반영
//	    }


	    // 업데이트 또는 새 항목 추가
	    for (EducationDTO dto : educationDTOs) {
	        Education education = (dto.getId() != null)
	            ? educationRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Education not found"))
	            : new Education();

	        education.setEducationStart(DataConverter.convertStringToDate(dto.getEducationStart()));
	        education.setEducationEnd(DataConverter.convertStringToDate(dto.getEducationEnd()));
	        education.setMajor(dto.getMajor());
	        education.setSchool(dto.getSchoolName());
	        education.setResume(resume); // 연관 설정

	        updatedList.add(education);
	    }

	    return updatedList;
	}

	private List<Certification> processCertificationList(Resume resume, List<CertificationDTO> certificationDTOs) {
	    List<Certification> existingCertification = resume.getCertificationList();
	    List<Certification> updatedList = new ArrayList<>();
	    
	    List<Long> dtoIds = certificationDTOs.stream()
	        .map(CertificationDTO::getId)
	        .filter(Objects::nonNull)
	        .collect(Collectors.toList());
	    
	    // 클라이언트에서 보내지 않은 기존 항목 삭제
	    List<Certification> toDelete = existingCertification.stream()
	        .filter(cert -> !dtoIds.contains(cert.getId())) // 클라이언트에서 보내지 않은 항목 필터링
	        .collect(Collectors.toList());

	    // 삭제된 항목 처리
	    if (!toDelete.isEmpty()) {
	        certificationRepository.deleteAll(toDelete); // 삭제
	        certificationRepository.flush(); // 즉시 데이터베이스에 반영
	    }

	    for (CertificationDTO dto : certificationDTOs) {
	        Certification certification = (dto.getId() != null)
	            ? certificationRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Certification not found"))
	            : new Certification();

	        certification.setAcquisitionDate(DataConverter.convertStringToDate(dto.getAcquisitionDate()));
	        certification.setCertificationName(dto.getCertificationName());
	        certification.setIssuingAuthority(dto.getIssuingAuthority());
	        certification.setResume(resume); // 연관 설정

	        updatedList.add(certification);
	    }

	    return updatedList;
	}

	private List<Training> processTrainingList(Resume resume, List<TrainingDTO> trainingDTOs) {
	    List<Training> existingTraining = resume.getTrainingList();
	    List<Training> updatedList = new ArrayList<>();
	    
	    List<Long> dtoIds = trainingDTOs.stream()
	        .map(TrainingDTO::getId)
	        .filter(Objects::nonNull)
	        .collect(Collectors.toList());

	    // 클라이언트에서 보내지 않은 기존 항목 삭제
	    List<Training> toDelete = existingTraining.stream()
	        .filter(train -> !dtoIds.contains(train.getId())) // 클라이언트에서 보내지 않은 항목 필터링
	        .collect(Collectors.toList());

	    // 삭제된 항목 처리
	    if (!toDelete.isEmpty()) {
	        trainingRepository.deleteAll(toDelete); // 삭제
	        trainingRepository.flush(); // 즉시 데이터베이스에 반영
	    }

	    for (TrainingDTO dto : trainingDTOs) {
	        Training training = (dto.getId() != null)
	            ? trainingRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Training not found"))
	            : new Training();

	        training.setTrainingStart(DataConverter.convertStringToDate(dto.getTrainingStart()));
	        training.setTrainingEnd(DataConverter.convertStringToDate(dto.getTrainingEnd()));
	        training.setTrainingName(dto.getTrainingName());
	        training.setTrainingInstitution(dto.getTrainingInstitution());
	        training.setResume(resume); // 연관 설정

	        updatedList.add(training);
	    }

	    return updatedList;
	}

	public boolean resumeIsExisted(Long id) {
		boolean isExisted = resumeRepository.existsByMemberId(id);
		
		return isExisted;
	}


}
