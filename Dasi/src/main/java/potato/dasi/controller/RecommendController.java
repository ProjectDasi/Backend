package potato.dasi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import potato.dasi.dto.PersonalRecommendDTO;
import potato.dasi.service.RecommendService;

@Controller
@RequiredArgsConstructor
public class RecommendController {
	private final RecommendService recommendService;
	
	@GetMapping("/personal/recommend")
	private ResponseEntity<?> getPersonalRecommend(@RequestParam String id) throws Exception{
		PersonalRecommendDTO result = recommendService.getPersonalRecommend(id);
		
		if(result == null)
			return ResponseEntity.badRequest().body("이력서가 정보가 없습니다.");
		else
			return ResponseEntity.ok(result);
	}
}
