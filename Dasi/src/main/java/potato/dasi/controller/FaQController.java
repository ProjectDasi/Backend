package potato.dasi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import potato.dasi.domain.Faq;
import potato.dasi.service.FaQService;

@Controller
@RequiredArgsConstructor
public class FaQController {
	private final FaQService faqService;
	
	@GetMapping("/faq")
	public ResponseEntity<?> getAllFaQ(){
		List<Faq> result = faqService.getAllFaQ();
		
		if(result == null)
			return ResponseEntity.badRequest().body("FaQ가 없습니다.");
		else
			return ResponseEntity.ok(result);
	}
	
}
