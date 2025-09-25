/** [ 리덕스 ]
    1. 정의 : 전역 상태 관리 라이브러리 , 서로 다른 컴포넌트 간의 효율적인 상태(값) 공유 한다.
    2. 목적 : 상태들을 저장하는 store(저장소) 만들고 서로 다른 컴포넌트 간의 일관적인 상태 유지한다(전역변수)
    3. 활용처 : 로그인상태(비밀번호X), UI테마 , 로그인유지상태 등등
        1) 전역 상태(값) 필요할때
        2) 여러개의 컴포넌트에서 동일한 변수를 공유할때
        3) 컴포넌트 간 props 전달이 복잡해질 때
   [1] 리덕스 설치 : reactapp 폴더 오른쪽 클릭 --> 터미널열기  */
// [리액트 종료된 상태]
// npm install @reduxjs/toolkit
// npm install react-redux
// [리액트 재실행] npm run dev 

/** [2] 전역변수의 초기값 , '장바구니 담기 여부'를 저장하는 상태 true:추가됨 , false:추가안됨  */
import { createSlice } from '@reduxjs/toolkit';

//const initialState = { isAdded: false }
const initialState = { isAdded: false }

/** [3] 상태를 변경하는 리듀서 함수들을 정의 */ 
// createSlice( { name : 'slice이름' , 초기값 , reducers : { 액션함수명 : (state)=>{ } } } );
const cartSlice = createSlice({
    name: "cartMenu", // 저장소에 저장될 때의 key값 : 전역변수
    initialState,
    reducers : {
        addCart : ( state ) => { state.isAdded = true; }, // 액션 통해 상태변경을 하면 *리렌더링* 된다.
        cancelCart : (state ) => { state.isAdded = false; } 
    },
});

/** [4] 내보내기  */
// [4-1] export default 내보내기는 파일내 1개만 가능 , store에 리듀서를 import 할수 있게
export default cartSlice.reducer 

// [4-2] export 내보내기는 여러번 가능 , // addCart,cancelCart액션을 다른 컴포넌트에 import 할수 있게
export const { addCart , cancelCart } = cartSlice.actions; 