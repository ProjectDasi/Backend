package potato.dasi.dto;

import lombok.Builder;
import lombok.Data;
import potato.dasi.domain.Work;
import potato.dasi.util.DataConverter;

@Data
@Builder
public class WorkRecommendDTO {
	private Long id;
	private String region;
	private String title;
	private String company;
	private String workCategory;
	private String dueDate;
	
	public static WorkRecommendDTO convertToDTO(Work work) {
		String dueDate = DataConverter.convertDate(work.getDueDate());
		
		WorkRecommendDTO dto = WorkRecommendDTO.builder()
				.id(work.getId())
				.title(work.getTitle())
				.company(work.getCompany())
				.region("부산 " + work.getRegion().getSubregion())
				.workCategory(work.getWorkCategory())
				.dueDate(dueDate)
				.build();
		
		return dto;
	}
}
