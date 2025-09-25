import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./components/Header";
import HomePage from "./pages/HomePage"
import MenuPage from "./pages/MenuPage"
import CartPage from "./pages/CartPage"
import Page404 from "./pages/Page404"

export default function App( props ){

// -------------------------------------- 인덱스(메인) > jsx 영역  --------------------------------------
return(<>
    <BrowserRouter>
        <Header />
        <div>
        <Routes>
            <Route path='/' element={ < HomePage /> }/>
            <Route path='/menu' element={ < MenuPage /> }/>
            <Route path='/cart' element={ < CartPage /> }/>
            <Route path = '*' element={ <Page404 /> } />
        </Routes>
        </div>
    </BrowserRouter>
</>)
}// jsx end