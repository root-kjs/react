package example2.practice1107;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table( name = "todo")
@Data
@Builder
public class TodoEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int tno;
    private String title;
    private String content;
}//class end
