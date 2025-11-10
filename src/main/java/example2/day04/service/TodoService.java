package example2.day04.service;

import java.util.List;
import java.util.stream.Collectors;

import example2.day04.model.dto.TodoDto;
import example2.day04.model.entity.TodoEntity;
import example2.day04.model.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    // 3. like 검색
    public List<TodoDto> query3( String title ){
        List<TodoEntity> result1 = todoRepository.findByTitleContaining( title );
        System.out.println("result1 = " + result1);

        List<TodoEntity> result2 = todoRepository.query3( title );
        System.out.println("result2 = " + result2);

        return result2.stream().map( TodoEntity::toDto).collect( Collectors.toList());
    }//func end

    // 4. 페이징처리
    // import org.springframework.data.domain.Page;
    public Page<TodoDto> page( int page, int size){
        // 1) 페이징 처리 옵션을 설정한다.
        //PageRequest.of( 조회할페이지번호, 조회할페이지당데이터수, Sort.by( Sort.Direction.ASC, "정렬속성명(컬럼)") )
        // PageRequest 객체로 저장
        PageRequest pageRequest = PageRequest.of( page-1, size, Sort.by( Sort.Direction.DESC, "tno"));
        // page - 1 : JPA는 페이지번호를 0부터 시작함으로 1페이지가 0이고, 2페이지가 1로 처리됨에 -1
        // size : 현재 1페이지에 조회할 자료개수, 1페이지에 3개 개수

        // 2) 조회한다. 페이지 타입으로 저장
        // Page : 페이징 처리결과를 담는 인터페이스 타입, 다양한 페이징 결과를 제공한다.
        Page<TodoEntity> result = todoRepository.findAll( pageRequest );
        // List<TodoEntity> result = (List<TodoEntity>) todoRepository.findAll( pageRequest );

        // 3) 조회결과 반환 : page 타입은 스트립을 기본적으로 제공한다. stream().map(TodoEntity::toDto); ---> X 안적어도 된다.
        return result.map(TodoEntity::toDto);

    }// func end
    
    
    // 검색결과 > 페이징 처리, Page 타입으로 가져온다. 
    public Page<TodoDto> page2( String keyword, int page, int size){
// 2. 검색이 있으면 검색조회( 먼저, 페이지 옵션 넣어준다. )
        // Pageable ---> 이게 더 상위 개념 interface( 다형성) --> 상속을 받았거나 구현체 이거나 PageRequest--> 구현체
        Pageable pageable =  PageRequest.of( page-1, size, Sort.by( Sort.Direction.DESC, "tno")); // 페이지 옵션
        Page<TodoEntity> result;
        // 1. 만약에 검색이 없으면 전체 조회
        if( keyword == null || keyword.isBlank() ){ // 키워드가 널이거나 공백이면
            result = todoRepository.findAll( pageable  ); // 전체 조회
        }else {
            result = todoRepository.findByTitleContaining( keyword, pageable );
        }
        // result  내 모든 자료들을 하나씩 toDto 함수를 호출하여 반환값들을 새로운 리스트에 반환한다.
        return result.map( TodoEntity :: toDto );

    }// func end


} // class end
