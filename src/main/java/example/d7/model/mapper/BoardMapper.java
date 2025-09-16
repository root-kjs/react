package example.d7.model.mapper;

import example.d7.model.dto.BoardDto;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper // 마이바티스 어노테이션
public interface BoardMapper { // @Mapper class 는 interface 안의 메소드는 추상메소드 ==> 선언 {} 빼고 선언한다.

    // [1] 등록
    @Insert("insert into board( bcontent, bwriter ) values( #{bcontent}, #{bwriter} )" )
    public boolean boardWrite( BoardDto boardDto ); //{} 중괄호 없는 추상 메소드로 작업한다. Mapper 클래스 자체가 인터페이스임.

    // [2] 전체 조회
    @Select("select * from board")
    public List<BoardDto> boardPrint();

    // [3] 개별조회
    @Select("select * from board where bno = #{bno} ")
    public BoardDto boardFind(int bno);

    // [4] 개별삭제
    @Delete("delete from board where bno = #{bno}")
    public boolean boardDelete( int bno );

    // [5] 개별수정
    @Update("update board set bcontent = #{bcontent} where bno = #{bno}")
    public boolean boardUpdate(BoardDto boardDto);

}//class end
