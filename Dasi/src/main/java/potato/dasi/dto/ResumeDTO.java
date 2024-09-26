package potato.dasi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDTO {
	private String photo;
	private String name;	
	private String address;
	private String phone;	
	private String email;	
	private String emergencyContact;
	private String emergencyRelationship;
	
	private String birthDate;
	private String updateDate;
}
