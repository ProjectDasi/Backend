package potato.dasi.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import potato.dasi.domain.Member;
import potato.dasi.domain.PreferenceType;
import potato.dasi.util.DataConverter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyProfileDTO {
	private String name;
	private String password;
	private String phone;
	private String region;
	private String loginId;
	private List<PreferenceType> preference;
	private Date birth;
	
	public static MyProfileDTO converToDTO(Member member) {
		List<PreferenceType> preType = member.getPreferenceList().stream()
                .map(pre -> pre.getPreferenceType())
                .collect(Collectors.toList());
		
		String phoneNum = DataConverter.formatPhoneNumber(member.getPhone());
		
		MyProfileDTO dto = MyProfileDTO.builder()
				.name(member.getName())
				.phone(phoneNum)
				.region(member.getRegion().getSubregion())
				.loginId(member.getLoginId())
				.preference(preType)
				.birth(member.getBirth())
				.build();
		
		return dto;
	}
}
