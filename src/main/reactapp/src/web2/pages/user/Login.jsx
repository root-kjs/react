import { useState } from "react";
import axios from "axios";
export default function Login( props ){
    //
    const [ uid, setUid ] = useState("");
    const [ upwd, setUpwd ] = useState("");

    //  로그인 요청
    const postLogin = async() => {
        try{
            const url ="http://localhost:8080/api/user/login";
            const obj ={uid, upwd }
            const res = await axios.post(url, obj, { withCredentials : true });
            if( res.data != '' ){
                alert("성공")
                location.href= "/"; // 전체 페이지 렌더링 
            }
        }catch(e){
            console.log(e);
            alert("로그인 실패");
        }
    }

    return(<>
    
        <h3>로그인 페이지</h3>
        <form>
            아이디 : <input value={uid} onChange={ (e) => setUid(e.target.value) }></input><br/>
            비밀번호 : <input value={upwd} onChange={ (e) => setUpwd(e.target.value) }></input><br/>
            <button type="button" onClick={ postLogin }>로그인</button><br/>
            <a href="http://localhost:8080/oauth2/authorization/google">구글로그인</a><br/>
            <a href="http://localhost:8080/oauth2/authorization/kakao">카카오로그인</a>
        </form>
    </>)

}