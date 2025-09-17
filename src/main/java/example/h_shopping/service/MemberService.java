package example.h_shopping.service;

import example.h_shopping.model.dao.MemberDao;
import example.h_shopping.model.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDao memberDao;

    // 1. 회원 등록
    public int signUp( MemberDto memberDto ){
        return memberDao.signUp( memberDto );
    }// f end

    // 2. 회원 전체 목록

    // 3. 회원 정보 수정

}//class end
