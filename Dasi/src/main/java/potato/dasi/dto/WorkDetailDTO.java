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
	private String signupDate;
	private String dueDate;	// Date type으로 변경 예정
	private String career;	
	private String education;
	private String certification;
	private String contact;
	private String email;
	private String details;
	private String link;
	private String preferredQualifications;
	
	
	public static WorkDetailDTO convertToDTO(Work work) {
		String dueDate = DataConverter.convertDate(work.getDueDate());
		String signupDate = DataConverter.convertDate(work.getSignupDate());
		String workType = DataConverter.convertToOther(work.getWorkType(), null);
		String workHours = DataConverter.convertToOther(work.getWorkHours(), null);
		String workCategory = DataConverter.convertToOther(work.getWorkCategory(), "-");
		String certification = DataConverter.convertToOther(work.getCertification(), "-");
		String salary = DataConverter.convertToOther(work.getSalary(), "협의");
		String career = DataConverter.convertToOther(work.getCareer(), "무관");
		String education = DataConverter.convertToOther(work.getEducation(), "무관");
		String email = DataConverter.convertToOther(work.getEmail(), "-");
		String contact = DataConverter.containsPhoneNumber(work.getContact()) ? work.getContact() : "-";
		String preferredQualifications = DataConverter.convertToOther(work.getPreferredQualifications(), "-");
		
		WorkDetailDTO dto = WorkDetailDTO.builder()
				.company(work.getCompany())
				.title(work.getTitle())
				.workHours(workHours)
				.workType(workType)
				.workCategory(workCategory)
				.salary(salary)
				.regionName(work.getRegionName())
				.signupDate(signupDate)
				.dueDate(dueDate)
				.career(career)
				.education(education)
				.certification(certification)
				.contact(contact)
				.email(email)
				.details(work.getDetails())
				.link(work.getLink())
				.preferredQualifications(preferredQualifications)
				.build();
		
		return dto;
	}
}
