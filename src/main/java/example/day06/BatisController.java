package example.day06;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/day06/batis")
@RequiredArgsConstructor // final 변수에 대해 자동 생성자
public class BatisController {
    // * Mapper 인터페이스를 DI
    private final BatisMapper batisMapper;
    // 1. 학생 등록
    @PostMapping("") // http://localhost:8080/day06/batis
    // body : { "name" : "유재석 " , "kor" : "90" , "math" : "70" }
    public ResponseEntity<Integer> save(
            @RequestBody StudentDto studentDto ){
        int result = batisMapper.save( studentDto );
        return ResponseEntity.status( 200 ).body( result );
    }

    // 2. 전체 학생 조회
    @GetMapping("") // http://localhost:8080/day06/batis
    public ResponseEntity< List<StudentDto> > findAll(){
        List< StudentDto > result = batisMapper.findAll(); // 현재 예제는 서비스를 생략
        // ResponseEntity 응답객체
        return ResponseEntity.status( 200 ).body( result );
    }

    // 3. 개별 학생 조회
    @GetMapping("/find") // http://localhost:8080/day06/batis/find?sno=1
    public ResponseEntity<Map<String,Object>> find(
            @RequestParam int sno ){
        Map<String,Object> result = batisMapper.find( sno );
        return ResponseEntity.status( 200 ).body( result );
    }
    // 4. 개별 학생 삭제
    @DeleteMapping("")  // http://localhost:8080/day06/batis?sno=1
    public ResponseEntity< Integer > delete(
            @RequestParam int sno ){
        int result = batisMapper.delete( sno );
        return ResponseEntity.status( 200 ).body( result );
    }
    // 5. 개별 학생 (kor/math) 수정
    @PutMapping("") // http://localhost:8080/day06/batis
    // body : { "sno" : "2 " , "kor" : "100" , "math" : "100" }
    public ResponseEntity<Integer> update(
            @RequestBody StudentDto studentDto ){
        int result = batisMapper.update( studentDto );
        return ResponseEntity.ok( result ); // ok == 200
    }



} // class end















