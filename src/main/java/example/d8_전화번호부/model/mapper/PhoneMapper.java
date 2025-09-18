package example.d8_전화번호부.model.mapper;

import example.d8_전화번호부.model.dto.PhoneDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper// 마이바티스 어노테이션
public interface PhoneMapper {

    // 1. 등록
    @Insert("insert into phone_book( pName, pPhone, pAge ) values ( #{pName}, #{pPhone}, #{pAge} )")
    public int addPhone( PhoneDto phoneDto );

    // 2. 전체 조회
    @Select("select * from phone_book")
    public List<PhoneDto> printPhone();
    
    // 3. 삭제
    @Delete("delete from phone_book where bno = #{ bno }")
    public boolean deletePhone( int bno );

}//interface end
