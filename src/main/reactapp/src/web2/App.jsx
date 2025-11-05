import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom"
import RoleRoute from "./components/RoleRoute"
import Header from "./components/Header";
import Login from "./pages/user/Login";
export default function App() {

/** ======================= App.jsx ======================== */
    return (
        <>
            <Router>
                <Header/>
                <Routes>
                    {/* 권한에 따른 조건 */}
                    {/* 공용페이지(비회원) */}
                    <Route path="/" element={<h1>메인페이지</h1>} />
                    <Route path="/signup" element={<h1>회원가입페이지</h1>} />
                    <Route path="/login" element={ <Login/> }/>

                    {/* 회원(USER)전용 + 관리자(ADMIN) */}
                    {/* props(속성) ============>  검사할 권한들(속성_props 전달 ) ={ ["USER", "ADMMIN"]  */}
                    <Route element={<RoleRoute roles={["USER", "ADMMIN"]} />}>
                        <Route path="/user/info" element={<h1>마이페이지</h1>} />
                    </Route>

                    {/* 관리자(ADMIN) 전용 */}
                    <Route element={<RoleRoute roles={["ADMIN"]} />} >
                        <Route path="/admin/dashboard" element={<h2>관리자페이지</h2>} />
                    </Route>
                    
                    {/* 에러페이지(404, 403) */}
                    <Route path="/fobidden" element={<h2>403페이지 접근권한없음</h2>} />
                </Routes>
            </Router>
        </>
    )
}