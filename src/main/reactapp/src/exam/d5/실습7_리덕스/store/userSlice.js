/** 슬라이스 : 상태(state), */
import { createSlice } from "@reduxjs/toolkit";

/** 1. 상태의 초기값 정의 , 로그인 여부 */
const initialState = { isAuthenticated : false, userInfo : null } 

/** 2. 슬라이스 정의 */
const userSlice = createSlice({  // 슬라이스명 
    name : "user", // 슬라이스 상태명
    initialState ,
    reducers : { // 여러 걍의 상태 변경 함수 정의한 곳 { 함수명 : ( state ) => {}  }
        login : ( state, action  )=>{ 
            state.isAuthenticated = true;
            /** action 할때 매개변수의 payload 안에 값이 들어있다. */
            // dispatch( login('안녕') ) --> payload = "안녕"
            console.log( action.payload )
            state.userInfo = action.payload;  // payload--> 탑재(전달된 값 )

         }, // state, 매개변수들
        logout : ( state ) => { 
            state.isAuthenticated = false; // 로그인 여부 false 변경 
            state.userInfo = null;         // 로그인 회원 정보 null로 변경 

        }
    }
})
/** 3. store에 저장할 수 있게 export  */
export default userSlice.reducer; // reducers ---> s 빼고 적기. 현재 정의한 리듀서(reducer)들을 store 등록 예정

/** 4. 다른 컴포넌트에서 액션이 가능하도록  */
export const { login, logout } = userSlice.actions;