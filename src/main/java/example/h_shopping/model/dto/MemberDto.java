package example.h_shopping.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDto {
    private int custno;         // 회원번호
    private String custname;    // 회원명
    private String phone;       // 전화번호
    private String address;	    // 주소
    private String joindate;   	// 가입일
    private String grade;  		// 고객등급(A/B/C) 1자리
    private String city;		// 거주도시 코드 2자리
}// class end
