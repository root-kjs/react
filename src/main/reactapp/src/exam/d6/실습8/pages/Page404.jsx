import { Link } from "react-router-dom";

/** 404 컴포넌트 : 존재하지 않는 가상 URL 요청시 노출  */
export default function Page404( props ){

// -------------------------------------- 404 > jsx 영역--------------------------------------
    return(<>
        <h1> 404 페이지 </h1>
        <p> 존재하지 않는 페이지 입니다. </p>
        <Link to ="/">메인페이지로 이동</Link>  
    </>)
}// func end
