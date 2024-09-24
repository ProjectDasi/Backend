package potato.dasi.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecommendReqDTO {
	private Long regionId;
	private List<String> preference;
}
