package example2.day01;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    //
    @PostMapping
    public ResponseEntity<?> post(@RequestBody ExamEntity examEntity){
        return ResponseEntity.ok( examService.post(examEntity));
    }

    @GetMapping
    public ResponseEntity<?> get(){
        return ResponseEntity.ok( examService.get());
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam int col1){
        return ResponseEntity.ok( examService.delete(col1));
    }

    @PutMapping
    public ResponseEntity<?> put2( @RequestBody ExamEntity examEntity){
        return ResponseEntity.ok( examService.put2(examEntity));
    }


}
