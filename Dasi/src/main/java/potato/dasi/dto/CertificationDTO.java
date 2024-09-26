package potato.dasi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CertificationDTO {
	private String acquisitionDate;		// 취득 날짜
	private String certificationName;	// 자격 이름
	private String issuingAuthority;
}
