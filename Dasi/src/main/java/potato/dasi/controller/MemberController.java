package potato.dasi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import potato.dasi.domain.Member;
import potato.dasi.dto.FindInfoDTO;
import potato.dasi.dto.MyProfileDTO;
import potato.dasi.dto.PasswordChangeRequest;
import potato.dasi.dto.SignUpDTO;
import potato.dasi.service.MemberService;
import potato.dasi.service.VerifyJWTService;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	private final VerifyJWTService jwtService;
	
	@PostMapping("/signUp")
	public ResponseEntity<?> signUp(@RequestBody SignUpDTO signUpInfo){
		Member result = memberService.signUp(signUpInfo);
		
		System.out.println(signUpInfo);
		
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
	
	@PostMapping("/find/password")
	public ResponseEntity<?> changePassword(@RequestHeader("Authorization") String token, @RequestBody PasswordChangeRequest request) {
		System.out.println(token);
	    // 토큰 유효성 검사
	    if (!jwtService.isTokenValid(token)) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token.");
	    }

	    // 토큰에서 사용자 정보 추출
	    String loginId = jwtService.getClaim(token);

	    // 새 비밀번호로 변경
	    boolean result = memberService.changePassword(loginId, request.getNewPassword());
	    
	    if(result)
	    	return ResponseEntity.ok("Password changed successfully.");
	    else
	    	return ResponseEntity.badRequest().body("Fail to change password");
	}
}
