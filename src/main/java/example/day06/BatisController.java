package example.day06;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/day06/batis")
@RequiredArgsConstructor // final 변수에 대해 자동 생성자
public class BatisController {
    // * Mapper 인터페이스를 DI
    private final BatisMapper batisMapper;
    // 1. 학생 등록
    // 2. 전체 학생 조회
    @GetMapping("") // http://localhost:8080/day06/batis
    public ResponseEntity< List<StudentDto> > findAll(){
        // 현재 예제는 서비스를 생략
        List< StudentDto > result = batisMapper.findAll();
        // ResponseEntity 응답객체
        return ResponseEntity.status( 200 ).body( result );
    }
    // 3. 개별 학생 조회
    // 4. 개별 학생 삭제
    // 5. 개별 학생 수정
}
