package example.day11;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Exam3 {
    public static void main(String[] args) {
    //* 메소드 레퍼런스 : 람다식에서 사용하는 아이 ===>  이미 정의된 메소드를 참조해서 사용한다. 추상 메소드는 불가
        // 람다는 추상 메소드도 가능하지만 메소드 레퍼런스는 이미 정의된 정적 메소드만 가능
        // 정적메소드란> 객체 없이 사용가능한 메소드 즉. static
        // 멤버멤소드( 통상 그냥 메소드 ) : static 없는
        // 클래스명.정적메소드명() vs 클래스명 :: 메소드명
        // 객체명.메소드명() vs 객체명 :: 메소드명

        // 예 1
        System.out.println( Integer.parseInt("123")); // 문자 타입 --> 숫자 타입 변환
        Function<String, Integer > func = Integer::parseInt;
        System.out.println( func.apply("123") );

        // 예 2
        List<String> names = List.of("유재석" , "강호동" , "신동엽" );
        for( int i = 0 ; i<names.size() ; i++ ){ System.out.println( names.get(i) ); }
        names.stream().forEach( name -> System.out.println( name ) );
        names.stream().forEach( System.out :: println  );


        // 예 3, 정적 메소드를 활용한 forEach 생성자
        names.stream().forEach( name -> new Member(name) ); // 람다 표현식
        names.stream().forEach( Member :: new ); // 메소드 레퍼런스

        List<Member> newMember = names.stream()
                .map( Member :: new ).collect(Collectors.toList());
        System.out.println("newMember = " + newMember);

    }// main end
}//class end

class Member{
    String name;
    // 생성자
    Member( String name ){ this.name = name;  }
}//class end
