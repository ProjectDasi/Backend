package potato.dasi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalEduRecommendDTO {
	private int id;
	private double similarity;
	private String tag;
	private LearningDetailDTO detail;
}
