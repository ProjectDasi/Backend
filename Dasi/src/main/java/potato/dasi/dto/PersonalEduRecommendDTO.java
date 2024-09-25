package potato.dasi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonalEduRecommendDTO {
	private int id;
	private double similarity;
	private String tag;
}
