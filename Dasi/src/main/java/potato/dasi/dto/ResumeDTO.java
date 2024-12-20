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
public class ResumeDTO {
	private Long id;
	private String name;	
	private String address;
	private String phone;	
	private String email;	
	private String emergencyContact;
	private String emergencyRelationship;
	private String birthDate;
	private String updateDate;
	
//	private List<WorkExperienceDTO> workExperience;
//	private List<EducationDTO> education;
//	private List<CertificationDTO> certification;
//	private List<TrainingDTO> training;
	
	public static ResumeDTO convertToDTO(Resume resume) {
		String birth = DataConverter.convertDate(resume.getBirthDate());
		String update = DataConverter.convertDate(resume.getUpdateDate());
		
		ResumeDTO dto = ResumeDTO.builder()
				.id(resume.getId())
				.name(resume.getName())
				.address(resume.getAddress())
				.phone(resume.getPhone())
				.email(resume.getEmail())
				.emergencyContact(resume.getEmergencyContact())
				.emergencyRelationship(resume.getEmergencyRelationship())
				.birthDate(birth)
				.updateDate(update)
				.build();
		
		return dto;
	}
}
