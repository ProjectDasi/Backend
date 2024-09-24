package potato.dasi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import potato.dasi.domain.Member;
import potato.dasi.dto.FindInfoDTO;
import potato.dasi.dto.MyProfileDTO;
import potato.dasi.dto.SignUpDTO;
import potato.dasi.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@PostMapping("/signUp")
	public ResponseEntity<?> signUp(@RequestBody SignUpDTO signUpInfo){
		Member result = memberService.signUp(signUpInfo);
		
		if(result == null)
			return ResponseEntity.badRequest().body("잘못된 요청입니다.");
		else
			return ResponseEntity.ok(true);
	}
	
	@GetMapping("/profile")
	public ResponseEntity<?> getProfile(@RequestParam String id){
		MyProfileDTO result = memberService.getProfile(id);
		
		if(result == null)
			return ResponseEntity.badRequest().body("잘못된 요청입니다.");
		else
			return ResponseEntity.ok(result);
	}
	
	@PostMapping("/profile/edit/{id}")
	public ResponseEntity<?> editProfile(@PathVariable String id, @RequestBody MyProfileDTO profile){
		Member result = memberService.editProfile(id, profile);
		
		if(result == null)
			return ResponseEntity.badRequest().body("잘못된 요청입니다.");
		else
			return ResponseEntity.ok(result);
	}
	
	@PostMapping("/find/id")
	public ResponseEntity<?> findId(@RequestBody FindInfoDTO findInfo){
		FindInfoDTO result = memberService.findId(findInfo);
		
		if(result == null)
			return ResponseEntity.badRequest().body("일치하는 전화번호가 없습니다.");
		else
			return ResponseEntity.ok(result);
	}
	
//	@PostMapping("/find/password")
//	public ResponseEntity<?> findPassword(@RequestBody FindInfoDTO findInfo){
//		FindInfoDTO result = memberService.findPassword(findInfo);
//		
//		if(result == null)
//			return ResponseEntity.badRequest().body("일치하는 정보가 없습니다.");
//		else
//			return ResponseEntity.ok(result);
//	}
}
