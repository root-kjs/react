package example2.day03._연관관계;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table( name = "eboard")
public class BoardEntity { // FK
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int bno;
    private String btitle;
    private String bcontent;

    // 양방향 (개발자의 코딩 조회속도가 빠르다. 메모리 속도가 빠른 게 아님.주의!!)

    // @ManyToOne  단방향 연결!!!!!, 하위 엔티티가 상위 엔티티 참조관계
    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY ) // 다수가 하나에게, FK 가 PK 에게(단방향)
    // fetch ===> 상태를 가져온다 . ( 양방향으로 가져올때 EAGER--> 바로 categoryEntity 가져오기 , LAZY --> 지연로딩.   )
    // 실무에서는 LAZY
    //CascadeType.ALL --> PK 삭제 되면 fk 삭제
    //
    @JoinColumn( name = "cno" ) // fk 필드명 (+PK 필드명과 같게 하는것을 권장)
    private CategoryEntity categoryEntity;
}
