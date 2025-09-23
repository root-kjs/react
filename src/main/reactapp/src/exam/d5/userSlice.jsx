import { createSlice } from "@reduxjs/toolkit";

/** [2] 전역변수 의 초기값, 로그인 여부를 저장하는 상태 true(로그인 상태)/false(로그인 안됨)*/ 
const inintialState = { isAuthenticated: false }; // 전역변수 초기값 객체 설정  , isAuthenqticated로그인상태 : 인증된 상태(로그인)
//

/** [3] 상태를 변경하는 리듀서 함수들을 정의 */
//createSlice( { name : slice이름, 초기값, reducers : { 액션함수명 :  (state)=>{}   } } );

/** 리덕스에서 슬라이스란: 저장소(store)에 저장되는 일부분의 값의 이름  */ 
const userSlice = createSlice({ // 전역변수(userSlice) --> 전역변수명 1 --> 
  name: "user", // 슬라이스의 이름 (저장소에 저장될 때의 key값)
  initialState: inintialState, // 정으한 객체로 초기값을 설정, 추후에 다양하게 저장 다능, 단 보안 민감 정보 제외
  reducers: {// 상태(slice)를 변경하는 함수들을 모아놓은 객체
    login: (state) => { state.isAuthenticated = true; }, 
    logout: (state) => { state.isAuthenticated = false; }
  }
});

/** [4] 내보내기 */
// export default 내보내기는 파일 내 1개만 가능, store에 import 할 수 있게
export default userSlice.reducer; // 리듀서 함수를 내보냄

/**  */
export const { login, logout } = userSlice.actions; // 액션 생성함수를 내보냄