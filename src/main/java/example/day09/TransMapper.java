package example.day09;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Mapper

public interface TransMapper {
    // 1. 정상 Insert
    @Insert("insert into trans( name ) values( #{name} )")
    public boolean trans1(String name);

    // 1. 비정상 Insert, 오타가 나도 스프링은 돌아감.
    // 그래서 서비스단에서 @Transactional  사용함.
    // 모 아니면 도 그래서 다 되든가 안되든가 그래서 키값 공백이 생김.
    @Insert( "insert into trans( name ) 오타( #{name} ")
    public boolean trans2( String name );

    // (2) update 입금, 더하기
    @Update( "update trans set money = money + #{money} " +
            " where name = #{name}")
    public boolean deposit( String name , int money );
    // (2) update 입금, 빼기
    @Update( "update trans set money = money - #{money} " +
            " where name = #{name} and money >= #{ money } ")
    public boolean withdraw( String name , int money );


}// interface end
