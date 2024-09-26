package potato.dasi.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonalRecommendDTO {
	private List<PersonalEduRecommendDTO> educationRecommend;
	private List<PersonalJobRecommendDTO> jobRecommend;
}
