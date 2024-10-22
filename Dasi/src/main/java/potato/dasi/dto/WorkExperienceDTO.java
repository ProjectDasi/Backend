package potato.dasi.dto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import potato.dasi.domain.WorkExperience;
import potato.dasi.util.DataConverter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceDTO {
	private Long id;
	private String workStart;
	private String workEnd;
	private String company;
	private String workDescription;
	
	public static List<WorkExperienceDTO> convertToDTO(List<WorkExperience> workExpList) {
		if (workExpList == null || workExpList.isEmpty()) {
	        return Collections.emptyList(); // 빈 리스트 반환
	    }

	    // WorkExperience 리스트를 순회하면서 DTO로 변환
	    return workExpList.stream().map(workExp -> {
	        String start = DataConverter.convertDate(workExp.getWorkStart());
	        String end = DataConverter.convertDate(workExp.getWorkEnd());

	        return WorkExperienceDTO.builder()
	        		.id(workExp.getId())
	                .workStart(start)
	                .workEnd(end)
	                .company(workExp.getCompany())
	                .workDescription(workExp.getWorkDescription())
	                .build();
	    }).collect(Collectors.toList());
	}
}
