package potato.dasi.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import potato.dasi.domain.Work;
import potato.dasi.util.DataConverter;

@Data
@Builder
public class WorkDetailDTO {
	private String company;
	private String title;
	private String workHours;	
	private String workType;	
	private String workCategory;
	private String salary;
	private String regionName;
	private Date signupDate;
	private String dueDate;	// Date type으로 변경 예정
	private String career;	
	private String education;
	private String certification;
	private String contact;
	private String email;
	private String details;
	private String link;
	
	
	public static WorkDetailDTO convertToDTO(Work work) {
		String dueDate = DataConverter.convertDate(work.getDueDate());
		
		WorkDetailDTO dto = WorkDetailDTO.builder()
				.company(work.getCompany())
				.title(work.getTitle())
				.workHours(work.getWorkHours())
				.workType(work.getWorkType())
				.workCategory(work.getWorkCategory())
				.salary(work.getSalary())
				.regionName(work.getRegionName())
				.signupDate(work.getSignupDate())
				.dueDate(dueDate)
				.career(work.getCareer())
				.education(work.getEducation())
				.certification(work.getCertification())
				.contact(work.getContact())
				.email(work.getEmail())
				.details(work.getDetails())
				.link(work.getLink())
				.build();
		
		return dto;
	}
}
