package example2.day04.model.entity;

import example2.day04.model.dto.TodoDto;
import jakarta.persistence.*;
import lombok.*;

@Entity // jpa 해당 엔티티를 테이블 매핑
@Table(name = "todo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoEntity extends BaseTime { // BaseTime 상속
    // 1. 테이블 설계
    // @EnableJpaAuditing // AppStart 주입/ BaseTime 사용, 생성일/수정일 수정
    // MySQL은 자바에서 카멜 사용하면 _ 스네이크로 들어온다. createDate --> create_date
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )//  오토키
    private int tno; //pk
    @Column( nullable = false, length = 100 ) // 테이블 속성 옵션
    private String title; // 제목
    @Column( columnDefinition = "longtext")// sql의 진짜 문법을 넣어도 된다.
    private String content; //내용
    @Column( columnDefinition = "boolean default false")
    private boolean done; //실행(체크) 여부

    // 2. 참조 관계 정의
    
    
    //3. 엔티티를 --> DTO로 변환하는 함수 정의( R )
    public TodoDto toDto(){
        return TodoDto.builder() // new 대신 사용, 순서 안지켜도 됨. 갯수 안지켜도 됨
                .tno( this.tno )
                .title( this.title )
                .content( this.content )
                .done( this.done )
                .createDate( this.getCreateDate().toString() )
                .updateDate( this.getUpdateDate().toString() )
                .build();
    }

}// class end
