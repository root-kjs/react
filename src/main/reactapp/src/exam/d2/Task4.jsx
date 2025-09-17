import { useState } from "react"

export default function Task4( props ){

    // 1. 배열 선언
    const users = [ 
        { name:"신동엽", tel:"010-1111-1111", age:"50"}, 
        { name:"유재석", tel:"010-2222-2222", age:"60"},
        { name:"강호동", tel:"010-3333-3333", age:"70"} 
     ]

    // 2. 인풋 입력값
    const[ name , setName ] = useState("");
    const[ tel, setTel ] = useState(""); 
    const[ age, setAge ] = useState("");

    // 3. 배열을 상태관리 초기값으로 선언
    const[ array, setArray ] = useState( users );

    // 4. 새로운 유저 등록 함수
    const addUser = () => {
        // 1) 새로운 유저 객체 생성
        const newUser ={
            name : name, 
            tel : tel,
            age : age
        };
        // 2) 새로운 유저 객체를 배열에 스프레드? 넣어줌
        setArray([...array, newUser]);
        setName('');
        setTel('');
        setAge('');

    } 

     // 5. 기존 유저 삭제
    const deleteUser = ( deleteTel ) => {
        const updateArray = array.filter( user => user.tel !== deleteTel ); 
        setArray( updateArray ); 
    } 

    // 5. html 출력
    return(<>

        <h3>전화번호부</h3>
        <input value={ name } onChange ={ e => setName(e.target.value)} placeholder="성명"/>
        <input value={ tel } onChange ={ e => setTel(e.target.value)} placeholder="연락처"/>
        <input value={ age } onChange ={ e => setAge(e.target.value)} placeholder="나이"/>
        <button onClick={ addUser }>등록</button>
        
        {/* map 반복문 */}
        <ul>
            { array.map( (user) => { return <li> 
                성명 : { user.name } 연락처: { user.tel } 나이:{ user.age } 
                <button onClick={()=>deleteUser(user.tel)}>삭제</button>
                </li> 
            })} 
        </ul>
    </>)
}//func end

// ★filter() 기본 문법
// array.filter(callback(element, index, array))

// const numbers = [5, 12, 8, 130, 44];
// const newNumbers = numbers.filter(number => number > 10);
// console.log(newNumbers); // [12, 130, 44]
// -----------------------------------------------------------------------------------------------------------------------------------
// ★ 리액트
// ★ 5. 기존 유저 삭제 함수
//     const deleteUser = (telToDelete) => {
//         // 전화번호(telToDelete)를 기준으로 필터링하여 해당 유저를 제거
//         const updatedArray = array.filter(user => user.tel !== telToDelete);
//         setArray(updatedArray);
//     };


// X 잘못된 예시 (버튼 클릭 전에도 함수가 실행됨)
// <button onClick={deleteUser(user.tel)}>삭제</button>

// 위와 같이 deleteUser(user.tel)을 직접 호출하면, 버튼을 클릭하지 않아도 deleteUser가 컴포넌트가 렌더링될 때 바로 실행됩니다. 
// 그 결과, 전화번호를 삭제하려고 할 때 문제가 발생합니다.

// O 올바른 예시 (클릭했을 때만 실행됨)
// <button onClick={() => deleteUser(user.tel)}>삭제</button>
// -----------------------------------------------------------------------------------------------------------------------------------