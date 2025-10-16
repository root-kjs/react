package example.실습.실습4;

import example.day13.StudentDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private final BookMapper bookMapper;

    // 1. 책 단일 등록( 생성된 도서번호 반환 )
    //int saveBook( BookDto bookDto );
    @PostMapping
    public ResponseEntity<?> saveBook( @RequestBody BookDto bookDto ){
        System.out.println("bookDto = " + bookDto);
        bookMapper.saveBook( bookDto );
        System.out.println("bookDto = " + bookDto);
        return ResponseEntity.ok( true );
    }//func end

    // 2.  책 일괄 등록
    //int saveAllBook( List<BookDto> bookDtos );
    @PostMapping("/all")
    public ResponseEntity<?> saveAllBook( @RequestBody List<BookDto> bookDtos ){
        System.out.println("bookDtos = " + bookDtos);
        bookMapper.saveAllBook(bookDtos);
        System.out.println("bookDtos = " + bookDtos);
        return ResponseEntity.ok( true );
    }

    // 3. 대출 기록 검색
    // - 대출한 사람 또는 대출한도서명 으로 조회
    // - 조건이 없을 경우 전체 조회
    //List<BookDto> findBook();
    @GetMapping
    public ResponseEntity<?> findBook( @RequestParam String member , String title ){
        List<BookDto> result = bookMapper.findBook( member, title );
        return ResponseEntity.ok( result );
    }

    // 4.ALTER 이용한 테이블 수정2개 controller/service/mapper
        // 4-1. 책books 테이블에 price 가격(int) 필드 추가
        // 4-2. 책books 테이블에 title 책이름 필드 (longtext) 필드 수정
    @GetMapping("/edit_c")
    public ResponseEntity<?> eidtBookColumn( ){
        List<BookDto> result = bookMapper.eidtBookColumn(  );
        return ResponseEntity.ok( result );
    }

    @PutMapping("/edit_2")
    public ResponseEntity<?> eidtBookColumn2(){
        bookMapper.eidtBookColumn2(  );
        return ResponseEntity.ok( true );
    }

    // 실습6. [ 조건2 ] view 생성 기능 2개 controller/service/mapper <UPDATE>
    //    1. 대출기록 상세 뷰 생성 ( 책 + 대출기록 JOIN )
    @PutMapping("/view1")
    public ResponseEntity<?> view1Book(){
        bookMapper.view1Book();
        return ResponseEntity.ok( true );
    }// func end

    // 실습6. [ 조건2 ] view 생성 기능 2개 controller/service/mapper <UPDATE>
    //    2. 평균보다 많은 재고를 가진 도서 조회 뷰 생성
    @PutMapping("/view2")
    public ResponseEntity<?> view2Book(){
        bookMapper.view2Book();
        return ResponseEntity.ok( true );
    }// func end

    // 실습6. [ 조건3 ] 생성한 view 조회 기능 2개 controller/service/mapper
    //    1. 대출 상세 뷰 조회
    @GetMapping("/view1")
    public ResponseEntity<?> view3Book(){
        List<Map<String, Object>> bookDtos = bookMapper.view3Book();
        return ResponseEntity.ok( bookDtos );
    }

    // 실습6.[ 조건3 ] 생성한 view 조회 기능 2개 controller/service/mapper
    //    2. 많은 재고를 가진 도서 조회 뷰 조회
    @GetMapping("/view2")
    public ResponseEntity<?> view4Book(){
        List<BookDto> bookDtos = bookMapper.view4Book();
        return ResponseEntity.ok( bookDtos );
    }





}// class end
