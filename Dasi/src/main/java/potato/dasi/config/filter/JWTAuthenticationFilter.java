package potato.dasi.config.filter;

import java.io.IOException;
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import potato.dasi.domain.Member;
import potato.dasi.dto.MemberDTO;
import potato.dasi.persistence.MemberRepository;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	// 인증 객체
	private final AuthenticationManager authenticationManager;
	private final MemberRepository memberRepository;

	// POST/login 요청이 왔을 때 인증을 시도하는 메소드
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Member member = mapper.readValue(request.getInputStream(), Member.class);
			// Security에게 자격 증명 요청에 필요한 객체 생성
			Authentication authToken = new UsernamePasswordAuthenticationToken(member.getLoginId(), member.getPassword());
			
			// OAuth
//			String username = request.getParameter("loginId"); // 폼 데이터를 통해 전달된 loginId를 받음
//			String password = request.getParameter("password"); // 폼 데이터를 통해 전달된 password를 받음
//			Authentication authToken = new UsernamePasswordAuthenticationToken(username,password);

			// 인증 진행 -> UserDetailsService의 loadUserByUsernaem에서 DB로부터 사용자 정보를 읽어온 뒤
			// 사용자 입력 정보와 비교하여 자격 증명에 성공하면 Authentication객체를 생성하여 return
			Authentication auth = authenticationManager.authenticate(authToken);
			System.out.println("auth: " + auth);
			return auth;
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		return null;
	}

	// 인증이 성공했을 때 실행되는 후처리 메소드
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// 자격증명이 성공하면 loadUserByUsername에서 만든 객체가 authResult에 담겨져 있음
		User user = (User) authResult.getPrincipal();
		
		// username으로 JWT를 생성해서 Response Header - Authorization에 담아 돌려줌
		// 필요에 따라 추가 정보를 담을 수 있음
		String token = JWT.create().withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.withClaim("username", user.getUsername()).sign(Algorithm.HMAC256("potato.dasi.jwt"));
		System.out.println("TOken:" + token);
		response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		
		Member member = memberRepository.findByLoginId(user.getUsername()).get();
		MemberDTO memberDTO = MemberDTO.builder()
				.id(member.getId().toString())
				.build();
		
		// 회원고유 ID정보를 JSON 형식으로 추가
		ObjectMapper objectMapper = new ObjectMapper();
		String responseBody = objectMapper.writeValueAsString(memberDTO);
		
		// 응답 본문에 JSON 데이터 전송
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(responseBody);
        
	    // JWT 토큰을 JSON 형태로 응답 (OAuth?)
//	    response.setContentType("application/json");
//	    response.setCharacterEncoding("UTF-8");
//	    response.getWriter().write("{\"token\": \"" + token + "\"}");
		response.setStatus(HttpStatus.OK.value());
	}
}
