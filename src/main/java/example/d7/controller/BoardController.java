package example.d7.controller;

import example.d7.model.dto.BoardDto;
import example.d7.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping // localhost:8080/board
    public ResponseEntity<Boolean> boardWrite( @RequestBody BoardDto boardDto ){
        boolean result = boardService.boardWrite( boardDto ); // 서비스 호출 하고 응답을 반환
        return ResponseEntity.status( 200 ).body( result ); // HTTP 응답코드, 응답자료
    }// f end

    // [2] 전체조회
    @GetMapping // localhost:8080/board
    public ResponseEntity<List<BoardDto>> boardPrint() {
        List<BoardDto> result = boardService.boardPrint();
        return ResponseEntity.ok().body(result); // .status( 200 ) ==> ok()
    }// f end

    // [3] 개별조회
    @GetMapping("/find") // localhost:8080/board/find?bno=1
    public ResponseEntity<BoardDto> boardFind( @RequestParam int bno ){
        BoardDto result = boardService.boardFind( bno );
        return ResponseEntity.ok().body(result);
    }// f end

    // [4] 개별삭제
    @DeleteMapping
    public ResponseEntity<Boolean> boardDelete( @RequestParam int bno ){
        boolean result = boardService.boardDelete( bno );
        return ResponseEntity.ok().body(result);
    }// f end

    // [5] 개별수정
    @PutMapping
    public ResponseEntity<Boolean> boardUpdate (@RequestBody BoardDto boardDto){ //ResponseEntity<Boolean> ResponseEntity <Boolean>제너릭 랩퍼
        boolean result =  boardService.boardUpdate( boardDto );
        return ResponseEntity.ok().body(result);
    }//f end

}// class end



