package potato.dasi.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
