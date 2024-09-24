package potato.dasi.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import potato.dasi.domain.Member;
import potato.dasi.domain.Role;
import potato.dasi.persistence.MemberRepository;
import potato.dasi.util.CustomMyUtil;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private final PasswordEncoder encoder;
	private final MemberRepository memRepo;
	
//	// SecurityConfig에 직접 의존하지 않고 필요한 컴포넌트를 주입받습니다.
//    public OAuth2SuccessHandler(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("OAuth2SuccessHandler:onAuthenticationSuccess");
		
		OAuth2User user = (OAuth2User) authentication.getPrincipal();

		// 임의의 사용자를 만들어서 서버에 저장
		String username = CustomMyUtil.getUsernameFromOAuth2User(user);
		if (username == null) {
			log.error("onAuthenticationSuccess:Cannot generate username from oauth2user!");
			throw new ServletException("Cannot generate username from oauth2user!");
		}
		log.info("onAuthenticationSuccess:" + username);
//		memRepo.save(Member.builder().loginId(username).password(encoder.encode("1a2s3d4f")).role(Role.ROLE_MEMBER)
//				.build());

	    // DB에서 회원 조회 또는 신규 회원 생성
//	    Member member = memRepo.findByLoginId(username)
//	                .orElse(createNewMember(username));
//		String jwtToken = JWTUtil.getJWT(username);
//		response.addHeader(HttpHeaders.AUTHORIZATION, jwtToken);
	}
	


//	       
//
//	        // JWT 토큰 생성
//	        String token = jwtUtil.generateToken(member);
//
//	        // 응답 헤더에 JWT 토큰 추가
//	        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
//
//	        // 리다이렉션 설정
//	        getRedirectStrategy().sendRedirect(request, response, "/success");
//	    }

	    private Member createNewMember(OAuth2User oauthUser) {
	        return memRepo.save(Member.builder()
	                .loginId(oauthUser.getAttribute("email"))
	                .name(oauthUser.getAttribute("name"))
	                .role(Role.ROLE_MEMBER)
	                .build());
	    }
}