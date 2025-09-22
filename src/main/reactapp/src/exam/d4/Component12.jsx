import { BrowserRouter, Routes,  Route, Link, useSearchParams } from 'react-router-dom';
// [1] 메인페이지 컴포넌트
function Home( props ){
    return(<>
        메인페이지
    </>)
}// func end

// [2] 소개페이지 컴포넌트
function About( props ){
    return(<>
        소개페이지
    </>)
}// func end

// [3] 마이페이지 컴포넌트 > 쿼리스트링 매개변수 전달
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
                    <li> <Link to ="/">메인페이지(Home/Link방식)</Link></li>
                    <li> <Link to ='/about'>소개페이지</Link></li>
                     <li> <Link to ='/mypage'>마이페이지1</Link></li>
                      {/* MyPage 컴포넌트의 리턴값을 가져온다! 개꿀!!!!!! 라우터 최고!!!!! */}
                    <li> <Link to ='/mypage?name=홍길동&age=30'>마이페이지2(쿼리스트링)</Link></li>
                   
                </ul>
                <Routes>
                    {/* 가상의 url을 정의하고 정의한 url과 매핑할 컴포넌트 정의 */}
                    <Route path="/" element={ <Home /> } ></Route>
                    <Route path ='/about' element={ <About /> }> </Route>
                    <Route path ='/mypage' element ={ <MyPage/> }></Route>
                </Routes>
            </BrowserRouter>
        </>)

}// func end