package example2.day02;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;

    // 1. 등록
    public GoodsDto goodsSave( GoodsDto goodsDto ){// 저장할 dto를 매개변수로 받는다.
        GoodsEntity goodsEntity = goodsDto.toEntity(); //저장할 dto를 entity로 반환한다.
        GoodsEntity goodsSaveEntity = goodsRepository.save( goodsEntity ); // .save()이용한 엔티티 영속화(저장)
        // 만약 pk가 생성되었으면 생성한 엔티티를 dto로 변환하여 반환
        if( goodsSaveEntity.getGno() >=0 ){ return goodsSaveEntity.toDto(); }
        return goodsDto;
    }

    // 2. 전체조회
    public List<GoodsDto> goodsAll(){
        List<GoodsEntity> goodsEntityList = goodsRepository.findAll(); // 1. 모든 엔티티를 조회한다.
        // 2. 모든 엔티티를 DTO로 변환한다.
        // 방법2 : 스트림API , java : 리스트명.stream().map() vs js : 리스트명.map()
        List<GoodsDto> goodsDtoList = goodsRepository.findAll()
                .stream().map( GoodsEntity :: toDto ) // 엔티티 하나씩 dto로 메소드 호출
                .collect( Collectors.toList() ); // map에서 반환된 값들을 리스트로 반환

        //// 방법1 :
        //List<GoodsDto> goodsDtoList = new ArrayList<>();
        //        for( int i = 0 ; i < goodsEntityList.size() ; i++ ){
        //GoodsEntity entity = goodsEntityList.get( i );  // i번째 엔티티 꺼내서
        //            goodsDtoList.add( entity.toDto() ); // 엔티티를 dto로 변환후 리스트에 저장
        //        }
        //// 방법2 : 스트림API
        //List<GoodsDto> goodsDtoList = goodsEntityList
        //        .stream().map( GoodsEntity :: toDto )
        //        .collect( Collectors.toList() );

        return goodsDtoList; // DTO LIST 반환한다.
    }

    // 3. 개별조회
    public GoodsDto goodsGet( int gno ){
        Optional<GoodsEntity> optionalGoods = goodsRepository.findById( gno );
        if ( optionalGoods.isPresent() ){// 조회 결과가 있다면 (null이 아니면)
            GoodsEntity entity = optionalGoods.get(); // 엔티티 꺼내기
            return entity.toDto(); // 엔티티를 DTO로 반환
        }
        return null;
    }

    // 4. 개별삭제
    public boolean goodsDelete(int gno){
        if( goodsRepository.existsById(gno)){ // existsById( gno ) pk값이 존재하면 true, 아니면 false
            goodsRepository.deleteById( gno );
            return true;
        }
        return false;
    }

    // 5. 개별수정 @Transactional
    public GoodsDto goodsUpdate( GoodsDto goodsDto ){
        // 1. 수정할 엔티티를 조회한다.
        Optional<GoodsEntity> optional = goodsRepository.findById( goodsDto.getGno()); // 수정할 엔티티를 조회한다.
        if(optional.isPresent()){
            GoodsEntity entity = optional.get();// 영속화된 엔티티 꺼내기
            entity.setGname( goodsDto.getGname());
            entity.setGdesc( goodsDto.getGdesc());
            entity.setGprice( goodsDto.getGprice());
            return entity.toDto(); // 수정된 엔티티를 dto 변환 후 반환
        }
        return goodsDto; //
    }
}
