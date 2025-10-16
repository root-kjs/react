package example.실습.실습4;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface BookMapper {
    // 1. 책 단일 등록( 생성된 도서번호 반환 )
    int saveBook( BookDto bookDto );

    // 2.  책 일괄 등록
    int saveAllBook( List<BookDto> bookDtos );

    // 3. 대출 기록 검색
        // - 대출한 사람 또는 대출한도서명 으로 조회
        // - 조건이 없을 경우 전체 조회
    List< BookDto > findBook( String member , String title );

    // 4.ALTER 이용한 테이블 수정2개 controller/service/mapper
        // 4-1. 책books 테이블에 price 가격(int) 필드 추가
        // 4-2. 책books 테이블에 title 책이름 필드 (longtext) 필드 수정
    List<BookDto> eidtBookColumn();


     int eidtBookColumn2(  );

    // 실습6. [ 조건2 ] view 생성 기능 2개 controller/service/mapper <UPDATE>
    //    1. 대출기록 상세 뷰 생성 ( 책 + 대출기록 JOIN )
    int view1Book();

    // 실습6. [ 조건2 ] view 생성 기능 2개 controller/service/mapper <UPDATE>
    //    2. 평균보다 많은 재고를 가진 도서 조회 뷰 생성
    int view2Book();

    //    [ 조건3 ] 생성한 view 조회 기능 2개 controller/service/mapper
    //    1. 대출 상세 뷰 조회
    List<Map<String, Object>> view3Book();

    //    [ 조건3 ] 생성한 view 조회 기능 2개 controller/service/mapper
    //    2. 많은 재고를 가진 도서 조회 뷰 조회
    List<BookDto> view4Book();



}//interface end
