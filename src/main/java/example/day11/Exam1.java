package example.day11;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

// 람다 표현식
public class Exam1 {

    public static int plus(int x, int y ){
       return x + y;
    }//func end

    // 인터페이스 : 인터페이스는 생성자가 없어서 객체를 만들 수 없다. 멤버 변수 없다 . 
    interface Calculator{
        int plus( int x, int y );// 추상 메소드
    }
    public static void main(String[] args) {

        // 1. 일반 메소드 호출
        int result = plus( 3, 5 );
        System.out.println("result = " + result);

        // 2. 인터페이스 추상 메소드 호출하는 방법 : 1) 구현체 , 2)익명구현체
            // 1) implements 구현체 만든다 :::  java5
            // 2) 익명구현체(1회성) 끝나는 지점에서 ; 적어준다.  이름이 없는 클래스를 만들어 구현
        Calculator calc = new Calculator() {
            @Override// 오버라이딩 : 상속 또는 추상 메소드를 재구현
            public int plus(int x, int y) {
                return 0;
            }
        };
        
        int value1 = calc.plus(3,5);
        System.out.println("value1 = " + value1);
        
        // 3. 람다식(중급 이상) 으로 익명구현체 만들기 :::  java8 부터 지원 , ( 매개변수 ) -> { 구현부 }
        Calculator calc2 = ( x, y ) -> x + y; // 매개변수랑 구현부를 심플하게 구성
        int value2 = calc2.plus( 3, 5 );
        System.out.println("value2 = " + value2);

        // ★★★★ 4. 람다 표현식을 사용하는 "함수형" 인터페이스들 ★★★★====================================================================
        // 제네릭 : 인스턴스(객체) 생성시, 인스턴스 내 멤버들의 타입 정의, 기본 타입은 불가능.
        // 4-1. Function<T, R> ::: T : ,입력 받아서 R: 반환, apply(T)  메소드 사용 // map
        Function<Integer, Integer> function = x -> x * 2;
        System.out.println("4. function(제네릭) / map 리턴값 필요한 경우 = " + function.apply(3)); // 6

        // 4-2. Supplier<T>, 입력 없이 T 결과를 반환::: .get() 메소드를 사용하여 확인
        Supplier<Double> supplier = () -> Math.random();
        System.out.println("supplier(제네릭) = " + supplier.get());

        // 4-3. Consumer< T > T : 입력받아서 결과가 없음::: .accept( T )  사용 // forEach
        Consumer< String > consumer = ( str ) -> System.out.println("4-2. Consumer(제네릭) str = " + str);
        consumer.accept( " 안녕 " );
        
        // 4-4. Predicate< T > T : 입력 받아서 결과를  true/false반환 ::: test( T ) 로 메소드를 사용한다.  // filter( 조건부 )
        Predicate< Integer > predicate = x -> x % 2 == 0; // 짝수이면 true 반환ㄴ
        System.out.println("4-4 predicate(제네릭)/ filter( 조건부 사용할 경우)= " + predicate. test( 3 ));
        
    }//main end
}//class end