package web2.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web2.model.dto.UserDto;
import web2.service.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //1. 회원가입
    @PostMapping("/signup") // {  "uid": "qwe", "upwd": "qwe", "uname": "유재석",  "uphone": "010-2222-3333",  "urole": "USER" }
    public ResponseEntity<?> signup(@RequestBody UserDto userDto){
        System.out.println("userDto = " + userDto);
        int result = userService.signup( userDto );
        System.out.println("result = " + result);
        return ResponseEntity.ok( result );
    }//func end

    // 2.1. 로그인( + 세션 : 자바웹서버(톰캣)의 임시 저장소 , 한번 로그인 성공했다는 증거 )
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody UserDto userDto , HttpSession session ){
//        UserDto result = userService.login( userDto );
//        if( result != null ){ // 만약에 로그인 성공 했다면 성공한 유저의 아이디를 세션에 저장
//            session.setAttribute( "loginUser" , result.getUid() );
//        }
//        return ResponseEntity.ok( result );
//    }//func end

    // 2.2. 로그인( + 쿠키 : 클라이언트 브라우저의 임시 저장소, 세션: 서버/ 쿠키 : 클라이언트 )
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto , HttpServletResponse response ){
        UserDto result = userService.login( userDto );
        if( result != null ){ // 만약에 로그인 성공 했다면 성공한 유저의 아이디를 쿠키에 저장
            // 로그인 정보를 서버에 저장 하면 서버이므로 안전하다. 쿠키(클라이언트)에 저장하면 보안이 취약하다.
            //
            // response.addCookies( 생성한 쿠키 );
            Cookie cookie = new Cookie("loginUser", result.getUid()); // 주로 사용자들의 설정값/임시 저장소
            // 클라이언트에서 해당 쿠키를 노출(탈취) 방지 = 주로 민감한 정보
            cookie.setHttpOnly( true );// 무조건 http에서만 사용. 즉 js로 접근 불가능
            cookie.setSecure( false ); // http( false ) 탈취하더라도 암호화, 단, https(true)에서만 사용 가능. 이 이상은 토큰은 사용하여 보안 강화(이것은 나중에) jwt(json web tokken)
            //
            cookie.setPath("/");// 쿠키에 접근할 수 있는 경로
            cookie.setMaxAge( 60 * 60 ); // 쿠키의 유효기간(초) , 1시간

            response.addCookie( cookie ); // 생성한 쿠키를 클라이언트에게 반환한다.

        }
        return ResponseEntity.ok( result );
    }//func end

    // 3. 내 정보 조회
    @GetMapping("/myinfo")
    public ResponseEntity<?> myInfo( HttpServletRequest request ){
        // 1. 현재 클라이언트(브라우저) 저장된 모든 쿠키 정보 가져오기
        Cookie[] cookies = request.getCookies();
        if ( cookies != null ){
            for ( Cookie c : cookies){
                if ( c.getName().equals("loginUser")){
                    String uid = c.getValue(); // 쿠키의 저장된 값 반환
                    //
                    UserDto result = userService.myInfo( uid );
                    return ResponseEntity.ok( result );
                }
            }// for end
        }
        return ResponseEntity.ok( null ); // 비로그인 상태
    }

    // 로그아웃
    @GetMapping("/logout")
    public ResponseEntity<?> logout( HttpServletResponse response ){
        Cookie cookie = new Cookie( "loginUser" , null );
        cookie.setHttpOnly( true );
        cookie.setSecure( false );
        cookie.setPath("/");
        cookie.setMaxAge( 0 );
        response.addCookie( cookie );// 기존 정보랑 똑같이 덮어쓰기 --> 삭제
        return ResponseEntity.ok( true );

    }


}// class end
