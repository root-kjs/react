package example2.day04.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// Dto : DB 데이터 이동 객체
// 기능 구현에 필요한 목적에 따른 이동할 데이터 구성 
// 실무에서는 기능별로 dto 선언하는 경우가 많다. 
// 1. 테이블과 유사하게 만드는 경우, 
// 2. 기능/상황별로 만드는 경우(DTO 많아짐, 폴더로 나누면 유지보수가 좀 나아 질 수 있음), MAP을 사용하는 경우도 많음

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {
    private int tno;            // RU
    private String title;       // CRU
    private String content;     // CRU
    private boolean done;       // CRU
    private String createDate;  // R
    private String updateDate;  // R
    
    // DTO를 --> 엔티티로 변환하는 함수 정의( 저장할때 C )
    
}// class end
