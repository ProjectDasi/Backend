package potato.dasi.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import potato.dasi.domain.Resume;
import potato.dasi.util.DataConverter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeReqDTO {
	private ResumeDTO resume;
	private List<WorkExperienceDTO> workExperience;
	private List<CertificationDTO> certification;
	private List<TrainingDTO> training;
	private List<EducationDTO> education;
	
	public static ResumeReqDTO convertToDTO(Resume resume) {		
		ResumeReqDTO dto = ResumeReqDTO.builder()
				.resume(ResumeDTO.convertToDTO(resume))
				.workExperience(WorkExperienceDTO.convertToDTO(resume.getWorkExperienceList()))
				.education(EducationDTO.convertToDTO(resume.getEducationList()))
				.certification(CertificationDTO.convertToDTO(resume.getCertificationList()))
				.training(TrainingDTO.convertToDTO(resume.getTrainingList()))
				.build();
		
		return dto;
	}
}
