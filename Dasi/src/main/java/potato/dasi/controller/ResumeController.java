package potato.dasi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import potato.dasi.dto.ResumeReqDTO;
import potato.dasi.service.ResumeService;

@RestController
@RequiredArgsConstructor
public class ResumeController {
	private final ResumeService resumeService;
	
	@PostMapping("/scan/resume")
	private ResponseEntity<?> scanResume(@RequestBody ResumeReqDTO resume){
		ResumeReqDTO result = resumeService.scanResume(resume);
		
		if(result == null)
			return ResponseEntity.badRequest().body("이력서를 스캔할 수 없습니다.");
		else
			return ResponseEntity.ok(result);
	}
	
}
