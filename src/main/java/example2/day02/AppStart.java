package example2.day02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaAuditing // 감사/감시(BaseTime 모니터링 필수), 데이터베이스 모니터링 작동
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }

        @Bean // 특정 경로 전체를 Spring Security 검사에서 제외,  특정한 경로는 시큐리티 무시.
        public WebSecurityCustomizer webSecurityCustomizer() {
            return (web) -> web.ignoring().requestMatchers("/api/goods/**");
        }
}
