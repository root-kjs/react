package example.d8_전화번호부.controller;

import example.d8_전화번호부.model.dto.PhoneDto;
import example.d8_전화번호부.service.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phone")
@RequiredArgsConstructor
public class PhoneController {

    private final PhoneService phoneService;

    // 1. 등록
    @PostMapping
    public ResponseEntity<Integer> addPhone(@RequestBody PhoneDto phoneDto ){
        // return ResponseEntity.ok().body( phoneService.addPhone( phoneDto ) ); // 기본(요청성공) --> .status(200)
        // ★★★ ResponseEntity --> 기본 HTTP 응답(반환) 코드 외 추후 비즈니스 로직에 관련된 에러코드를 넣는게 효율적임
        try {
            int isSuccess = phoneService.addPhone( phoneDto );
            if (isSuccess > 0) { // 응답 성공 시
                return ResponseEntity.ok().body(isSuccess); // .status(200)
            } else {
                return ResponseEntity.badRequest().body(0); // .status(400)
            }// if end
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0); //.status(400)
        }
    }// f end

    // 2. 전체 조회
    @GetMapping
    public ResponseEntity<List<PhoneDto>> printPhone(){
        return ResponseEntity.ok().body(phoneService.printPhone());
    }

    // 3. 삭제
    @DeleteMapping
    public boolean deletePhone( @RequestParam int bno ){
        return phoneService.deletePhone( bno );
    }

}// class end
