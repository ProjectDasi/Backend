package potato.dasi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonalRecommendReqDTO {
	private Long regionId;
	private String workDescription;
	private String certificationName;
	private String trainingName;
	private String major;
	private String preferenceType;
	@JsonProperty("isDisabled")
	private boolean isDisabled;
	private Long computerSkillLevel;
	private FavoritesDTO favorites;
}
