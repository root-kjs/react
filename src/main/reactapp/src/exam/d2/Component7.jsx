import { useState } from "react"

export default function Component7( props ){
    // [0] useState 변수 선언 : 구문 분해 이용한  useState 반환값을 변수화
    // --> const[ 변수명,  set변수명 ] =  ustState( 초기값 );
    // 변수명은 카멜표기법, set변수명은 변수명 앞에 set 붙인다. 

    const [ count , setCount ] = useState(0);

    // 특정한 useState 값을 변경
    // ++count 
    const newValue = count + 1;
    // setXXX (새로운 값) ==> 만일 값이 변경 되었을 때 해당 컴포넌트 재실행(재렌더링) 
    // 훅(갈고리) : 연결 됐다. 특정한 기능이 실행되면 다른 기능도 실행
    // !!!!주의할 점 : 값이 변경되는 전제 조건 // 1 --> 2, 유재석 --> 강호동 , [1,2] --> [1,2,3] // 값이라는 것은 주소값이 변화되는 것임.
    // 데이터(자료/값) : 자료타입(값의 분류) ---> 기본타입(리터럴) vs 참조타입(주소값/객체)
    // 1 ----> 2 : 리터럴(주소값)이 변경된 것임. 기본 타입은 그러함.
    // { name : "유재석"} ---> { name : "유재석", age : 40 } : 주소값은 변경되지 않았음 
    // let a = 1(101번지); 리터럴은 미이 만들어진 주소값, 하나를 공유해서 사용함, static  변수
    // let b = 1(101번지); 
    // let ㅇ

    const [ array , setArray ] = useState(['수박']);
    const arrayAdd = () => { 
        // array.push("사과"); 
        // 주소값 변경 목적 새로운 객체 주소를 만든다. 
        // setArray( array ); // 불가능
        //setArray([...array]) // 스프레드 연산자, 값의 변경이 아닌 주소가 변경됐을 때 작동이 됨
        setArray([...array, "사과"]) // 스프레드 연산자, 값의 변경이 아닌 주소가 변경됐을 때 작동이 됨
    }

    const countAdd = () => { setCount( newValue ) } 

    // 3. 
    const [ data , setData ] = useState("");

    const dataAdd = ( event ) => { //  이벤트결과 
        // onChange가 실행되었을때 결과가 함수의 매개변수로 전달된다.
        console.log( event );
        console.log( event.target ); // onChanege 가 발동한 마크업, document.querrySelector(".").value 안써도 됨
        console.log( event.target.value );
        setData( event.target.value )
    }

    return(<>

    <h3>useState 예제1 : {count}</h3>
    <button onClick={ countAdd }> count 증가 </button>

    <h3>useState 예제2 : { array }</h3>
    <button onClick={ arrayAdd }> 과일 추가 </button>

    <h3>useState 예제3-1 : 인풋값 : 별도로 함수 정의</h3>
    {/* 인풋 초기값을 넣으면 수정이 안됨  <input value="유재석"/> */}
    {/* 입력값은 불변이라 아예 입력이 안됨. */}
    <input value={ data } onChange = { dataAdd }/> 

    <h3>useState 예제3-2 : 바로 함수 정의</h3>
    {/* 인풋 초기값을 넣으면 수정이 안됨  <input value="유재석"/> */}
    {/* 입력값은 불변이라 아예 입력이 안됨. */}
    <input value={ data } onChange = { (e)=>{ setData(e.target.value)} }/> 



    </>)

}// func end