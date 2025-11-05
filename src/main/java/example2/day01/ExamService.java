package example2.day01;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    // 등록
    public ExamEntity post( ExamEntity examEntity ){
        ExamEntity saveEntity = examRepository.save( examEntity ); // insert 자동처리
        // 엔티티 객체 (레코드 = 행) 엔티티(테이블)
        return saveEntity;
    }//`

    // 전체 조회
    public List<ExamEntity> get(){
    List<ExamEntity> examEntityList = examRepository.findAll();
        return examEntityList;
    }

    // 삭제
    public boolean delete( int col1 ){
        examRepository.deleteById( col1 );
        return true;
    }

    // 특정한 엔티티 수정
    public ExamEntity put( ExamEntity examEntity ){
        // save( 수정할 엔티티 ), 만약에 지정한 엔티티에 pk 없으면 생성
        // 만약에 지정한 엔티티에 pk가 있다면 수정
       ExamEntity examEntity1 = examRepository.save( examEntity );
       return examEntity1;
    }

    //
    @Transactional// 2개 이상 사용할때 SQL에서 하나라도 실패하면 다같이 실패 , 수정은 Transactional 반드시 사용해야 한다.
    public ExamEntity put2( ExamEntity examEntity ){
        // 수정할 엔티티 조회
        // findAll() 전체 조회, findById
        // Optional : 자바에서 자주 발생하는 nullPointer 예외를 감싼 포장 클래스
        // null 값에 대한
        Optional<ExamEntity> optionalExam = examRepository.findById( examEntity.getCol1() );
        if(optionalExam.isPresent() ){ // optionalExam == null
            // 만약 결과에 entity 존재하면
            ExamEntity entity = optionalExam.get();
            entity.setCol2( examEntity.getCol2());
            entity.setCol3( examEntity.getCol3());
            return entity;
        }
        return examEntity;
    }//


}//class end
