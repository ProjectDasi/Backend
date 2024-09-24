package potato.dasi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import potato.dasi.domain.Member;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindInfoDTO {
	private String phone;
	private String loginId;
	private Long memberId;
	private String password;
	
	public static FindInfoDTO convertToDTO(Member member) {
		FindInfoDTO dto = FindInfoDTO.builder()
				.loginId(member.getLoginId())
				.build();
		
		return dto;
	}
}
