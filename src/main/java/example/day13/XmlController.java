package example.day13;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
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
        // int 로 하게 되면 완료된 레코드의 수
        // @Options( useGeneratedKeys = true, keyProperty = "sno") 하게 되면 Dto sno를 반환한다.
        System.out.println("studentDto = " + studentDto); // studentDto = StudentDto(sno=0, name=11, kor=11, math=11)
        xmlMapper.save(studentDto);

        System.out.println("studentDto = " + studentDto); // studentDto = StudentDto(sno=6, name=11, kor=11, math=11)
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

    // 3. 개별 조회
    @GetMapping("/s")
    public ResponseEntity<?> find( @RequestParam int sno ){
        // < ? > ----> 제네릭 타입에 ? 넣으면 와일드 카드 됨. 모든 타입을 지칭한다.
        // .ok( true ) ----> 소괄호 안에 모든 타입 데이터 입력 가능해진다.
        StudentDto result = xmlMapper.find( sno );
        return ResponseEntity.ok( result );
    }

    // 4. 개별 삭제 int sno : 레코드 수를 반환 // Dto dp sno 매핑됨
    @DeleteMapping
    public ResponseEntity<?> delete( @RequestParam int sno ){
        // < ? > ----> 제네릭 타입에 ? 넣으면 와일드 카드 됨. 모든 타입을 지칭한다.
        // .ok( true ) ----> 소괄호 안에 모든 타입 데이터 입력 가능해진다.
        xmlMapper.delete( sno );
        return ResponseEntity.ok( true );
    }

    // 5. 개별 수정 StudentDto studentDto
    @PutMapping
    public ResponseEntity<?> update( @RequestBody StudentDto studentDto ){
        // < ? > ----> 제네릭 타입에 ? 넣으면 와일드 카드 됨. 모든 타입을 지칭한다. 모든 자료가 대입된다.
        // .ok( true ) ----> 소괄호 안에 모든 타입 데이터 입력 가능해진다.
        xmlMapper.update( studentDto );
        return ResponseEntity.ok( true );
    }



}// class end
