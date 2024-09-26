package potato.dasi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EducationDTO {
	private String educationStart;
	private String educationEnd;
	private String school;
	private String major;
}
