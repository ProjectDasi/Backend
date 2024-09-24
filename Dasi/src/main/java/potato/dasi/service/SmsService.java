package potato.dasi.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import potato.dasi.persistence.MemberRepository;

@Service
@RequiredArgsConstructor
public class SmsService {

	@Value("${coolsms.api.key}")
	private String apiKey;

	@Value("${coolsms.api.secret}")
	private String apiSecret;

	@Value("${coolsms.api.number}")
	private String fromPhoneNumber;

	public void sendVerificationCode(String phone, String verificationCode) throws CoolsmsException {
		Message coolsms = new Message(apiKey, apiSecret); // 생성자를 통해 API 키와 API 시크릿 전달

		HashMap<String, String> params = new HashMap<>();
		params.put("to", phone); // 수신 전화번호
		params.put("from", fromPhoneNumber); // 발신 전화번호
		params.put("type", "sms");
		params.put("text", "인증번호는 [" + verificationCode + "] 입니다.");

		// 메시지 전송
		coolsms.send(params);
	}

}
