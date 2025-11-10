package example2.day04.service;

import java.util.List;
import java.util.stream.Collectors;

import example2.day04.model.dto.TodoDto;
import example2.day04.model.entity.TodoEntity;
import example2.day04.model.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;

    // 1.
    public List<TodoDto> query1( String title){

        // 1. 내가 만든 명명 규칙 사용한 쿼리 메소드
       List<TodoEntity> result1 =
               todoRepository.findByTitle( title );
        System.out.println("result1 = " + result1);

        // 2. 내가 만든 네이티브 쿼리 메소드
        List< TodoEntity> result2 =
                todoRepository.query1(title);
        System.out.println("result2 = " + result2);
        //stream().map() 문법
        return result2.stream().map( TodoEntity::toDto ).collect( Collectors.toList());

    }// funcx end

    //2.
    public List<TodoDto> query2( String title, String content ){
        List< TodoEntity> result1 = todoRepository.findByTitleAndContent( title, content);
        System.out.println("result1 = " + result1);

        List< TodoEntity > result2 = todoRepository.query2( title, content);

        return result2.stream().map( TodoEntity::toDto).collect( Collectors.toList());

    }//func end

    // 3.
    public List<TodoDto> query3( String title ){
        List<TodoEntity> result1 = todoRepository.findByTitleContaining( title );
        System.out.println("result1 = " + result1);

        List<TodoEntity> result2 = todoRepository.query3( title );
        System.out.println("result2 = " + result2);

        return result2.stream().map( TodoEntity::toDto).collect( Collectors.toList());
    }//func end


} // class end
