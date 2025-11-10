package example2.day04.controller;


import example2.day04.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // 1.
    @GetMapping("/query1")
    public ResponseEntity<?> query1( @RequestParam String title ){
        return ResponseEntity.ok( todoService.query1( title ));
    }

    // 2. And 연산자
    @GetMapping("/query2")
    public ResponseEntity<?> query2(@RequestParam String title, @RequestParam String content){
        return ResponseEntity.ok( todoService.query2( title, content));
    }

    // 3. like 연산자
    @GetMapping("/query3")
    public ResponseEntity<?> query3( @RequestParam String title ){
        return ResponseEntity.ok( todoService.query3(title));
    }

    // 4. 페이징 처리(기본)
    @GetMapping("/page")
    public ResponseEntity<?> page(
            @RequestParam(defaultValue = "1") int page, // 조회할 페이지 번호
            @RequestParam(defaultValue = "3") int size  // 조회 페이지에(1페이지당) 조회할 자료 총 갯수
     ){
        return ResponseEntity.ok( todoService.page( page,size) );
    }

    // 5. 검색결과 페이징 처리
    @GetMapping("/page2")
    public ResponseEntity<?> page2( @RequestParam String keyword ,
                                    @RequestParam int page,
                                    @RequestParam int size
                                    ){
        return ResponseEntity.ok( todoService.page2( keyword, page, size));
    }



}// class end
