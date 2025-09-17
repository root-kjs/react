import { useState } from "react"

export default function Task3( props ){

    // 1. 인풋 텍스트 입력
    const [ data, setData ] = useState( "코카콜라" );

    const addData = (e) =>{
        setData( e.target.value );
    }

    // 2. 수량 증가  ==> 현재 수량을 관리하는 useState( 초기값 );
    const [ count, setCount ] = useState(0);

    const addCount = () => {   // 증가함수 
        setCount( count + 1 ); // const 상수를 사용했기때문에 ++ 말고 + 1을 사용
    }
      const minusCount = () => { // 감소함수
        setCount( count - 1 );
    }

    // 3. html 출력
    return(<>
    
        {/* <h3>제품명: <input value = { data } onChange = { addData }/></h3> */}
        <h3>제품명: <input value = { data } onChange = { e => setData(e.target.value) }/></h3>
        <h3>현재수량 : { count }</h3>
        <button onClick={ minusCount }>감소</button>
        <button onClick={ addCount }>증가</button>
    
    </>)
}// func end