package example.day11;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Exam2 {

    public static void main(String[] args) {
        // *. 임의 데이터
        List< Integer > numbers = List.of( 1,1,2,3,4,5,6,7,8,9,10 );

        // .stream()  ::::::::::::: 데이터가 다니는 통로 : 자바안에서 다니는 데이터 , 스트림(데이터(매개변수)) --> 중간 연산(예. sort, max, )을 편하게 해준다. -----> 최종 출력
        //  스트림 API(라이브러리) : 스트림 API는 **데이터의 흐름(flow of data)**을 다루는 객체!!!!! 중간 연산(예. sort, max 등 )을 편하게 해준다.

        // 1. stream() + forEach()
        // 매개변수 에 반복변수응  하나씩 대입하여  return이 없는 반복문
        numbers.stream().forEach( x -> System.out.println("1. forEach(x) = " + x));

        // 2. stream() + forEach() + collect()
        // 매개변수에 반복변수응 // .collect(Collectors.toList());
        List< Integer > newNumbers =
        numbers.stream().map( x -> x * 2 ).collect(Collectors.toList());// return을 쓰면 {} 필수!!! 매개변수 1개 일 경우에도 {} 생략 가능!
        System.out.println("newNumbers = " + newNumbers);
        
        // 3. .stream().map().forEach()
        numbers.stream() // 스트림 시작 
                .map( x -> x*2) // 중간 연산
                .forEach( x -> System.out.println("map.forEach(x) = " + x)); // 최종 출력

        // 4. 조건부 
        numbers.stream()
                .filter( x -> x % 2 == 0)
                .forEach( x -> System.out.println("4. 조건부(필터) x = " + x));

        // 5. 정렬
        numbers.stream()
                .sorted()// 기본값(오름차순)
                .sorted(Comparator.reverseOrder())// 내림차순
                .distinct()
                .forEach( x -> System.out.println("5. 정렬(x) = " + x));

        // 6. 중복제거
        List<Integer> disList = numbers.stream().distinct().collect(Collectors.toList());
        System.out.println("6. disList = " + disList);

        // 7. 데이터 출력 제한(limit)
        numbers.stream()
                .limit( 5 )
                .distinct()
                .forEach( x -> System.out.println("7. x = " + x));

        // 8-1. reduce( 초기값, (누적값, 현재값) -> 연산 ) :::: 최종 출력에 속합!
        int sum  =  numbers.stream().reduce(0, (누적값, 현재값 ) -> 누적값 + 현재값 );
        System.out.println("8-1. reduce(sum) = " + sum);

        //8-2. 
        int product = numbers.stream().reduce(1, ( 누적값, 현재값) -> 누적값 + 현재값 );
        System.out.println("8-1. reduce(product)  = " + product);

        //8-3. 매개변수는 아무거나 대입()
        int min = numbers.stream().reduce( Integer.MAX_VALUE, ( 이전값, 현재값) -> 이전값 < 현재값 ? 이전값 : 현재값 );
        // 최소값을 찾을 때는 비교값이 가장 커야 하니 Integer.MAX_VALUE 을 사용한다.
        System.out.println("8-3. reduce(min)  = " + min);


    }// main end
}//class end
