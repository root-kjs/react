package example2.day01;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data// 컨트롤러에서 게터 세터 사용하기 위해
public class ExamEntity {
    @Id
    private int col1;
    private String col2;
    private double col3;
}
