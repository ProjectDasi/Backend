package potato.dasi.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import potato.dasi.domain.ApplyMethod;
import potato.dasi.domain.LearningProgram;

@Data
@Builder
public class LearningRecommendDTO {
	private Long id;
	private String title;
	private String organization;
	private String apply;
	private Date applicationStart;
	private Date applicationEnd;
	
	public static LearningRecommendDTO convertToDTO(LearningProgram learningProgram) {
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
	    
	    LearningRecommendDTO dto = LearningRecommendDTO.builder()
				.id(learningProgram.getId())
				.title(learningProgram.getTitle())
				.organization(learningProgram.getOrganization())
				.apply(apply.toString())
				.applicationStart(learningProgram.getApplicationStart())
				.applicationEnd(learningProgram.getApplicationEnd())
				.build();
		
		return dto;
	}
}
