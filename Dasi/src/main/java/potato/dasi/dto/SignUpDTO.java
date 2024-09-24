package potato.dasi.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {
	private String name;
	private String phone;
	private String region;
	private String loginId;
	private String password;
	private Date birth;
	private String[] preferenceId;
}
