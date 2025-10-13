package example.day13;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

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

    //============== ★★★★ 동적 쿼리 : if(동적 쿼리), forEach 등등 사용가능 ★★★ ==========

    /** 6. 특정 국어점수 보다 이상인 학생 조회, 일반 어노테이션에서는 사용 불가. */

    // @Select(" select * from student where kor >= #{ kor}")
    // 자바 15 이상 부터 """ 템플릿 지원 +연산자처럼 문자열 연결해줌.
    // <script>
    //            // XML 방식의 SQL 작성 할 수 있다.
    //            </script>
    // where 1 = 1 : 무조건 true로 만들기 위한 강제 참(true), 뒤에 동적 쿼리 if문을 작성하기 위해서 
    // <if test="조건식">
    //   참일 경우 sql
    // </if>
    @Select("""
            <script>
                select * from student where 1 = 1
                <if test="kor != null">
                    and kor >= #{ kor } 
                </if>
            </script>       
            """)
    // 방법1
    StudentDto query1( int kor );

    // 6. 방법2 : XML  : 조건에 맞는 동적쿼리 : 여러개 학생 검색!
    List<StudentDto> query2( int kor );
    
    // 7. 이름(포함된) 또는 수학점수(이상)로 검색
    StudentDto query3( String name, int math );

    // 8. 여러개 학생 등록( forEach )
    int saveAll( List<StudentDto> studentDtos );

}// i end
