/** 여러개의 슬라이스(전역변수)들을 하나의 store 에서 관리하는 코드 */
import { configureStore } from '@reduxjs/toolkit'
import cartReducer from './cartSlice.js' // Reducer(리듀서) : 상태를 변경하는 함수

/** [1] 스토어 생성하기 : 모든 컴포넌트에서 store 참조하여 store에 저장된 슬라이스를 사용한다. */
// configureStore( { reducer : { 상태명1 : 슬라이스명 , 상태명2 : 슬라이스명 } } )
const store = configureStore({
    reducer:{
         // [2] 내가만든 슬라이드 등록 , cartMenu 상태에 개발자(사용자정의)가 만든 슬라이드를 대입
        cartMenu : cartReducer,
    }

});

/** [3] 스토어를 다른 컴포넌트가 사용할수 있게 export default */
export default store;