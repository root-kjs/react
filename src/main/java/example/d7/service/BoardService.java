package example.d7.service;

import example.d7.model.dto.BoardDto;
import example.d7.model.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;

    // [1] 등록
    public boolean boardWrite(BoardDto boardDto ){
        boolean result = boardMapper.boardWrite( boardDto );
        return result;
    }
    
    // [2] 전체조회
    public List<BoardDto> boardPrint() {
        List<BoardDto> result = boardMapper.boardPrint();
        return result;
    }

    // [3] 개별조회
    public BoardDto boardFind(int bno) {
        BoardDto result = boardMapper.boardFind( bno );
        return result;
    }

    // [4] 개별삭제
    public boolean boardDelete( int bno ){
        boolean result = boardMapper.boardDelete( bno );
        return result;
    }

    // [5] 개별수정
    public boolean boardUpdate (BoardDto boardDto){
       boolean result =  boardMapper.boardUpdate( boardDto );
       return result;
    }//f end


}// class ends
