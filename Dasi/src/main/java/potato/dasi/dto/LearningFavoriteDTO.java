package potato.dasi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import potato.dasi.domain.LearningProgram;
import potato.dasi.domain.Work;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LearningFavoriteDTO {
	private Long id;
	private String title;
	private String organization;
	private Long regionId;
	private String preferenceType;
	
	public static LearningFavoriteDTO convertToDTO(LearningProgram learning) {
		LearningFavoriteDTO dto = LearningFavoriteDTO.builder()
				.id(learning.getId())
				.title(learning.getTitle())
				.regionId(learning.getRegion().getId())
//				.preferenceType(learning.getPreferenceType())
//				.certification(learning.getCertification())
				.build();
		
		return dto;
	}
}
