import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

export default function Header( props ){
    // 1. 로그인된 유저 정보 저장 
    const [ user , setUser ] = useState( null ); 

    // 2. 최초로 컴포넌트 실행시 유저 정보 요청하기
    const getMyInfo = async()=>{
        try{
            const url = "http://localhost:8080/api/user/info"
            const res = await axios.get( url , { withCredentials : true } );  // 쿠키/세션 withCredentials 중요!!!
            // Credentials :  쿠키, Authorization 인증 헤더, TLS client certificates(증명서)를 내포하는 자격 인증 정보
            // 기본적으로 브라우저가 제공하는 요청 API 들은 별도의 옵션 없이 브라우저의 쿠키와 같은 인증과 관련된 데이터를 함부로 요청 데이터에 담지 않도록 되어있다.
            //  이는 응답을 받을때도 마찬가지이다.  따라서 요청과 응답에 쿠키를 허용하고 싶을 경우, 이를 해결하기 위한 옵션이 바로 withCredentials 옵

            setUser( res.data ); // 반환된 유저 정보를 저장 
        }catch( err ){ setUser(null); } // 오류시 null 
    }

    useEffect( () => { getMyInfo(); } , [] );

    
    // 3. 로그아웃 요청하기 
    const getLogout = async()=>{
        try{
            const url = 'http://localhost:8080/api/user/logout'
            const res = await axios.get( url , { withCredentials : true } );
            alert('로그아웃 되었습니다.');
            // navigate("/login"); // 라우터( 클라이언트 사이드 렌더링 )
            location.href="/login" // ( 서버 사이드 렌더링 ) // 로그인/로그아웃에 아주 중요!!!
        }catch( err ){ }
    }

    return(<>
        <div>
            <nav>
                {/* */}
                { user ? <> 로그인 </> : <> 비로그인 </>} {/* 삼항연산자로 상태를 권한에 따른 레이아웃 상태를 구분한다. 주의 <></> 감싸기 */}
                { user ? 
                    <>  
                        { /* 로그인 상태 */}
                        <span to="/login"> 유재석 님 </span>
                        <button onClick={ getLogout} > 로그아웃 </button>
                        <Link to="/user/info"> 마이페이지 </Link>
                        { /* 로그인 상태 이면서 관리자 이면 */}
                        { user.urole == "ADMIN" ? 
                            <><Link to="/admin/dashboard"> 관리자페이지 </Link></> : <></>}
                    </> : 
                    <>
                        { /* 비로그인 상태  */}
                        <Link to="/"> 홈 </Link>
                        <Link to="/login"> 로그인 </Link>
                        <Link to="/signup"> 회원가입 </Link>
                    </>}
            </nav>
        </div>
    </>
    );
}