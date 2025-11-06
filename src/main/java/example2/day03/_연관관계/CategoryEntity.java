package example2.day03._연관관계;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@Table( name = "ecategory")
public class CategoryEntity { //PK
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int cno;
    private String cname;

    // ** 양방향 연결!!!!!!!!!!!!! **//
    // 상위 엔티티가 하위 엔티티 참조관계. 양방향 (개발자의 코딩 조회속도가 빠르다. 메모리 속도가 빠른 게 아님.주의!!)
    // 자바에서는(만) 양방향을 사용하고, DB에서는 양방향(매핑테이블) 하지 않는다.
    // !!!!!!!!!!!! 자바에서만 매핑을 하고(양방향을 사용하고), DB에서는 양방향(매핑테이블) 하지 않는다.
    @OneToMany( mappedBy = "categoryEntity" ) // 1:다 하나의 PK 가 다수 FK 에게, 참조하는 jsa 테이블 엔티티 명//  자바에서만 직접, 안하면 테이블 생김
    @ToString.Exclude // 순환참조 방지(계속 호출)
    @Builder.Default // 만약에 빌더패턴 사용시 초기값 사용
    private List<BoardEntity> boardEntityList = new ArrayList<>();

}
