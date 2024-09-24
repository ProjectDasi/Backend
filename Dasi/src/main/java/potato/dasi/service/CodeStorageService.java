package potato.dasi.service;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeStorageService {
    private final RedisTemplate<String, String> redisTemplate;

    private static final long EXPIRATION_TIME = 5 * 60; // 5분 후 만료

    public void saveCode(String phoneNumber, String code) {
        redisTemplate.opsForValue().set(phoneNumber, code, EXPIRATION_TIME, TimeUnit.SECONDS);
    }

    public boolean verifyCode(String phoneNumber, String inputCode) {
        String savedCode = redisTemplate.opsForValue().get(phoneNumber);
        
        if(savedCode != null && savedCode.equals(inputCode)) {
        	redisTemplate.delete(phoneNumber);
        	return true;
        }
        else
        	return false;
    }

}
