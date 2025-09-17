// -----1depth --> 외부로 호출되는 Component
export default function Component4( props ){ // 관례적으로 대문자로 사용하며 파일명과 같다. 
    //js 영역
    const obj = { name : "유재석",  age : 40 }
    return(<>
     {/** jsx : js + html 혼합!!! 영역 백틱을 사용하지 않고 {}으로 울력 문자열은 그대로*/} 
       <h4>{ obj.name } , { obj.age }</h4>
      
       {/* props 객체 전달!  */}
       {/* 호출한 props 자식의 자식의 요소를 다 호출 한다. 바로 아래 자식만을 호출하였지만 해당 자시이 다른 자식을 호출하였기에 3차 컴포넌트가 불러와진다. */}
       <SubComp key1="value1" key2="value2"></SubComp>   
       <SubComp name="유재석1" age="42"></SubComp>   
       <SubComp name={obj.name} age={obj.age}></SubComp>   
        </>)    
}// f end

// -----2depth
function SubComp( props ){
    console.log(props);
    return(<>
    {/* <h3> {props} </h3> */}
    <SubSubComp value3="value_sub"></SubSubComp>
    </>)
}// f end

// ----3depth ==> 실무에서 3depth 까지 자주 사용함. 
let count2 = 0;
function SubSubComp( props ){
    console.log( props )

    let count = 0; // 지역변수 특성 : 현재 실행중인!! 함수(컴포넌트) 안에서 사용되는 변수
    const onAdd = () => { 
        count++, console.log( `---01.지역변수 : ${count}` );
        count2++, console.log( `---02.전역변수 : ${count2}` );
     }

    return(<>
    {/* Jsx에서는  */}
    <h3>자식의 자식 컴포넌트 </h3>
    <button onClick={ onAdd }>버튼</button>
    <h6> 1.지역변수 : { count } / 2.전역변수 : { count2 } </h6>
    {/* 문자열은 불변성(리터럴)이라 처음 출력되면 변경이 안된다. 언제나 기본이 모든 초석이 되는구나... */}
    </>)
}