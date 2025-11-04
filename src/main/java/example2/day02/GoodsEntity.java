package example2.day02;

import jakarta.persistence.*;
import lombok.*;

@Entity // 해당 클래스를 데이터 베이스 테이블과 매핑
@Table(name="goods") // 테이블 이름 정의, 생략시 해당 클래스명으로 테이블이 생성된다.
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )// MYSQL에서만 가능, auto_increment주입
    private int gno;
    @Column(nullable = false, length = 100 ) // @Column( 속성명 = 값. 속성명 = 값 ), null 제외, 글자수 100
    private String gname;
    @Column(nullable = true)
    private int gprice;
    @Column( columnDefinition = "varchar(100) default '제품설명' not null" ) // 원하는 SQL 구문 작성 가능
    private String gdesc;
}
