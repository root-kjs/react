/** [1] 내가 만든 저장소() */
import store from "./store.jsx";

/** [1] Store 사용할 곳에 store 공급해주기 */
import { useDispatch, useSelector } from "react-redux";

import { login, logout } from "./userSlice.jsx";



export default function Compslice13() {

    /** [3] store에 저장된 상태 가져오기 */
    const dispatch = useDispatch(); // store에 액션을 전달하는 함수
    // dispatch( 액션생성함수() ); // 액션 생성함수를 호출하여 액션을 dispatch 함수의 매개변수로 전달한다.
    // dispatch( login() ); // 로그인 액션
    // dispatch( logout() ); // 로그아웃 액션
    const { isAuthenticated } = useSelector( (state) => state.user ); // store에 저장된 user 슬라이스의 상태 가져오기
    console.log( isAuthenticated ); // false


    /** [4] 로그인 처리 */
    const loginHandle = () => {
        dispatch( login() ); // dispatch를 이용한 login 액션 요청한다. 
    }
    
     /** [5] 로그아웃 처리 */
    const logoutHandle = () => {
        dispatch( logout() ); // dispatch를 이용한 logout 액션 요청한다. 
    }

    return (<>
        <Provider store= { store } >
            <h3>리덕스 예제1</h3>
            <button onClick={ loginHandle } >로그인</button>
            <button onClick={ logoutHandle } >로그아웃</button>
            <div>로그인 상태 : { isAuthenticated == true
                ? 
                <div>로그인 상태입니다.
                    <button onClick={ logoutHandle } >로그아웃</button>
                </div>
                : 
                <div>로그아웃 상태입니다.
                    <button onClick={ loginHandle } >로그인</button>
                </div>
                }
            </div>
        </Provider>
    </>)

}// func end