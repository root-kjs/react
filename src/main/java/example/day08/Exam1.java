package example.day08;

/** 메소드 1/2 동일한 메소드를 실행한다면  */
/** AOP(관점 지향 프로그래밍) --> 훅이랑 비슷함(누군가 실행되면 자동 실행됨)  */
class TestService{
    // 메소드 1
    public void enter1(){
        System.out.println("[코로나] 열체크");
        System.out.println("1. 식당 입장");
    }
    // 메소드 2
    public void enter2(){
        System.out.println("[코로나] 열체크");
        System.out.println("2. 학원 입장");
    }
}// class end

/** ============================== main ==================================== */
public class Exam1 {
    public static void main(String[] args) {
        TestService testService = new TestService();
        testService.enter1();
        testService.enter2();
    }// main end
}// class end
