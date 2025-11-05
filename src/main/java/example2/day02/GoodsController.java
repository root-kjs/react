package example2.day02;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//@RequiredArgsConstructor는 final 또는 @NonNull이 붙은 필드만 파라미터로 받는 생성자를 만든다.
//실무에서는 불변성 유지 + 의존성 주입(DI, Dependency Injection) 을 위해 @RequiredArgsConstructor를 더 자주 사용한다.
@RestController
@RequestMapping("/api/goods")
@RequiredArgsConstructor//@AllArgsConstructor는 모든 필드를 파라미터로 받는 생성자를 만든다.
public class GoodsController {

    private final GoodsService goodsService;

    // 등록
    @PostMapping
    public ResponseEntity<?> goodsSave(@RequestBody GoodsDto goodsDto){
        return ResponseEntity.ok( goodsService.goodsSave(goodsDto));
    }

    // 개별조회
    @GetMapping // http://localhost:8080/api/goods?gno=1
    public ResponseEntity<?> goodsGet( @RequestParam int gno ){
        return ResponseEntity.ok( goodsService.goodsGet( gno ) );
    }

    // 개별삭제
    @DeleteMapping
    public ResponseEntity<?> goodsDelete( @RequestParam int gno ){
        return ResponseEntity.ok( goodsService.goodsDelete(gno));
    }

    // 개별수정
    @PutMapping
    public ResponseEntity<?> goodsUpdate(@RequestBody GoodsDto goodsDto){
        return ResponseEntity.ok(goodsService.goodsUpdate(goodsDto));
    }

}
