package example2.day02;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 엔티티는 서비스에서만 사용
// JPA
@Data@AllArgsConstructor@NoArgsConstructor@Builder
public class GoodsDto {
    // 데이터베이스/엔티티 필드/속성 기반으로 구성
    private int gno;
    private int gprice;
    private String gname;
    private String gdesc;
    private String create_date;
    private String update_date;
    // DTO를 엔티티로 변경하는 것은  controller --> Service

    // controller --> Service : 등록.수정
    // 밖으로 나갈때는 DTO/ 들어올 때는  Entity
    public GoodsEntity toEntity(){ // 자동 생성되는 컬럼은 제외
        return GoodsEntity.builder()
                .gno(this.gno) // 수정 할때 사용.
                .gname(this.gname)
                .gprice( this.gprice)
                .gdesc(this.gdesc)
                .build();
    }
}
