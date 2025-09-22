import { BrowserRouter, Routes,  Route, Link, useSearchParams, useParams, useNavigate } from 'react-router-dom';
/** [1] 메인페이지 컴포넌트 */
function Home( props ){
    return(<>
        메인페이지
    </>)
}// func end

/** [2] 소개페이지 컴포넌트 */ 
function About( props ){
    return(<>
        소개페이지
    </>)
}// func end

/** [3] 마이페이지 컴포넌트 > 쿼리스트링 매개변수 전달 */
function MyPage( props ){
    // 기존 바닐라 js 쿼리스트링 매개변수 받기
    //const name = new URL( location.href ).searchParams.get('name');
    //const age = new URL( location.href ).searchParams.get('age');
    // 리액트 라우터 v6 쿼리스트링 매개변수 받기
    const [ searchParams ] = useSearchParams();// 구조 분해
    const name = searchParams.get('name');
    const age = searchParams.get('age');

    return(<>
        <h3>마이페이지</h3>
        <p>이름 : { name } / 나이 : { age } </p>
    </>)
}// func end

/**  [4] 제품 소개 페이지 :  path방식 (매개변수 전달) */
function Product( props ){
    // 기존 바닐라 js 쿼리스트링 매개변수 받기
    // const path = new URL( location.href ).pathname; // /product/ 코카콜라/1000 vs 여러개는 쿼리스트링을 많이 사용한다. 
    // const pno = path.substring( path.lastIndexOf('/') + 1 ); // 1004

    const{ pname, price } = useParams(); // 구조분해할당 const pname = useParams().pname; const price = useParams().price;

    return(<>
        <h3>제품소개 페이지</h3>
        <p>제품명 : { pname } / 제품가격 : { price } </p>
    </>)    
}//func end

/** [5] 404 컴포넌트 : 존재하지 않는 가상 URL 요청시 노출  */
function Page404( props ){

    // 5-1) useNavigate() 훅 함수 사용 --> 반환값 저장
    const navigate = useNavigate(); // 함수형 컴포넌트에서 사용가능, 훅은 무조건 함수형 컴포넌트에서만 사용가능

    const 이동함수 = () => {
        // 1) HTML 페이지 전환 방식
        // location.href = '/'; // '경로', 메인페이지로 이동 : 서버사이드 렌더링
        // (새로고침됨 --> 화면이 깜빡거림--> 서버통신이 필요한 경우나(로그인 세션 비번이나 데이터를 다시 가져오는 경우))
        
        //  2) 리액트 라우터 페이지 전환 방식 ******************************************************************************
        navigate( '/' ); // '경로', 메인페이지로 이동(새로고침 없음), 클라이언트사이드 렌더링
    }

    

    return(<>
        <h3> 404 페이지 </h3>
        <p> 존재하지 않는 페이지 입니다. </p>
        <a href ="/">[서버사이드 렌더링]메인페이지로 이동(html방식/보안측면/보안 관련은 서버에서 관리해야 하기때문에 a링크를 사용한다!!!!!!/)</a><br/>
        <Link to ="/">[클라이언트 사이드 렌더링]메인페이지로 이동(리액트 라우터 방식/)</Link>  
        <button onClick={ 이동함수 }>[navigate 함수] 홈으로3  </button>
        
    </>)
}// func end


// [****] 라우터 : 하나의 컴포넌트가 여러 컴포넌를 연결하는 구조 : 가상의 url 만들기
// 라이브러리 설치 : 리액트 서버 종료 후에 터미널에서 설치 한 후 다시 리액트 실행 : npm i react-router-dom 
export default function Component12( props ){

    /** =================================================== jsx =================================================== */
    return(<>
            <BrowserRouter>
                <ul>
                     {/* 깜빡거림(\새로고침) ---> 돌아가긴 하지만 비추!!!! 옛날 방식 nononono!!! */}
                    <li> <a href="/">메인페이지(Home/html방식)</a> </li>


                    {/* =========================== 진짜 url이 아닌 가상의 url ============================ */}
                    <li> <Link to ="/">메인페이지(Home/a Link방식보안측면/보안 관련은(예) <br/>로그인!!! 서버에서 관리해야 하기때문에 a링크를 사용한다!!!!!!/서버사이드 렌더링)</Link></li>
                    <li> <Link to ='/about'>소개페이지</Link></li>
                     <li> <Link to ='/mypage'>마이페이지1</Link></li>
                      {/* MyPage 컴포넌트의 리턴값을 가져온다! 개꿀!!!!!! 라우터 최고!!!!! */}
                    <li> <Link to ='/mypage?name=홍길동&age=30'>마이페이지2(?쿼리스트링)</Link></li>
                    {/* path varible  구체적인 경로명이 없으면 나오지도 않음  */}
                    <li> <Link to ='/product'>제품페이지1(path X)</Link></li>
                    <li> <Link to ='/product/코카콜라/1000'>제품페이지2(path O)</Link></li>
                </ul>
                <Routes>
                    {/* 가상의 url을 정의하고 정의한 url과 매핑할 컴포넌트 정의 */}
                    <Route path="/" element={ <Home /> } ></Route>

                    <Route path ='/about' element={ <About /> }> </Route>

                    {/* 쿼리스트링은 사용하는 쪽에서??? */}
                    <Route path ='/mypage' element ={ <MyPage/> }></Route>

                    {/* path varible 방식 : 우선순위가 다르다??  */}
                    <Route path ='/product/:pname/:price' element={ <Product /> }></Route>

                    {/*  만약에 존재하진 않은 가상 URL 을 요청하면, 404 컴포넌트 노출  */}
                    <Route path = '*' element={ <Page404 /> }></Route>  
                </Routes>
            </BrowserRouter>
        </>)

}// func end