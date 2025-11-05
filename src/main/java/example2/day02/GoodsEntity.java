package example2.day02;

import jakarta.persistence.*;
import lombok.*;

@Entity // 해당 클래스를 데이터 베이스 테이블과 매핑
@Table(name="goods") // 테이블 이름 정의, 생략시 해당 클래스명으로 테이블이 생성된다.
@Data@Builder@NoArgsConstructor@AllArgsConstructor
public class GoodsEntity extends BaseTime{
    @Id // PK필드 주입 :: JPA는 엔티티 1개당 PK필드는 1개이상 필수이다
    @GeneratedValue( strategy = GenerationType.IDENTITY )// MYSQL에서만 가능, auto_increment주입
    private int gno; // 제품번호     , 자바 int -->  DB int
    @Column(nullable = false, length = 100 ) // @Column( 속성명 = 값. 속성명 = 값 ), null 제외, 글자수 100
    private String gname;// 제품명      , 자바 String -->  DB varchar(255)
    @Column(nullable = true)// null 포함
    private int gprice;
    @Column( columnDefinition = "varchar(100) default '제품설명' not null" ) // 원하는 SQL 구문 작성 가능
    // columnDefinition = "SQL 구문 직접 작성"
    private String gdesc;


    public GoodsDto toDto(){
        //  객체 생성 방법1 : new 클래스명( 값 , 값 );
        //  객체 생성 방법2 : 클래스명.builder(). 속성명(값).속성명(값).build()
        return GoodsDto.builder()
                .gno(this.gno) // this이란? 현재 메소드를 호출한 인스턴스(객체)
                .gname(this.gname)
                .gprice( this.gprice)
                .gdesc(this.gdesc)
                .update_date(this.getUpdateDate().toString()) // 날자 스트링 변환
                .create_date(this.getCreateDate().toString())
                .build();
    }
}
