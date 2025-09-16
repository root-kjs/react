package example.d7.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class BoardDto {
    private int bno;
    private String bcontent;
    private String bwriter;
}// class end
