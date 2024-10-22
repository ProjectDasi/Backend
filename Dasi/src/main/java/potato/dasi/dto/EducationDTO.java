package potato.dasi.dto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import potato.dasi.domain.Education;
import potato.dasi.util.DataConverter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EducationDTO {
	private Long id;
	private String educationStart;
	private String educationEnd;
	private String schoolName;
	private String major;
	
	public static List<EducationDTO> convertToDTO(List<Education> eduList) {
		if (eduList == null || eduList.isEmpty()) {
	        return Collections.emptyList(); // 빈 리스트 반환
	    }

	    // eduList 리스트를 순회하면서 DTO로 변환
	    return eduList.stream().map(edu -> {
	        String start = DataConverter.convertDate(edu.getEducationStart());
	        String end = DataConverter.convertDate(edu.getEducationEnd());

	        return EducationDTO.builder()
	        		.id(edu.getId())
	                .educationStart(start)
	                .educationEnd(end)
	                .schoolName(edu.getSchool())
	                .major(edu.getMajor())
	                .build();
	    }).collect(Collectors.toList());
	}
}
