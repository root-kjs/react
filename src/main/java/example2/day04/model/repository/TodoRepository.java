package example2.day04.model.repository;

import example2.day04.model.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {
    // JPA Repository : 기본적으로 미리 만들어진 CRUD를 제공한다.
    // 1. save()

    // 2. findById()

    // 3. findAll()

    // 4. deleteById()

    // 5. ============================ 쿼리 메소드 ========================================
    // --- SQL 문장을 직접 작성하지 않고 메소드 이름으로 쿼리 생성! 무조건!!! <카멜 표기법> 으로 메소드명을 작성해야 한다.
    // findByTitle(); <---- vs ----> select from todo where title = 매개변수;
    // findByXXXXX(); // findBy필드명 ( String 매개변수 )
    // 5-1.  findByTitle
    List<TodoEntity> findByTitle( String title );

    // 5-2.  findByTitleAndContent,  findByTitleOrContent
    List<TodoEntity> findByTitleAndContent( String title, String content );

    // 5-3. 카멜에 오타가 나면 아예 켜지지도 않음, like 검색!
    // findBy필드명Containing <--vs -->
    List<TodoEntity> findByTitleContaining( String keyword );

    // 6. 네이티브 쿼리 메소드, 매개변수를 어떻게 처리 표현하냐의 차이임!
    // SQL 문장을 직접 작성하여 실행한다. : 매개변수를 이용하여 매개변수 대입한다. ? # 대신 :  을 넣어 매개변수를 대입한다.

    // 6.1. @Query( value = "SQL 작성 " , nativeQuery = true )
    @Query( value = "select * from todo where title = :title", nativeQuery = true )
    List< TodoEntity > query1( String title );

    // 6.2. and 연산자
    @Query( value = "select * from todo where title =:title and content = :content", nativeQuery = true)
    List< TodoEntity > query2 ( String title, String content);

    // 6.3. like 연산자
    @Query( value = "select * from todo where title like %:keyword%", nativeQuery = true)
    List< TodoEntity > query3( String keyword );


}// interface end
