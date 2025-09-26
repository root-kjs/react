package example.실습.transactional.model.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.Map;


@Mapper
public interface BookMapper {

    @Update(" update books set stock = stock - 1 where id = #{ book_id} and stock >= 0 ")
    public boolean rent( Map<String, Object> body);

}
