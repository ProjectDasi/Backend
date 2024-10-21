package potato.dasi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import potato.dasi.service.ResumeService;

@RestController
@RequiredArgsConstructor
public class ResumeController {
	private final ResumeService resumeService;
	
//	@PostMapping("/scan/resume/{id}")
//	private ResponseEntity<?> scanResume(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) throws Exception{
//		return ResponseEntity.ok(resumeService.scanResume(id, file));
//	}
	@PostMapping("/scan/resume/{id}")
	private ResponseEntity<?> scanResume(@PathVariable("id") Long id, @RequestBody String resumeData) throws Exception{
		return ResponseEntity.ok(resumeService.scanResume(id, resumeData));
	}
	
	
//	@PostMapping("/scan/resume/{id}")
//	private ResponseEntity<?> scanResume(@RequestBody ResumeReqDTO resume){
//		ResumeReqDTO result = resumeService.scanResume(resume);
//		
//		if(result == null)
//			return ResponseEntity.badRequest().body("이력서를 스캔할 수 없습니다.");
//		else
//			return ResponseEntity.ok(result);
//	}
	
	
	
}
