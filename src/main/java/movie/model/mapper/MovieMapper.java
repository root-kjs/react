package movie.model.mapper;

import movie.model.dto.MovieDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface MovieMapper {

    // 1. 영화 등록
    @Insert("insert into movie( mName, msupervision, mgenre, mintro, nickname, mpwd ) values( #{ mName }, #{ msupervision }, #{ mgenre }, #{ mintro }, #{ nickname }, #{ mpwd } )")
    public int addMovie( MovieDto movieDto );

    // 2. 영화 삭제
    @Delete("delete from movie where mno = #{ mno }")
    public boolean deleteMovie( int mno );

    // 3. 영화 목록 조회
    @Select("select * from movie ORDER BY mno DESC")
    public List<MovieDto> printMovie();

}//interface end
