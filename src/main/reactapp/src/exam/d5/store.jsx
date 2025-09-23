/** 여러개의 슬라이스들을 스토어에서 관리하는 코드  */
import { configureStore } from "@reduxjs/toolkit";
import  userReducer from "./userSlice.jsx";


/** [1] 스토어 생성 */

// configureStore( { reducer : { 슬라이스명(상태명1) : 슬라이스리듀서함수  } } );
const store = configureStore({
  reducer: {  // reducer : 여러개의 슬라이스들을 묶어서 전달하는 속성
    /** 내가 만든 슬라이스 등록, user 상태에서 개발자(사용자정의) 한 슬라이스를 대입 */
    user: userReducer , // 상태명 ---> 내가 만든 슬라이스명 
  } 
}); 

export default store;