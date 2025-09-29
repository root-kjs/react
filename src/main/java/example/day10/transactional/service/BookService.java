package example.day10.transactional.service;

import example.day10.transactional.model.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookMapper bookMapper;

    //1.
    @Transactional
    public boolean rent( Map<String, Object> body){
        return bookMapper.rent( body );
    }
}// class end
