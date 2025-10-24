import { useEffect, useState } from "react"
import { Outlet } from "react-router-dom";

export default function RoleRoute( props ){
    // -props란 ? 상위 컴포넌트에서 해당 컴포넌트로 부터 전달받은 속성*들*
    console.log( props );

    // - useState 이란? : 현재 컴포넌트내 상태(값저장) 변수 + 렌더링(새로고침)
    const [ auth , setAuth ] = useState( { isAuth : null , urole : null } )

    // [1] 서버로 부터 권한 요청 
    const checkAuth = async () => {
        try{
            const url = "http://localhost:8080/api/user/check"
            const res = await axios.get( url , { withCredentials : true } );
            // *withCredentials* : httpOnly 쿠키 자동 포함하기 위해서 필수 옵션
            setAuth( res.data );
            console.log( res.data )
        }catch(error){
            setAuth( { isAuth : false , urole : null } );
        }
    }
    // [2] 최초 렌더링 1번 권한 검증, useEffect 이란? 컴포넌트의 생명주기에 따른 특정 작업 실행
    useEffect( ()=>{ checkAuth() } , [] )

    // [3] 
    if( auth.isAuth == null ) return <div> 권한 확인중...</div>

    // [4]
    if( auth.isAuth == false ) return <Navigate to="/login"></Navigate>

    // [5]
    if( !props. roles.includes( auth.urole))  return <Navigate to="/forbidden"/>        
    
    // [6] 3,4,5 통과하면 


/** ============================= .jsx 출력 =============================== */    
    return <Outlet/>;

}// jsx end