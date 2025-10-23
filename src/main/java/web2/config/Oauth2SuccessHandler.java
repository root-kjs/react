package web2.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import web2.service.JwtService;
import web2.service.UserService;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class Oauth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final UserService userService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        System.out.println( authentication ); // 인증정보(개인정보)
        // 타사 로그인 이후 로직 커스텀(
        // [3] 로그인 성공한 회원의 타사 발급한 토큰 확인
        // [3.1]Oauth2 라이브러리 : implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'
        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
        System.out.println("authToken = " + authToken);

        // 3-3 : 로그인 성공한 회원의 동의항목(정보) , Oauth2User
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal(); // Principal() 주체(로그인성공한정보)
        System.out.println("oauth2User = " + oauth2User);

        // [4] 공급자 회사 확인
        String provider = authToken.getAuthorizedClientRegistrationId();
        System.out.println("provider = " + provider);

        String uid = null; String name = null; // 동의 항목
        if( provider.equals("google")){
            uid = oauth2User.getAttribute("email");
            name = oauth2User.getAttribute("name");
        }else if( provider.equals("kakao")){  // 카카오
            // 카카오의 동의항목 profile_nickname
            Map<String, Object> kakaoAccount = oauth2User.getAttribute("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            uid = (String) profile.get("nickname"); // 가져오는 데이터가 닉네임밖에 없어서 임의로 다 닉네임 넣음
            name = (String) profile.get("nickname");

        }

        // 구글 테스트 url : http://localhost:8080/oauth2/authorization/google
        // [5] 자사의 로그인 방식 통합, 권한은 USER 로 통합( web2 : 토큰 /쿠키 발급)
        Cookie cookie = new Cookie("loginUser", jwtService.createToken(uid,"USER") );
        cookie.setHttpOnly( true ); cookie.setSecure(false);
        cookie.setPath("/"); cookie.setMaxAge(60*60);
        response.addCookie( cookie );

        // [6] 로그인 성공시 어디로 이동할지 (프론트엔드 루트)
        response.sendRedirect("http://localhost:5173"); // 리액트 서버
        response.sendRedirect("/"); // 자사 서버 메인 경로, localhost:8080


        // [7] oauth2 DB 저장
        userService.oauth2UserSignup( uid, name );


        // 1. 어느 타사의 로그인 성공인지 확인
        // 2. 로그인 성공한 동의항목(정보) 가져오기, 개인정보(아이디, 회원명, 주소, 전화번호)
        // 3. 자사서버와 타사서버의 통합 로그인( web2 : 토큰 /쿠키 발급)
        // 4. 자사 서버와 타사서버 통합 DB(최초 로그인이면 DB저장, 아니면 DB 처이 없음)




    }//func end
}// class end
