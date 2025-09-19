package movie.controller;


import lombok.RequiredArgsConstructor;
import movie.model.dto.MovieDto;
import movie.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    // 1. 영화 등록
    @PostMapping
    public int addMovie( @RequestBody MovieDto movieDto ){
        System.out.println("movieDto = " + movieDto);
        return movieService.addMovie(movieDto);
    }//f end

    // 2. 영화 삭제
    @DeleteMapping
    public boolean deleteMovie( @RequestParam int mno ){
        return movieService.deleteMovie( mno );
    }//f end

    // 3. 영화 목록 조회
    @GetMapping
    public List<MovieDto> printMovie(){
        return movieService.printMovie();
    }//f end

}//class end
