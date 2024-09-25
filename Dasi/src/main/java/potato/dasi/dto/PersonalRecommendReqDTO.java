package potato.dasi.dto;

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
	private String address;
	private String workDescription;
	private String certificationName;
	private String trainingName;
	private String major;
	private String preferenceType;
}
