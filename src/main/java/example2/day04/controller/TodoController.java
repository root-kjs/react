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

    // 32. like 연산자
    @GetMapping("/query3")
    public ResponseEntity<?> query3( @RequestParam String title ){
        return ResponseEntity.ok( todoService.query3(title));
    }



}// class end
