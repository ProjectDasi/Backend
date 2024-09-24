package potato.dasi.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import potato.dasi.persistence.MemberRepository;

@Service
@RequiredArgsConstructor
public class SmsService {
	private final MemberRepository memberRepository;

	public void sendVerificationCode(String phone, String verificationCode) {
		// TODO Auto-generated method stub
		
	}
	


}
