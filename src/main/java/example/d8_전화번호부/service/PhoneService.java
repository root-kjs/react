package example.d8_전화번호부.service;

import example.d8_전화번호부.model.dto.PhoneDto;
import example.d8_전화번호부.model.mapper.PhoneMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhoneService {

    private final PhoneMapper phoneMapper;

    // 1. 등록
    public int addPhone( PhoneDto phoneDto ){
        return phoneMapper.addPhone( phoneDto );
    }

    // 2. 전체 조회
    public List<PhoneDto> printPhone(){
        return phoneMapper.printPhone();
    }

    // 3. 삭제
    public boolean deletePhone( int bno ){
        return phoneMapper.deletePhone( bno );
    }

}// clas end
