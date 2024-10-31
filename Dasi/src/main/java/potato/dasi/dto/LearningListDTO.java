package potato.dasi.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import potato.dasi.domain.LearningProgram;
import potato.dasi.util.DataConverter;

@Data
@Builder
public class LearningListDTO {
	private Long id;
	private String title;
	private String source;
	private String organization;
	private String applicationStart;
	private String applicationEnd;
	private String progressStart;
	private String progressEnd;
	
	public static LearningListDTO convertToDTO(LearningProgram learning) {
		String applicationStart = DataConverter.convertDateWithTime(learning.getApplicationStart());
		String applicationEnd = DataConverter.convertDateWithTime(learning.getApplicationEnd());
		String progressStart = DataConverter.convertDateWithTime(learning.getProgressStart());
		String progressEnd = DataConverter.convertDateWithTime(learning.getApplicationEnd());
		
		LearningListDTO dto = LearningListDTO.builder()
				.id(learning.getId())
				.title(learning.getTitle())
				.source(learning.getSource())
				.organization(learning.getOrganization())
				.applicationStart(applicationStart)
				.applicationEnd(applicationEnd)
				.progressStart(progressStart)
				.progressEnd(progressEnd)
				.build();
		
		return dto;
	}
	
	
}
