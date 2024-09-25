package potato.dasi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonalJobRecommendDTO {
	private int id;
	private double similarity;
	private String tag;
}
