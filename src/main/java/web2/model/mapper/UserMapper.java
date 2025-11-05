package web2.model.mapper;

import org.apache.catalina.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import web2.model.dto.UserDto;

@Mapper
public interface UserMapper {

    // 1. 회원가입( pk 반환 )
    @Insert("insert into users(uid, upwd, uname, uphone, urole) values( #{uid}, #{upwd}, #{uname}, #{uphone}, #{urole} )")
    @Options( useGeneratedKeys = true, keyProperty = "uno")// insert 성공시 매개변수에 생성된 pk값을 주입한다.
    public int signup(UserDto userDto);

    // 2. 로그인
    @Select("select * from users where uid = #{ uid } ")
    UserDto login( String uid );

    // 3. 내 정보 조회
    @Select("select uno,  uid, uname,uphone, urole, udate from users where uid = #{ uid } ")
    UserDto myInfo( String uid );



}// interface end
