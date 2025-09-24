/** 스토어 : 여러개의 상태를 보관하는 저장소, 1개 존재해야 한다. */
// 김현수 선생님 엑셀 참고 : https://docs.google.com/spreadsheets/d/1QEJ-DvLa22uUQucMeCZDUH9VH5PPZr5-vzLx5UF8Qig/edit?gid=42838050#gid=42838050
/** 퍼시스턴스 : 로컬/세션스토리지에 상태 유지하는 방법 
 * 1. 설치 : npm i redux-persist
 * 2. 스토리지 설정: 
 */


import { configureStore } from "@reduxjs/toolkit";
import userSlice from "./userSlice.js" ;

// 4) persist
import storage from 'redux-persist/lib/storage'
//import storageSession from 'redux-persist/lib/storage/session' // session 사용

const persistConfig = { key : 'user' , storage  } // redux-persist 설정 -->  storare(localStorage) 사용하겠다

// 5) 리듀서에 persist 설정
//const persistedConfig = persistReducer(옵션, 설정리듀서);
import { persistStore, persistReducer } from 'redux-persist'
const persistedReducer = persistReducer( persistConfig, userSlice ); // userSlice 상태를 user라는 이름으로 storare 저장을 하겠다.

/** 1. 스토어 만들기(모든 전역변수가 관리 되는 곳이라 1개만 있음 ) */
const store = configureStore({
    reducer : { //  
        /**  2. 내가 만든 슬라이스(상태)를 등록한다. */
        // user : userSlice     // persist퍼시스턴스 적용 전
        user : persistedReducer // persist퍼시스턴스 적용 후
    }
})

export default store; // 맨 아래 정의해야 함.
// 7) 
export const persistor = persistStore( store );