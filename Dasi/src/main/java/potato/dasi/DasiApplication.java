package potato.dasi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@SpringBootApplication
public class DasiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DasiApplication.class, args);
	}

//	@Bean
//	PageableHandlerMethodArgumentResolverCustomizer customize() {
//		return p -> {
//			p.setOneIndexedParameters(true);	// 1부터 시작
//		};
//	}
}
