package movie.controller;

import lombok.RequiredArgsConstructor;
import movie.model.dto.CommentDto;
import movie.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
@CrossOrigin( value = "http://localhost:5173" ) // 리액트서버와 CORS통신(서로 다른 서버간의 요청/응답) 허용
public class CommentController {

    private final CommentService commentService;

    // 1. 댓글 작성
    @PostMapping
    public int addComment( @RequestBody CommentDto commentDto ){
        return commentService.addComment( commentDto );
    }//f end

    // 2. 댓글 삭제
    @DeleteMapping
    public boolean deleteComment( @RequestParam int cno ){
        return commentService.deleteComment( cno );
    }//f end

    // 3. 특정 영화 > 댓글 전체 조회
    @GetMapping
    public List<CommentDto> printComment( @RequestParam int mno ){
        return commentService.printComment( mno );
    }//f end

}//class end
