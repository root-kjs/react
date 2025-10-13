package example.실습.실습4;

import example.day13.StudentDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}// class end
