package potato.dasi.dto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import potato.dasi.domain.Certification;
import potato.dasi.util.DataConverter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CertificationDTO {
	private Long id;
	private String acquisitionDate;		// 취득 날짜
	private String certificationName;	// 자격 이름
	private String issuingAuthority;
	
	public static List<CertificationDTO> convertToDTO(List<Certification> certList) {
		if (certList == null || certList.isEmpty()) {
	        return Collections.emptyList(); // 빈 리스트 반환
	    }

	    // certList 리스트를 순회하면서 DTO로 변환
	    return certList.stream().map(cert -> {
	    	String acquistion = DataConverter.convertDate(cert.getAcquisitionDate());
			
			return CertificationDTO.builder()
					.id(cert.getId())
					.acquisitionDate(acquistion)
					.certificationName(cert.getCertificationName())
					.issuingAuthority(cert.getIssuingAuthority())
					.build();
	    }).collect(Collectors.toList());
	}
}
