package movie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {

    private int cno;            // PK번호
    private int mno;            // 영화fK번호
    private String ccomment;    // 댓글내용
    private String nickname;    // 닉네임
    private String mpwd;        // 비번4자리
    private String cdate;       // 작성일
    
}// class end
