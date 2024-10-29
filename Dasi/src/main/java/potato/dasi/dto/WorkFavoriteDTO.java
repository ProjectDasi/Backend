package potato.dasi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import potato.dasi.domain.Work;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkFavoriteDTO {
	private Long id;
	private String title;
	private String subtitle;
	private Long regionId;
	private String workCategory;
	private String preferenceType;
	private String certification;
	
	public static WorkFavoriteDTO convertToDTO(Work work) {
		WorkFavoriteDTO dto = WorkFavoriteDTO.builder()
				.id(work.getId())
				.title(work.getTitle())
				.subtitle(work.getSubtitle())
				.regionId(work.getRegion().getId())
				.workCategory(work.getWorkCategory())
				.preferenceType(work.getPreferenceType())
				.certification(work.getCertification())
				.build();
		
		return dto;
	}
}
