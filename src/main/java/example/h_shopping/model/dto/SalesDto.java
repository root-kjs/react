package example.h_shopping.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data //  @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor 어노테이션을 한 번에 포함하는 '만능' 어노테이션
public class SalesDto {
    private int salenol; // 매출번호
    private int custno; // 회원번호
    private int pcost; // 단가
    private int amount; // 수량
    private int price; // 가격
    private String pcode; // 상품코드
    private String sdate; // 판매일
}// class end
