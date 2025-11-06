package example2.day03._연관관계;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
// import java.util.List;


@Entity
@Data
@Builder
@Table(name = "eboard")
public class BoardEntity { // FK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;
    private String btitle;
    private String bcontent;

    // 양방향 (개발자의 코딩 조회속도가 빠르다. 메모리 속도가 빠른 게 아님.주의!!)

    // @ManyToOne  단방향 연결!!!!!, 하위 엔티티가 상위 엔티티 참조관계
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // 다수가 하나에게, FK 가 PK 에게(단방향)
    // fetch ===> 상태를 가져온다 . ( 양방향으로 가져올때 EAGER--> 바로 categoryEntity 가져오기 , LAZY --> 지연로딩.   )
    // .getXXX() 하는 순간 그때 참조 엔티티 조회
    // 실무에서는 LAZY 캐싱은 기록
    //CascadeType.ALL --> PK 삭제 되면 fk 삭제
    //
    @JoinColumn(name = "cno") // fk 필드명 (+PK 필드명과 같게 하는것을 권장)
    private CategoryEntity categoryEntity;

    // ********** 양방향 연결
    @OneToMany(mappedBy = "boardEntity")
    @ToString.Exclude
    @Builder.Default
    private List<ReplyEntity> replyEntityList = new ArrayList<>();


}// class end

/*
영속성 : 자바 데이터(객체)와 데이터베이스 데이터(테이블/레코드)를 매핑(연결)
    - 즉, 영속 중이라면 자바 객체 수정시 db 데이터 수정
    - 즉. 영속중이 아니면 자바객체 수정해도 DB 데이터 그대로 (리프레쉬 해줘야 함)
pk- fk  제약조건 옵션
[ cascade ]
    cascade = CascadeType.ALL : 부모가 삭제/수정/REFRESH/DETACH되면 자식도 같이 --된다.
    cascade = CascadeType.PERSIST : 부모가 저장되면 자식도 같이 저장된다.
    cascade = CascadeType.MERGE : FK 관련 부모가 수정되면 자식도 같이 수정된다.
    cascade = CascadeType.REMOVE : 부모가 삭제되면 자식도 같이 삭제된다.
    cascade = CascadeType.REFRESH : 부모가 재호출(갱신)되면 자식도
    cascade = CascadeType.DETACH : 부모가 영속해제되면 자식도



 */
