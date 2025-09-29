package example.day10.transactional.controller;

import example.day10.transactional.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Log4j2 // 2. 로그(기록)처리하는 어노테이션 제공===============================

public class BookController {
    private final BookService bookService;

    //2. print 함수 대신에 로그함수 사용해보기
    @GetMapping("/log") public void log(){
        System.out.println( " 개발단계에는 print를 정말 많이 사용하지... ");
        log.debug("테스트 과정에서 사용" ); //
        log.info("서비스의 흐름/ 상태 확인시 사용 ");
        log.warn("잠재적 문제에서 사용");
        log.error("예외/실패 상황에서 사용된다.");
    }


    //1.
    @PostMapping("/rent")
    public ResponseEntity<Boolean> rent(@RequestBody Map<String, Object> body){
        log.debug("테스트 과정에서 사용" );
        boolean result = bookService.rent( body );
        return ResponseEntity.ok(result);
    }

}// class end
