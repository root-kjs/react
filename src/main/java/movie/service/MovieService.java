package movie.service;

import lombok.RequiredArgsConstructor;
import movie.model.dto.MovieDto;
import movie.model.mapper.MovieMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieMapper movieMapper;

    // 1. 영화 등록
    public int addMovie( MovieDto movieDto ){
        return movieMapper.addMovie(movieDto);
    }//f end

    // 2. 영화 삭제
    public boolean deleteMovie( int mno ){
        return movieMapper.deleteMovie( mno );
    }//f end

    // 3. 영화 목록 조회
    public List<MovieDto> printMovie(){
        return movieMapper.printMovie();
    }//f end

}//class end
