import { useRef } from "react"
import { useDispatch } from "react-redux";
import { login } from "../store/userSlice.js"
import { useNavigate } from "react-router-dom";

/** 메인 페이지 */
export default function LoginPage( props ) {

    /** 상태 변경을 하기 위한 useDispatch 훅 함수 가져오기 */
    const dispatch = useDispatch();

    /** 가상 URL로 페이지 전환하기 위한 navigate 함수 가져오기 */
    const navigate = useNavigate()
   
    /** useRef 함수 정의 */
    const formRef = useRef( null );

    /**  로그인 함수 : axios 생략  */
    const formLogin = async() => {
        
        const uid = formRef.current.elements['uid'].value;
        const upwd = formRef.current.elements['upwd'].value;
        const obj = { uid : "admin", name : "관리자" }; // 액션에 보낼 샘플 데이터
        // 로그인 성공/실패 여부 결과 조건식
        if( uid == 'admin' && upwd == '1234' ){
            alert('로그인 성공! 메인 페이지로 이동합니다.');
            
            dispatch( login( obj ) ); // login 함수 액션 요청하여 상태변경한다. ( 전역변수의 상태 변경 )
            navigate('/'); // 라우터 방식 : 로그인 후 메인 페이지로 이동(클라이언트 사이드 렌더링)
        }else{
            alert('로그인 실패! 아이디 또는 비밀번호를 확인하세요!');
            formRef.current.elements['uid'].value = '';
            formRef.current.elements['upwd'].value = '';
            formRef.current.elements['uid'].focus();
            return;
            // location.href = '/login'; // 로그인 실패 후 로그인 페이지로 이동(서버사이드 렌더링)  
        }

    }// f end

    // --------------------------------------jsx 영역--------------------------------------
   return (<>  
        <h3>로그인페이지</h3>
        <form ref={ formRef }>
            <input name="uid" placeholder="아이디" />
            <input name="upwd" placeholder="비밀번호"/>
            <button onClick={ formLogin } type="button">로그인</button>
        </form>
    </>)

}//func end