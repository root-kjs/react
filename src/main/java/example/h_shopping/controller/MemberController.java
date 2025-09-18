package example.h_shopping.controller;

import example.h_shopping.model.dto.MemberDto;
import example.h_shopping.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 1. 회원 등록
    @PostMapping("/signup")
    public int signUp( @RequestBody MemberDto memberDto ){
        System.out.println("memberDto = " + memberDto);
        return memberService.signUp( memberDto );
    }// f end
//    {
//      "custno": 100007,
//      "custname": "김행복",
//      "phone": "010-1234-5678",
//      "address": "서울시 강남구 청담동",
//      "grade": "A",
//      "city": "01"
//    }

    // 2. 회원 전체 목록
    @GetMapping("/list")
    public List<MemberDto> memberList(){
        return memberService.memberList();
    }// f e

    // 3. 회원 정보 수정
    @PutMapping("/update")
    public boolean updateMember( @RequestBody MemberDto memberDto ){
        return memberService.updateMember( memberDto );
    }

    // 4. 특정한 필드/열/컬럼 의 값 중복/존재 확인
    @GetMapping("/check")
    public boolean check( @RequestParam String type , String data ) {
        return memberService.check( type , data );
    }

}// class end