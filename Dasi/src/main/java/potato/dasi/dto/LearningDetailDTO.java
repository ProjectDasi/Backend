package potato.dasi.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import potato.dasi.domain.ApplyMethod;
import potato.dasi.domain.LearningProgram;

@Data
@Builder
public class LearningDetailDTO {
	private String organization;
	private String title;
	private Date applicationStart;
	private Date applicationEnd;
	private Date progressStart;
	private Date progressEnd;
	private String apply;
	private String situation;
	private String manager;
	private String phone;
	private String email;
	private String details;
	private String link;
	private String viewDetailsLink;
	
	public static LearningDetailDTO convertToDTO(LearningProgram learningProgram) {
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
				.applicationStart(learningProgram.getApplicationStart())
				.applicationEnd(learningProgram.getApplicationEnd())
				.progressStart(learningProgram.getProgressStart())
				.progressEnd(learningProgram.getProgressEnd())
				.apply(apply.toString())
				.situation(learningProgram.getSituation())
				.manager(learningProgram.getManager())
				.phone(learningProgram.getPhone())
				.email(learningProgram.getEmail())
				.details(learningProgram.getDetails())
				.link(learningProgram.getLink())
				.viewDetailsLink(learningProgram.getViewDetailsLink())
				.build();
		
		return dto;
	}
}
