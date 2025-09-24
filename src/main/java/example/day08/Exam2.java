package example.day08;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/** AOP --> Aspect
 * 부가기능을 하나로 모듈화 해서 핵심 비즈니스 로직과 분리
 * 로그(기록), 트랜잭션(SQL 시작과 종료), 보안(인증/권한)
 * 1. 설치 : AOP 라이브러리 // implementation 'org.springframework.boot:spring-boot-starter-aop'
 * */
// 4. AOP 커스텀
@Aspect // AOP 클래스를 AOP 컨테이너 등록
@Component // AOP 클래스를 빈(객체) 컨테이너에 등록
class AopClass{
    /* 4.1 애플리케이션 내 AopService내 모든 메소드가 실행되면 같이 실행
     @Before("execution( 경로와 조건)")
     @Before("execution( 경로와 조건 )")
     * : 모든 리턴 타입의 메소드들
     java 이하경로부터  : 적용할 메소드가 위치한 패키지/파일 경로
     -> 같은패키지 : AopService
     -> 다른패키지 : example.day08.AopService
     .메소드명
     .* : 해당 클래스내 메소드 에 적용
     .enter1 : 해당 클래스내 enter1 메소드 에만 적용
     ( 매개변수 )
     .(..) : 모든 매개변수를 갖는 메소드만 적용
     .enter1( int , boolean ) : 지정한 매개변수를 갖는 메소드만 적용
     */

    @Before("execution( * AopService.*(..) )")
    public void check1(){
        System.out.println("@Before 코로나 공통 열체크!");
    }
    //4.2
    @After("execution( * AopService.*(..))")
    public void check2(){
        System.out.println("@After 잘가...");
    }
    //4.3
    // @After("execution( 리턴타입 )")
    @After( "execution( * example.day08.AopService.enter1(..) )")
    public void check3(){
        System.out.println("//4.3 enter1(특정 메소드)에서만 AOP ");
    }
    //4.4. && args(인자들) --> 매개변수 값들을 연결/바인딩 할 이름 정의해야 함!!!!!!!!!!!!!!!!!
    @Before("execution( boolean example.day08.AopService.enter3( String )) && args( name )")
    public void check4( String name ){
        System.out.println("//4.4 enter3(특정 메소드)에서만 AOP[매개변수] " + name );
    }
    //4.5 @AfterReturning(result)
    @AfterReturning( 
            value = "execution( boolean example.day08.AopService.enter3(..) )",
            returning = "result" // 리턴값을 매핑/바인딩 할 이름 정의
    )
    public void check5( boolean result ){ // *(와일드카드) --> Object
        System.out.println("//4.5 enter3(특정 메소드)에서만 AOP[반환값] " + result );
    }
    // [4-6] 개발자가 직접 메소드를 실행하는 시점
    @Around(" execution( * example.day08.AopService.enter3(..) ) ")
    public Object check6( ProceedingJoinPoint joinPoint ) throws Throwable {
        // 리턴타입을 * 했으므로 모든 자료들을 저장하기 위해 Object
        // 매개변수에는 'ProceedingJoinPoint' 라는 비지니스로직 과 조합
        System.out.println( "[4-6] 객체: " + joinPoint ); // 1. 객체 확인
        System.out.println( "[4-6] 실행할 메소드명 : " + joinPoint.getSignature() );// 2. 해당 AOP 메소드를 실행할 대상(메소드) 확인
        System.out.println( "[4-6] 실행할 메소드의 인자들:"+ Arrays.toString( joinPoint.getArgs() ) );// 3. 실행할 대상(메소드) 매개변수의 인자들(배열) 확인
        // [Ljava.lang.Object;@4197afa0 ---> Arrays.toString( 배열 ) -->  [유재석]
        Object result = // 실행 결과를 반환도 받을 수 있다.
                joinPoint.proceed(); // 4. 실행할 대상 메소드를 직접 실행 ( 실행 시점 커스텀 가능 ), *예외처리*
        System.out.println( "[4-6] 실행후 메소드의 반환값 : "+result );
        return result; // 5. 실행한 대상 메소드의 리턴값을 그대로 리턴 해야한다.
    }

}//class end

//3.Controller ---------------------------------------------
@RestController class AopController{
    @Autowired AopService aopService;
    @GetMapping("/d8/aop")
    public void method( ){
        aopService.enter1();
        aopService.enter2();
        aopService.enter3("유재석");
    }


}// class end

// 2.Service ---------------------------------------------
@Service class AopService{
    public void enter1() {
        System.out.println(" 1.학원 입장");
    }
    public void enter2() {
        System.out.println(" 2.식당 입장");
    }
    public boolean enter3( String name ){
        System.out.println(" 3.헬스장 입장 ");
        return true;
    }
}
// 1.main --------------------------------------------------
@SpringBootApplication
public class Exam2 {
    public static void main(String[] args) {
        SpringApplication.run(Exam2.class);
    }//main end
}// class end