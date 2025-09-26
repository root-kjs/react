package example.실습.transactional.service;

import example.실습.transactional.model.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
