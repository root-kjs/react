import { BrowserRouter, Routes, Route, Link, useNavigate } from 'react-router-dom';
import './Task6.css';
import { useRef } from 'react';

/** [00] 라우터로 사용할 최초 컴포넌트 */ 
export default function Task6( props) {

    /** ============================== jsx ============================== */
    return (<>
    <BrowserRouter>
        <div class="container">
            {/* 1. 좌측 > 메뉴 영역 */}
            <ul class="lnb">
                <li><Link to="/">홈</Link></li>
                <li><Link to="signup">회원가입</Link></li>
                <li><Link to="login">로그인</Link></li>
            </ul>
            
            {/* 2. 우측 > 본문 > 라우터 렌더링 영역 */}
            <div class="contents_wrap">
                <Routes>
                    <Route path="/" element={<Home />}></Route>
                    <Route path="/signup" element={<Signup />}></Route>
                    <Route path="/login" element={<Login />}></Route>
                    <Route path="*" element={<Page404 />}></Route>
                </Routes>
            </div>
        </div>
    </BrowserRouter>
    </>)
}//func end

/** [01] 홈 컴포넌트 */
function Home( props ){
    return(<>
        <h3>메인 페이지(Home)</h3>
        <p>리액트 :  BrowserRouter 활용하자!!!</p>
    </>)
}// func end

/** [02] 회원가입 컴포넌트 */
function Signup( props ){

    // 1. 입력상자들을 참조하는 useRef() 훅 함수 사용
    //const formRef = useRef();
    // console.log( formRef.current );
    // console.log( formRef.current.elements['userid'].value );
    // console.log( formRef.current.elements['pwd'].value );
    // const userid = formRef.current.elements['userid'].value;
    // const pwd = formRef.current.elements['pwd'].value;
    const idRef = useRef( null ); // idRef: 참조 객체 / idRef.current : 참조값, 참조객체가 참조중인 값(기억중인 값)
    //console.log( idRef.current.value ); // 아이디 입력값
    const pwdRef = useRef( null );
    //console.log( pwdRef.current.value ); // 비밀번호 입력값
    const navigate = useNavigate(); // ********************* 훅은 컴포넌트의 최상위 레벨에서만 호출하고, 
    // 조건문, 반복문, 중첩된 함수 안에서는 사용하지 않도록 코드를 수정해야 합니다.************************
    
    /**  회원가입 함수 */
    const formSignup = async() => {
        const userid = idRef.current.value;
        const pwd = pwdRef.current.value;   
        console.log( userid, pwd );
        // * ajax 통신으로 서버(스프링) 에게 통신 했다 가정하에
        // 라우터 방식이 아닌 고전적인 HTML 방식으로 페이지가 새로고침(깜빡거림) 되는 방식
        // location.href = '/login'; // 회원가입 후 로그인 페이지로 이동(서버사이드 렌더링)
        alert('회원가입 성공! 로그인 페이지로 이동합니다.');
        navigate('/login'); // 라우터 방식 : 회원가입 후 로그인 페이지로 이동(클라이언트 사이드 렌더링)
    }// f end

    /** ------------------------------ 회원가입 jsx ------------------------- */
    return(<>
        <h3>회원가입 페이지</h3>
        {/* // ref={ formRef } */}
        {/* <form > */}
            <input name="userid" ref={ idRef } placeholder="아이디"  />
            <input name="pwd" ref={ pwdRef } type="password" placeholder="비밀번호"/>
            <button type="button" onClick={ formSignup }>회원가입</button>
        {/* </form> */}
    </>)
}// func end

/** [03] 로그인 컴포넌트  */
function Login( props ){

    const formRef = useRef( null );
    console.log( formRef.current );// 입력상자 전체를 참조하기 때문에 value값이 없음/콘솔로그에서서도 사용해봤자 안됨...

    /**  로그인 함수 */
    const formLogin = () => {
        const uid = formRef.current.elements['uid'].value;
        const upwd = formRef.current.elements['upwd'].value;
        // console.log( uid, upwd );
        // ajax 통신으로 서버(스프링) 에게 통신 했다 가정하에 
        // 로그인 성공/실패 여부 결과 조건식
        if( uid == 'admin' && upwd == '1234' ){
            alert('로그인 성공! 메인 페이지로 이동합니다.');
            useNavigate('/'); // 라우터 방식 : 로그인 후 메인 페이지로 이동(클라이언트 사이드 렌더링)
        }else{
            alert('로그인 실패! 아이디 또는 비밀번호를 확인하세요!');
            formRef.current.elements['uid'].value = '';
            formRef.current.elements['upwd'].value = '';
            formRef.current.elements['uid'].focus();
            return;
            // location.href = '/login'; // 로그인 실패 후 로그인 페이지로 이동(서버사이드 렌더링)  
        }
    }// f end

    /** ------------------------------ 로그인 jsx ------------------------- */
    return(<>
        <h3>로그인 페이지</h3>
        <form ref={ formRef }>
            <input name="uid" placeholder="아이디"  />
            <input name="upwd" type="password" placeholder="비밀번호"/>
            <button type="button" onClick={formLogin}>로그인</button>
        </form> 
    </>)
}// func end

/** [04] 404페이지 컴포넌트 */
function Page404( props ){
    const navigate = useNavigate(); // 훅은 컴포넌트의 최상위 레벨에서만 호출하고, 조건문, 반복문, 중첩된 함수 안에서는 사용하지 않도록 코드를 수정해야 합니다.
    /** ------------------------------ 404페이지 jsx ------------------------- */
    return(<>
        <h3>404 페이지</h3>
        <p>존재하지 않는 페이지 입니다.</p>
        <button onClick={ () => { navigate('/') } }>홈으로</button>
    </>)
}// func end