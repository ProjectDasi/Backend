package potato.dasi.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import lombok.RequiredArgsConstructor;
import potato.dasi.dto.PasswordChangeRequest;
import potato.dasi.dto.VerificationRequest;
import potato.dasi.service.MemberService;
import potato.dasi.service.VerifyJWTService;
import potato.dasi.service.VerifyService;

@Controller
@RequiredArgsConstructor
public class VerifyController {
	private final VerifyService verifyService;
	private final VerifyJWTService jwtService;
	
	@PostMapping("/find-password/send-code")
	public ResponseEntity<?> sendVerificationCode(@RequestBody VerificationRequest request) {
	    boolean result = verifyService.sendVerificationCode(request);
	    if (result) {
	        return ResponseEntity.ok("Verification code sent.");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
	    }
	}
	
	@PostMapping("/find-password/verify-code")
	public ResponseEntity<?> verifyCode(@RequestBody VerificationRequest request) {
		Boolean isValid = verifyService.verifyCode(request);
	    if (isValid != null) {
	        if (isValid) {
	        	// 인증 성공시 토큰 생성
	        	String token = jwtService.createToekn(request.getLoginId());
	            return ResponseEntity.ok()
	            		.header(HttpHeaders.AUTHORIZATION, token)
	            		.body("Code verified. Redirect to password reset page.");
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid verification code.");
	        }
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
	    }
	}
}
