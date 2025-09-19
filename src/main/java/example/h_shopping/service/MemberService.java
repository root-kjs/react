package example.h_shopping.service;

import example.h_shopping.model.dao.MemberDao;
import example.h_shopping.model.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDao memberDao;

    // 1. 회원 등록
    public int signUp( MemberDto memberDto ){
        return memberDao.signUp( memberDto );
    }// f end

    // 2. 회원 전체 목록
    public List<MemberDto> memberList(){
        return memberDao.memberList();
    }

    // 3. 회원 정보 수정
    public boolean updateMember( MemberDto memberDto ){
        return memberDao.updateMember( memberDto );
    }

    // 4. 특정한 필드/열/컬럼 의 값 중복/존재 확인
    public boolean check( String type , String data ) {
        return memberDao.check( type , data );
    }

}//class end