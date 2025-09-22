import { useEffect, useRef, useState } from "react"

export default function Component11( props ){
    
    // [1] useRef : 렌더링 하지 않고 데이터를 참조하는 훅--> 입력받은 값을 재렌더링 하진 않지만 단순입력값을 받을 경우(회원가입/글쓰기)에는 useRef 사용! 내부 적으로 값을 기억! useRef는 렌더링 대상이 아니다.
    const inputRef = useRef( null )//() 초기값 넣기
    //
    const 등록함수 = () => {
        console.log(inputRef); // inputRef ===> 현재 참조중인 객체정보
        console.log( inputRef.current );    // 4. useRef.current : 참조값 , <input>
        inputRef.current.focus();           // - 포커스( 마우스커서 )
        console.log(inputRef.current.value); // 렌더링을 하지 않고 화면에 입력받은 값을 노출 시킬수는 없다. 화면에 변경된 값을 보일려면 useState사용. 단순 입력시에는 inputRef가 단순!!!!

    }

    // [2] useState vs useRef, 비교/차이점!!!!!!
        //1.
        const [ count, setCount ] = useState(0);
        const countRef = useRef( count ); // 초기값은 넣어 줘야 한다.

        // 2. 카운트가 변경될 때마다 해당 함수 자동 실행(훅 실행!) // 이전 값을 기억 시킬 때, (예시 : 검색, 뒤로가기)
        useEffect( () => { countRef.current = count; }, [count] )// 특벙한 상태가 변경될 때마다 실행되는 함수(훅)  useEffect( ()=>{},[] )

    // [3] 한꺼번에 입력값을 가져올 수 있음, 주의 점은 버튼 타입을 반드시 사용/ 
    // form은 name을 사용!
    const formRef = useRef();
    const 전송함수 = () => {
        console.log( formRef.current ); // 폼 내용을 한번에 가져와서 (name 을 사용해야 자바 dto가 받아줌) 자바(스프링)에게 전송함
        console.log( formRef.current.elements['textData'].value ); // .elements[ 'name 속성값' ]
        console.log( formRef.current.qurrySelector['textData'].value );// 이것도 사용가능은 하지만 위의 것을 리액트엔서는 사용요망, qurrySelector  가운데 대문자
        console.log( document.qurrySelector['textData'].value );// 이것도 사용가능은 하지만 위의 것을 리액트엔서는 사용요망, 

    }

    /** =================================================== jsx =================================================== */
    return(<>
        <h3> useRef 예제1</h3>
        <input ref={ inputRef }/>
        <button onClick={등록함수}>등록</button>

        <h3> useRef 예제2</h3>
        <p> 현재 카운트 : { count } / 이전 : { countRef.current  } </p>
        <button onClick={ (e) => { setCount( count + 1 ) } }>증가</button>

        <h3> useRef 예제3 : 입력폼</h3>
        <form ref={ formRef }>
            <input name ="textData" class ="textData"/>
            <select name="selectData">
                <option>바나나</option>
            </select>
            <textarea name ="text2Data"></textarea>
            <button onClick={ 전송함수 } type="button"> 폼 전송</button>
        </form>

        </>)


}// func end