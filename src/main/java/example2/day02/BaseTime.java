package example2.day02;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
// AppStart 위에 반드시 기입해줘야 한다. @EnableJpaAuditing // 감사/감시(BaseTime 모니터링 필수)
// 여러 엔티티들의 생성/수정 날짜(시간) 자동 주입 + 관례적으로 레코드 생성/수정일 넣음
@Getter //
@MappedSuperclass // 엔티티 상속용도
@EntityListeners( AuditingEntityListener.class ) // 해당 엔티티를 자동 감시 적용
public class BaseTime {

    @CreatedDate // 현재 시간을 자동 주입
    private LocalDateTime createDate; // 생성날짜/시간

    @LastModifiedDate // 엔티티 변화 시점의 날ㅉㅏ/시간을 자동으로 주입
    private LocalDateTime updateDate; //
}
