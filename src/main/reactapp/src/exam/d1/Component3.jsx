// [1] 컴포넌트의 함수 선언!!!
export default function Component3( props ){
    // js 코드 들어가는 위치
    let name = "유재석"; 
    // return 이후부터는 jsx문법 사용함 ---> 백틱 사용하지 않음
    // 함수의 반환값은 무조건 1개이기 때문에 (<> </>) 의미없는 태그로 1개 처럼 묶어줌 
    // 주석 처리 { /* 주석처리 */ } 컴포넌트는 가상 Dom 이라서 
    // <SumCom1/> 리턴 안에 다른 함수를 불러 올 수가 있다.
    return (<>
            <div>{name}입니다.</div> 
            <div>{13+20}</div>
            <SubCom1 key1 ="value1" key2 ="40"/> {/** 주석 */}
            <SubCom1 key1 ="유재석1" key2 ="60"/>
           </>)
}// func end

// [2] 컴포넌트 함수 선언 2
function SubCom1( props ){
    const obj = { name : "강호동" , age : "50" }
    console.log( obj );
    // props
    console.log(props);
    const{ key1, key2} = props; // props( 배열/객체) 구조 분해(쪼개기)
    // vs const key1 = props.key1; const key2 = props.key2;
    return ( <>
        <h4>  1. {obj.name}님의 나이: { obj.age }</h4>
        <h4>  2. {props.key1}님의 나이: { props.key2}</h4>
        <h4>  3. {key1}님의 나이: { key2 }</h4>
    </> )
}// func end