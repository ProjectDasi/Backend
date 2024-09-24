package potato.dasi.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import potato.dasi.domain.Member;
import potato.dasi.dto.VerificationRequest;
import potato.dasi.persistence.MemberRepository;

@Service
@RequiredArgsConstructor
public class VerifyService {
	private final SmsService smsService;
	private final MemberRepository memberRepository;
	private final CodeStorageService codeStorage;

	public boolean sendVerificationCode(VerificationRequest request) {
		System.out.println("Id:" + request);
		Optional<Member> user = memberRepository.findByLoginId(request.getLoginId());
		System.out.println(user.get());
		if (user.isPresent()) {
			String verificationCode = generateVerificationCode(); // 인증번호 생성
			
			try {
				// 인증번호 전송
				smsService.sendVerificationCode(user.get().getPhone(), verificationCode);
			} catch (CoolsmsException e) {
				e.printStackTrace();
			} 
			
			System.out.println(verificationCode);
			// 인증번호 저장 (예: 메모리 캐시, DB 등)
			// 인증번호와 사용자 전화번호를 저장, 일정 시간 후 만료
		    codeStorage.saveCode(user.get().getPhone(), verificationCode);
			return true;
		} else {
			return false;
		}
	}

	public Boolean verifyCode(VerificationRequest request) {
	    Optional<Member> user = memberRepository.findByLoginId(request.getLoginId());
	    if (user.isPresent()) {
	    	// 코드 인증
	        boolean isValid = codeStorage.verifyCode(user.get().getPhone(), request.getVerificationCode());
	        if (isValid) {
	            return true;
	        } else {
	            return false;
	        }
	    } else {
	        return null;
	    }
	}

	public String generateVerificationCode() {
		return String.valueOf((int) ((Math.random() * 899999) + 100000)); // 6자리 인증번호 생성
	}

}
