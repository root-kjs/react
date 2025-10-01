package example.day12;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration// 설정관련 빈등록
public class CrosConfig implements WebMvcConfigurer{
    // CORS 관련 매핑 설정 변경
    // 소켓이나 코스는 매핑이나

    @Override
    public void addCorsMappings(CorsRegistry registry) { // 서로다른 도메인간의 매핑 설정값
        //WebMvcConfigurer.super.addCorsMappings(registry);
//        registry.addMapping("/허용할 컨트롤러 URL") //     /** 모든 컨트롤러
//                .allowedOrigins("허용할 출처/도메인")        * 모든 출처, "http://localhost:5173", "http://localhost:5174"
//                .allowedMethods("허용할 http 메소드")       * 모든 메소드
//                 .allowCredentials(true);                    HTTP 인증(세션 유지) 허용/CORS  기준 세션/쿠키  허용

        registry.addMapping("/**")// 전체허용은 --->  별 2개  /** 해당 폴더 경로 뒤로 /** 적어줘야 그 이후의 경로가 정산 동작함.
                .allowedOrigins("http://localhost:5173", "http://localhost:5174")
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowCredentials(true)
                .allowedHeaders("*");
    }
}
