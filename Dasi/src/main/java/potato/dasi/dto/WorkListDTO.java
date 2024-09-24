package potato.dasi.dto;

import lombok.Builder;
import lombok.Data;
import potato.dasi.domain.Work;
import potato.dasi.util.DataConverter;

@Data
@Builder
public class WorkListDTO {
	private Long id;
	private String title;
	private String regionName;
	private String salary;
	private String dueDate;
//	private int index;
	
	public static WorkListDTO convertToDTO(Work work) {
		String dueDate = DataConverter.convertDate(work.getDueDate());
		
		WorkListDTO dto = WorkListDTO.builder()
				.id(work.getId())
				.title(work.getTitle())
				.regionName(work.getRegionName())
				.salary(work.getSalary())
				.dueDate(dueDate)
				.build();
		
		return dto;
	} 
}
