package potato.dasi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDTO {
	private String trainingStart;
	private String trainingEnd;
	private String trainingName;
	private String trainingInstitution;
}
