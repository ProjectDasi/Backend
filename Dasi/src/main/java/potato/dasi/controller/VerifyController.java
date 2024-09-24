package potato.dasi.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import potato.dasi.dto.VerificationRequest;
import potato.dasi.service.VerifyService;

@Controller
@RequiredArgsConstructor
public class VerifyController {
	private final VerifyService verifyService;
	
	@PostMapping("/password-recovery/send-code")
	public ResponseEntity<?> sendVerificationCode(@RequestBody VerificationRequest request) {
	    boolean result = verifyService.sendVerificationCode(request);
	    if (result) {
	        return ResponseEntity.ok("Verification code sent.");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
	    }
	}
	
	@PostMapping("/password-recovery/verify-code")
	public ResponseEntity<?> verifyCode(@RequestBody VerificationRequest request) {
		Boolean isValid = verifyService.verifyCode(request);
	    if (isValid != null) {
	        if (isValid) {
	            return ResponseEntity.ok("Code verified. Redirect to password reset page.");
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid verification code.");
	        }
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
	    }
	}
}
