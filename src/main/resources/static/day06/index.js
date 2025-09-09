// 
console.log( 'index.js open');

// [1] 변수 와 상수 선언 키워드
let count = 10;     // 변수의 선언과 초기화
count = 20;         // 변수값 수정 가능
const count2 = 20;  // 상수의 선언과 초기화
// count2 = 20;     // 상수값 수정 불가능
// 변수/상수 에는 객체/함수/배열 를 담기 가능
const obj = { name : "유재석" }; 
const mehtod = ( )=> { } 
const arr = [ "유재석" , "강호동" ]
// * var 키워드 : let 키워드 없었던 시절에 사용된 키워드
var count3 = 30;
var count3 = 40; // var 키워드는 중복 변수명을 허용한다. 식별이 어렵다.

// [2] 백틱 : 문자열 템플릿 , 문자열내 JS표현식을 연결할때 사용한다. 
console.log( `Hello : ${ count }` );
let html = ``;
html += `<div> Hello : ${ count2 } <div> `
console.log( html );

// [3-1] 조건문1 : IF
const point = 85;
if( point >= 90 ){ console.log( "A학점"); }
else if( point >=80 ){ console.log( "B학점"); }
else{ console.log("C학점"); }
// [3-2] 조건문2 : 삼항연산자 , 조건 ? 참 : 거짓 , 간단한조건 에서 주로 사용됨
console.log( point >= 90 ? "A학점" : point >= 80 ? "B학점" : "C학점" );
// [3-3] 조건문3 : 단축평가 , 조건 && 참이면결과 , 조건 || 거짓이면결과
console.log( point >= 90 && "A학점" ); // 만약에 참이면 'A학점' 아니면 false 
console.log( point >= 90 || "A학점" ); // 만약에 참이면 true 아니면 "A학점"

// [4] 반복문 : 
const array = [ 10 , 20 , 30 , 40 , 50 ]
for( let index = 0 ; index < array.length ; index++ ){ console.log( array[index] ) }
for( let index in array ){ console.log( array[index] ) }
for( let value of array ){ console.log( value ) }
//  forEach
array.forEach( (value) => { console.log( value ); } )
// ** map ** : forEach 다르게 return 가능하다.
let newArray = array.map( (value) => { console.log( value ); return value;  } )
// ** filter ** : 조건부 return 가능하다.  
let newArray2 = array.filter( (value) => { console.log( value ); return value > 20; })




















































