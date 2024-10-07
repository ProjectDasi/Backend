package potato.dasi.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class VerifyJWTService {
	private static final long ACCESS_TOKEN_MSEC = 5 * (60 * 1000); // 10분
	private static final String JWT_KEY = "edu.pnu.jwtkey";
	private static final String claimName = "loginId";
	private static final String prefix = "Bearer ";

	// 인코딩을 위한 secret key
	// 토큰에 담을 정보의 key값
	// JWT 토큰 헤더 문자열
	private String getJWTSource(String token) {
		if (token.startsWith(prefix))
			return token.replace(prefix, "");
		return token;
	}

	public String createToekn(String loginId) {
		String src = JWT.create()
				.withClaim(claimName, loginId)
				.withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_MSEC))
				.sign(Algorithm.HMAC256(JWT_KEY));
		return prefix + src;
	}

	public String getClaim(String token) {
		String tok = getJWTSource(token);
		// 토큰에 담긴 정보 중 key가 “loginId”인 데이터 가져오기
		return JWT.require(Algorithm.HMAC256(JWT_KEY)).build().verify(tok).getClaim(claimName).asString();
	}
	
	public boolean isTokenValid(String token) {
		if (token == null || token.isEmpty()) {
			System.out.println("토큰에");
		    return false; // Or throw an exception
		}
		
		String tok = getJWTSource(token);
		return JWT.require(Algorithm.HMAC256(JWT_KEY)).build().verify(tok).getExpiresAt().before(new Date());
	}
}
