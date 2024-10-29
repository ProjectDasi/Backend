package potato.dasi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import potato.dasi.dto.ResumeReqDTO;
import potato.dasi.service.ResumeService;

@RestController
@RequiredArgsConstructor
public class ResumeController {
	private final ResumeService resumeService;
	
	@PostMapping("/scan/resume/{id}")
	private ResponseEntity<?> scanResume(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) throws Exception{
		ResumeReqDTO result = resumeService.scanResume(id, file);
		
		if(result == null)
			return ResponseEntity.badRequest().body("이력서 등록에 실패했습니다");
		else
			return ResponseEntity.ok(result);
	}
	
	@PostMapping("/update/resume/{id}")
	private ResponseEntity<?> scanResume(@PathVariable("id") Long id, @RequestBody ResumeReqDTO resume) throws Exception{
		ResumeReqDTO result = resumeService.updateResume(id, resume);
		
		if(result == null)
			return ResponseEntity.badRequest().body("이력서 등록에 실패했습니다");
		else
			return ResponseEntity.ok(result);
	}
	
	@GetMapping("/resume/{id}")
	private ResponseEntity<?> getResume(@PathVariable("id") Long id){
		ResumeReqDTO result = resumeService.getResume(id);
		
		if(result == null)
			return ResponseEntity.badRequest().body("이력서가 없습니다");
		else
			return ResponseEntity.ok(result);
	}
	
	@GetMapping("/resume/existed/{id}")
	private ResponseEntity<?> resumeIsExisted(@PathVariable("id") Long id){
		boolean result = resumeService.resumeIsExisted(id);
		
		return ResponseEntity.ok(result);
	}
}
