package example2.day01;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 지정한 엔티티(테이블)을 조작하는 인터페이스
public interface ExamRepository extends JpaRepository< ExamEntity, Integer > {

}// i end

