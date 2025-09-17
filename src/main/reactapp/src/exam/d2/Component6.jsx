import { useState } from "react";


export default function Component6( props ){
// [1] 리액트에서 상태관리(변수를 대신에 관리) 라이브러리(미리 만들어진 함수) 사용하기
// useState 사용법
// 1. useState 자동완성 import { useState } from "react";
// 2. useState( 초기값 );
// ---> 반환값 :[0] 데이터 / [1]재렌더링 함수

const 상태관리변수 = useState("유재석");
console.log( 상태관리변수 );
console.log( 상태관리변수[0] ); // 유재석 --> 데이터가 들어있음
console.log( 상태관리변수[1] ); // 값이 변경되면 실해되는(재렌더링) 함수가 있음

const 상태변경함수 = () => { 
    상태관리변수[0] = '강호동'; console.log( 상태관리변수[0]);
    상태관리변수[1]("강호동"); // ()함수

 }

    return(<>
        <h3> useState 상태관리 </h3>
        <p> useState 초기값 : { 상태관리변수[0] }</p>
        <p> useState 값 변경 : <button onClick={ 상태변경함수 }>변경</button></p>

    </>)

}// func e