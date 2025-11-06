package example2.day03._자바참조;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
public class Category {

    //1. 멤버변수
    private int cno;
    private String cname;
    //***************  순환참조 방지  @ToString.Exclude PK <--> FK  ****************
    @ToString.Exclude //ToString 제외 참조 주소값 대신에 참조 내용값 호출, 순환참조 방지
    List<Board> boardList = new ArrayList<>();


    //2. 생성자( 실체를 만드는 것을 instance / 메모리에 올라감 / )



    //3. 메소드

}// class end
