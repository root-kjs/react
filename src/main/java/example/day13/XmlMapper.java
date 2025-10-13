package example.day13;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface XmlMapper {
    // 1. 등록
    // 방법1 : 추상 메소드 위에 @Insert("sql") 작성
    // 방법2 : 추상메소드를 매핑하는 방법(XML) 복잡한 SQL 권장
    int save( StudentDto studentDto );

    // 2. 전체 조회
    List<StudentDto> findAll();

}// i end
