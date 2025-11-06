package example2.day03._자바참조;

import lombok.Data;

@Data
public class Board {
    private int bno;
    private String btitle;
    private String bcontent;
    private Category category; // 참조 관계 // FK,
    // 클래스 안에 클래스 일대 다 관계 생성, 조인이 필요없다.

}
