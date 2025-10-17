package example.day17;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    // 레디스 관련 설정 메소드
    @Bean
    public RedisTemplate<String, Object> redisTemplate( RedisConnectionFactory redisConnectionFactory){
        //  1. 레디스 템플릿 객체 생성
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        // 2. 생성한
        template.setConnectionFactory( redisConnectionFactory );
        // 3. 생성한 템플릿은 key값을 String 타입으로 직렬화(변환) 한다
        template.setKeySerializer( new StringRedisSerializer());
        // 4. 생성한 템플릿은 value값을  json/dto 타입으로 직렬화
        // 많이 사용하는 json 타입으로
        template.setValueSerializer( new GenericJackson2JsonRedisSerializer());
        // 직렬화 : 레디스에 저장된 데이터를 자바 타입으로 변환하는 과정 , 복원화(역직렬화)
        // 역직렬화 : 복원



        return template;
    }// func end
}// class end
