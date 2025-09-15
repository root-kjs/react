package example.day06;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper // 해당 인터페이스를 스프링 컨테이너 등록, xxxDAO 역할 대신 한다.
public interface BatisMapper {
    // 1. 학생 등록

    // 2. 전체 학생 조회
    @Select("select * from student")
    List< StudentDto > findAll();

    // 3. 개별 학생 조회

    // 4. 개별 학생 삭제

    // 5. 개별 학생 수정
}
