import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./components/Header";
import HomePage from "./pages/HomePage";
import LoginPage from "./pages/LoginPage";
import ProfilePage from "./pages/ProfilePage";

export default function App( props ) {


// --------------------------------------jsx 영역--------------------------------------
return (<>
    <BrowserRouter>
        <h1> 루트페이지 </h1>
        <Header />
        <div class="container">
            <Routes>
                <Route path="/" element ={< HomePage />}/>
                <Route path='/login' element ={< LoginPage />}/> 
                <Route path='/profile' element ={< ProfilePage />}/>  
            </Routes>
        </div>
    </BrowserRouter>
</>)
}