package potato.dasi.config.filter;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import potato.dasi.domain.Member;
import potato.dasi.persistence.MemberRepository;

@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private final MemberRepository memberRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String srcToken = request.getHeader("Authorization");			// 요청 헤더에서 Authorization을 얻어옴

		if(srcToken == null || !srcToken.startsWith("Bearer ")) {		// 없거나 "Bearer "로 시작하지 않으면
			filterChain.doFilter(request, response);					// 필터를 그냥 통과
			return;
		}
		String jwtToken = srcToken.replace("Bearer ", ""); 			// 토큰에서 "Bearer "를 제거
		// token이 만료된 경우 필터를 그냥 통과
		if(JWT.require(Algorithm.HMAC256("potato.dasi.jwt")).build().verify(jwtToken).getExpiresAt().before(new Date())) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().write("토큰 만료됨");
			return;
		}
		
		// 토큰에서 username 추출
		String username = JWT.require(Algorithm.HMAC256("potato.dasi.jwt")).build().verify(jwtToken).getClaim("username").asString();
		
		Optional<Member> opt = memberRepository.findByLoginId(username);
		if(!opt.isPresent()) {							// 사용자가 존재하지 않으면
			filterChain.doFilter(request, response);	// 필터를 그냥 통과
			return;
		}
		
		Member findMember = opt.get();
		
		// DB에서 읽은 사용자 정보를 이용해서 UserDetails 타입의 객체를 생성
		User user = new User(findMember.getLoginId(), findMember.getPassword(),
							AuthorityUtils.createAuthorityList(findMember.getRole().toString()));
		
		// Authentication 객체를 생성: 사용자명과 권한 관리를 위한 정보를 입력
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		// Security session에 등록
		SecurityContextHolder.getContext().setAuthentication(auth);
		filterChain.doFilter(request, response);
	}

}
