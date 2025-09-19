package movie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDto {
    private int mno;            // PK번호
    private String mName;       // 영화명
    private String msupervision;// 영화감독
    private String mgenre;      // 장르
    private String mintro;      // 영화소개
    private String nickname;    // 닉네임
    private String mdate;       // 작성일
    private String mpwd;        // 비번4자리

}//class end
