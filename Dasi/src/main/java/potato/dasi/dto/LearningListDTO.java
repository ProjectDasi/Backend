package potato.dasi.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import potato.dasi.domain.LearningProgram;

@Data
@Builder
public class LearningListDTO {
	private Long id;
	private String title;
	private String source;
	private String organization;
	private Date applicationStart;
	private Date applicationEnd;
	private Date progressStart;
	private Date progressEnd;
	
	public static LearningListDTO convertToDTO(LearningProgram learning) {
		LearningListDTO dto = LearningListDTO.builder()
				.id(learning.getId())
				.title(learning.getTitle())
				.source(learning.getSource())
				.organization(learning.getOrganization())
				.applicationStart(learning.getApplicationStart())
				.applicationEnd(learning.getApplicationEnd())
				.progressStart(learning.getProgressStart())
				.progressEnd(learning.getProgressEnd())
				.build();
		
		return dto;
	}
	
	
}
