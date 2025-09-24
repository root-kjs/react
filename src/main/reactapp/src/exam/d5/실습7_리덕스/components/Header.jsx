import { Link, useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { logout } from "../store/userSlice.js";


/** 메인 페이지 */
export default function Header( props ) {
   
    /** 1. store 저장된 상태 가져오기 */
    const { isAuthenticated, userInfo } = useSelector( ( state ) =>  state.user ); // 구문 분해
    // vs
    // const user = useSelector( (state) => state.user  )

    console.log( isAuthenticated );
    console.log( userInfo );

    const dispatch = useDispatch();
    const navigate = useNavigate();

    /** 로그아웃 함수 */
    const onLogOut =async() => {
        alert(" 로그아웃 성공");
        dispatch( logout() ); 
        navigate("/");
    }

    // 새로고침 js 리덕스 문제 해결 
    // (방법1). 서버 사이드 렌더링 axious를 이용한 서버 재요청 : useEffect( () => {} , [] ); 

    // (방법2). 퍼시스턴스 : 프론트저장소 --> 브라우저 로컬스토리지, 세션스토리지 이용



    // --------------------------------------jsx 영역--------------------------------------
    return (<>
        <div>
            <h3>해더 메뉴</h3>
            <ul>
                <li><Link to='/'>홈</Link></li>
               
                    { isAuthenticated == false
                    ?
                     <li><Link to='/login'>로그인</Link> </li>
                    :
                     <>
                     <li><Link to='/profile'>프로필</Link>  </li>
                     {/*  */}
                     <li>{ userInfo.name }  <button onClick={ onLogOut }>로그아웃</button></li>
                     </>
                    }
            </ul>
        </div>
    </>)

}//func end