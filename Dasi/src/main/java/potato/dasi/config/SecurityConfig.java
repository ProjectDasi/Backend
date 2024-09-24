package potato.dasi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import lombok.RequiredArgsConstructor;
import potato.dasi.config.filter.JWTAuthenticationFilter;
import potato.dasi.config.filter.JWTAuthorizationFilter;
import potato.dasi.handler.OAuth2SuccessHandler;
import potato.dasi.persistence.MemberRepository;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	@Autowired
	private MemberRepository memberRepository;

	private final OAuth2SuccessHandler successHandler;

//	@Bean
//	PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

	@Bean
	SecurityFilterChain sercurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(cf -> cf.disable()); // CSRF 보호 비활성화

		http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

//		http.formLogin(form->form.disable());	// Form을 이용한 로그인 사용 X

		http.httpBasic(basic -> basic.disable()); // Http Basic 인증 방식 사용 X

		// 세션을 유지하지 않겠다고 설정 -> url 호출 후 응답할때까지만 유지(응답 후 삭제)
		http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.oauth2Login(oauth2->oauth2.successHandler(successHandler));

		// 스프링 시큐리티가 등록한 필터체인의 뒤에 작성한 필터 추가
		http.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager(), memberRepository));

		// 스프링 시큐리티가 등록한 필터들 중에서 AuthorizationFilter 앞에 앞에서 작성한 필터를 삽입
		http.addFilterBefore(new JWTAuthorizationFilter(memberRepository), AuthorizationFilter.class);


		return http.build();
	}
}
