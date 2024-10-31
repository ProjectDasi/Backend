package potato.dasi.dto;

import lombok.Builder;
import lombok.Data;
import potato.dasi.domain.ApplyMethod;
import potato.dasi.domain.LearningProgram;
import potato.dasi.util.DataConverter;

@Data
@Builder
public class LearningDetailDTO {
	private String organization;
	private String title;
	private String applicationStart;
	private String applicationEnd;
	private String progressStart;
	private String progressEnd;
	private String apply;
	private String situation;
	private String manager;
	private String phone;
	private String email;
	private String details;
	private String link;
	private Long views;
	
	public static LearningDetailDTO convertToDTO(LearningProgram learningProgram) {
		String applicationStart = DataConverter.convertDateWithTime(learningProgram.getApplicationStart());
		String applicationEnd = DataConverter.convertDateWithTime(learningProgram.getApplicationEnd());
		String progressStart = DataConverter.convertDateWithTime(learningProgram.getProgressStart());
		String progressEnd = DataConverter.convertDateWithTime(learningProgram.getApplicationEnd());
		String situation = DataConverter.convertToOther(learningProgram.getSituation(), "-");
		String manager = DataConverter.convertToOther(learningProgram.getManager(), "-");
		String phone = DataConverter.convertToOther(learningProgram.getPhone(), "-");
		String email = DataConverter.convertToOther(learningProgram.getEmail(), "-");
		
	    StringBuilder apply = new StringBuilder();
	    int size = learningProgram.getApplyMethod().size();
	    int count = 0;
	    
	    for (ApplyMethod applyMethod : learningProgram.getApplyMethod()) {
	        apply.append(applyMethod.getMethod().toString());
	        count++;
	        if (count < size) {
	            apply.append(", ");
	        }
	    }
	    
		LearningDetailDTO dto = LearningDetailDTO.builder()
				.organization(learningProgram.getOrganization())
				.title(learningProgram.getTitle())
				.applicationStart(applicationStart)
				.applicationEnd(applicationEnd)
				.progressStart(progressStart)
				.progressEnd(progressEnd)
				.apply(apply.toString())
				.situation(situation)
				.manager(manager)
				.phone(phone)
				.email(email)
				.details(learningProgram.getDetails())
				.link(learningProgram.getLink())
				.views(learningProgram.getViews())
				.build();
		
		return dto;
	}
}
