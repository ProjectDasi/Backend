package potato.dasi.dto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import potato.dasi.domain.Training;
import potato.dasi.util.DataConverter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDTO {
	private Long id;
	private String trainingStart;
	private String trainingEnd;
	private String trainingName;
	private String trainingInstitution;
	
	public static List<TrainingDTO> convertToDTO(List<Training> trainList) {
		if (trainList == null || trainList.isEmpty()) {
	        return Collections.emptyList(); // 빈 리스트 반환
	    }

	    // certList 리스트를 순회하면서 DTO로 변환
	    return trainList.stream().map(train -> {
	    	String start = DataConverter.convertDate(train.getTrainingStart());
			String end = DataConverter.convertDate(train.getTrainingEnd());
			
			return TrainingDTO.builder()
					.id(train.getId())
					.trainingStart(start)
					.trainingEnd(end)
					.trainingName(train.getTrainingName())
					.trainingInstitution(train.getTrainingInstitution())
					.build();
	    }).collect(Collectors.toList());
	}
}
