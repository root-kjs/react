package example.day12;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/axios")
//@CrossOrigin("http://localhost:5173")// CORS : cross origin 오류 해결 방안1

public class AxiosController {
    //1.
    @GetMapping
    public int println(){
        System.out.println("AxiosController axios");
        return 10;
    }


    //2. 로그인
    @PostMapping("/login")
    public boolean axios2(@RequestBody Map<String, String> map, HttpSession session){
        String id = map.get("id");
        session.setAttribute("loginId", id);// 로그인 세션의 세션의 속성 등록
        return true;
    }
    
    
    //3. 마이페이지 세션 유지
    @GetMapping("/info")
    public boolean axios3( HttpSession session){
        Object object = session.getAttribute("loginId");
        if( object == null ) return false; // 비로그인 중
        return true; // 로그인 중 
    }

    //4. 일반 폼 양식(리액트)
    @PostMapping("/form")
    public boolean axios4( @RequestParam Map<String, String> map){
        System.out.println("4. map = " + map);
        return true;
    }

    //5. 첨부파일 폼 양식(리액트)
    @PostMapping("/formdata") // 폼은  @RequestParam
    public boolean axios5( @RequestParam MultipartFile file ){
        System.out.println("5. file = " + file);
        return true;
    }


}//
