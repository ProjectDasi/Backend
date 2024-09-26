package potato.dasi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceDTO {
	private String workStart;
	private String workEnd;
	private String company;
	private String workDescription;
}
