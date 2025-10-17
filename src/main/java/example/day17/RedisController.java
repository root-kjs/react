package example.day17;

import example.day13.StudentDto;
import example.day13.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisController {

    // [*] 간단한 텍스트를 레디스에 접근하는 객체
    private final RedisTemplate redisTemplate;

    // 생성자에서 레디스 config하는 방법도 있음(지금은 별도 class 로 구현)


    // [1] 간단한 텍스트를 레디스 서버에 저장하기(나중에 서비스(Service) 에서 호출할 것 )
    @GetMapping("/test")
    public ResponseEntity<?> test(){

        // [저장]
        System.out.println("RedisController.test");
        redisTemplate.opsForValue().set( "유재석", "90");
        redisTemplate.opsForValue().set( "강호동", "80");
        redisTemplate.opsForValue().set( "유재석", "100");
        // [호출] 레디스템플릿 객체명.keys("*") ---> 레디스에 저장된 모든 키 반환
        redisTemplate.keys("*");

        Set< String > keys = redisTemplate.keys("*");
        List<Object> result = new ArrayList<>(); // 임의의 리스트
        for( String key : keys){
            result.add( redisTemplate.opsForValue().get( key )); // 특정한 키의 값 호출
        }
        return ResponseEntity.ok(result);
    }// func end

    // --------------------- crud 테스트 () ---------------------------
    private final RedisTemplate<String, Object> studentRedisTemplate;

    // 1. 등록
    @PostMapping  // { "sno" : "1", "name": "강호동", "kor" : "90", "math" : "80" }
    private ResponseEntity<?> save(@RequestBody StudentDto studentDto ){
        // < ? > ----> 제네릭 타입에 ? 넣으면 와일드 카드 됨. 모든 타입을 지칭한다.
        // .ok( true ) ----> 소괄호 안에 모든 타입 데이터 입력 가능해진다.
        // int 로 하게 되면 완료된 레코드의 수
        // @Options( useGeneratedKeys = true, keyProperty = "sno") 하게 되면 Dto sno를 반환한다.
        System.out.println("studentDto = " + studentDto); // studentDto = StudentDto(sno=0, name=11, kor=11, math=11)

        // 1. 중복없는 key 구상
        String key = "student:"+studentDto.getSno(); //
        // 2. 레디스에 전달받은 값을 저장한다.
        // 예상 { "student1":{ sno : 1, name: "강호동", kor : 90, math : 80 } }
        studentRedisTemplate.opsForValue().set( key, studentDto);

        System.out.println("studentDto = " + studentDto); // studentDto = StudentDto(sno=6, name=11, kor=11, math=11)
        return ResponseEntity.ok().body( "redis 저장성공" );

    }

    // 2. 전체조회
    @GetMapping
    private ResponseEntity<?> findAll( ){
        // 1. 조회할 모든 키를 가져온다. * 레디스
        // studentRedisTemplate.keys("패턴문자열*"); // 패턴 문자열까지는
        Set< String> keys = studentRedisTemplate.keys("student:*"); // 패턴 문자열까지는
        List<Object> result = new ArrayList<>();
        for( String key : keys ){
            result.add(studentRedisTemplate.opsForValue().get(key));
        }

        return ResponseEntity.ok().body( result );
    }

    // 3. 개별 조회
    @GetMapping("/s")
    public ResponseEntity<?> find( @RequestParam int sno ){
        // 1. 조회할 키 구상
        String key = "student:"+sno;
        Object result = studentRedisTemplate.opsForValue().get(key);
        return ResponseEntity.ok( result );
    }

    // 4. 개별 삭제 int sno : 레코드 수를 반환 // Dto dp sno 매핑됨
    @DeleteMapping
    public ResponseEntity<?> delete( @RequestParam int sno ){
        String key = "student:"+sno;
        boolean result = studentRedisTemplate.delete(key);
        return ResponseEntity.ok( result );
    }

    // 5. 개별 수정 StudentDto studentDto{ "sno" : "1", "name": "강호동", "kor" : "90", "math" : "80" }
    @PutMapping
    public ResponseEntity<?> update( @RequestBody StudentDto studentDto ){
        // 수정할 키 구상
        String key = "student:"+studentDto.getSno();
        //
        studentRedisTemplate.opsForValue().set(key, studentDto);
        return ResponseEntity.ok( true ); // ok = 200
    }



}// class end
