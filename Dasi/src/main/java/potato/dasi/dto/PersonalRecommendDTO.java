package potato.dasi.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonalRecommendDTO {
	private List<PersonalEduRecommendDTO> educationRecommend;
	private List<PersonalJobRecommendDTO> jobRecommend;
}
