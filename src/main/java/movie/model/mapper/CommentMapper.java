package movie.model.mapper;

import movie.model.dto.CommentDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface CommentMapper {

    // 1. 댓글 작성
    @Insert("insert into comment_movie( mno, ccomment, nickname, mpwd ) values( #{ mno }, #{ ccomment }, #{ nickname }, #{ mpwd } )")
    public int addComment( CommentDto commentDto );

    // 2. 댓글 삭제
    @Delete("delete from comment_movie where cno = #{ cno }")
    public boolean deleteComment( int cno );

    // 3. 특정 영화 > 댓글 전체 조회
    @Select("select * from Comment")
    public List<CommentDto> printComment();

}//interface end
