package example.day13;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/xml")
@RequiredArgsConstructor
public class XmlController {
    private final XmlMapper xmlMapper;

    // 1. 등록
    @PostMapping
    public ResponseEntity<?> save(@RequestBody StudentDto studentDto ){
        // < ? > ----> 제네릭 타입에 ? 넣으면 와일드 카드 됨. 모든 타입을 지칭한다.
        // .ok( true ) ----> 소괄호 안에 모든 타입 데이터 입력 가능해진다.
        xmlMapper.save(studentDto);
        return ResponseEntity.ok( true );
    }

    // 2. 전체조회
    @GetMapping
    public ResponseEntity<?> findAll( ){
        // < ? > ----> 제네릭 타입에 ? 넣으면 와일드 카드 됨. 모든 타입을 지칭한다.
        // .ok( true ) ----> 소괄호 안에 모든 타입 데이터 입력 가능해진다.
        List<StudentDto> result = xmlMapper.findAll();
        return ResponseEntity.ok( result );
    }

}// class end
