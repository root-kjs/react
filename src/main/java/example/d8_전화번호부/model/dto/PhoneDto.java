package example.d8_전화번호부.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhoneDto {

    private int pno;        // PK번호
    private String pName;   // 이름
    private String pPhone;  // 전화번호
    private int pAge;       // 나이


}// class end
