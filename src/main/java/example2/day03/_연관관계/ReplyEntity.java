package example2.day03._연관관계;

import example2.day03._자바참조.Board;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table( name = "ereply" )
public class ReplyEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int rno;
    private String rconent;

    // 단방향 테이블 연결
    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )// 옵션은 선택
    @JoinColumn( name = "bno" ) // fk  필드명 (원래 pk랑 같이)
    private BoardEntity boardEntity;



}// class end
