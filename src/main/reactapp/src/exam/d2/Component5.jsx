
export default function Component5( props ){

    const items = [ '사과', '바나나','딸기' ]

    const newItem1 = items.forEach( (item) => { console.log(item); return item; }  ) // forEach return 없다.
    console.log( newItem1 );

    const newItem2 = items.map( (item) => { console.log(item); return item; }  ) // map return 있다.
    console.log( newItem2 );

    // 함수 : 변수의 변화를 주는 함수 
    const onAdd = () => { items.push("수박"); console.log( items ); }

    // 함수는 1번 호출에 1번 리턴! 즉 1번 리턴된 html(jsx)는 수정불가능....리액트 컴포넌트의 불변성
    // ---> 해결책은 return을 재실행 --> 함수 재 호출 --> 재렌더링(훅_ useState 함수)
    return(<>
        <h3> jsx 반복문 : forEach(리턴 X) vs Map(리턴 O)  </h3>
        {/* map을 사용하여 태그를 리턴으로 반복 돌려준다. */}
        <ul>{ items.map( (item) => { <li>{item}</li>} ) }</ul>

        {/* 리액트는 가상이다. 리액트는 onClick = {  함수명 }  */}
        1. 순수 js방식(작동X) <button onclick="onAdd()"> item 추가 </button> 
        2. 리액트 방식(작동O) <button onClick={ onAdd }> item 추가 </button>

    </>)

}// func e 



function SubCom( props ){

}