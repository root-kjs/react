package example.h_shopping.controller;

import example.h_shopping.model.dto.MemberDto;
import example.h_shopping.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 1. 회원 등록
    @PostMapping("/signUp")
    public int signUp( @RequestBody MemberDto memberDto ){
        System.out.println("memberDto = " + memberDto);
        return memberService.signUp( memberDto );
    }// f end
    //{
    //  "custno": 100007,
    //  "custname": "김행복",
    //  "phone": "010-1234-5678",
    //  "address": "서울시 강남구 청담동",
    //  "grade": "A",
    //  "city": "01"
    //}

    // 2. 회원 전체 목록

    // 3. 회원 정보 수정

}// class end
