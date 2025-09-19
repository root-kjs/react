package movie.service;

import lombok.RequiredArgsConstructor;
import movie.model.dto.CommentDto;
import movie.model.mapper.CommentMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;

    // 1. 댓글 작성
    public int addComment( CommentDto commentDto ){
        return commentMapper.addComment( commentDto );
    }//f end

    // 2. 댓글 삭제
    public boolean deleteComment( int cno ){
        return commentMapper.deleteComment( cno );
    }//f end

    // 3. 특정 영화 > 댓글 전체 조회
    public List<CommentDto> printComment(int mno){
        return commentMapper.printComment( mno );
    }//f end

}//class end
