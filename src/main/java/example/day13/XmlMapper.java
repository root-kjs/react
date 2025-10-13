package example.day13;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface XmlMapper {
    // 1. 등록
    // 방법1 : 추상 메소드 위에 @Insert("sql") 작성
    // 방법2 : 추상메소드를 매핑하는 방법(XML) 복잡한 SQL 권장
    // @Insert("insert into student(name, kor, math ) values(#{name}, #{kor}, #{math} )")
    // @Options( useGeneratedKeys = true, keyProperty = "sno") // 생성된 pk을 sno 필드에 매핑
    int save( StudentDto studentDto );

    // 2. 전체 조회
    List<StudentDto> findAll();
    
    // 3. 개별 조회
    StudentDto find(int sno );

    // 4. 개별 삭제
    int delete( int sno ); // 여기서 int 타입이 가르키는 것은 완료된 레코드의 수를 지칭함.

    // 5. 개별 수정
    int update( StudentDto studentDto );

}// i end
