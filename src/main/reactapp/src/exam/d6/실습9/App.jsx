import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from './components/Header';
import HomePage from './pages/HomePage';
import MenuPage from './pages/MenuPage';
import CartPage from './pages/CartPage';

export default function App( props ){

//======================== APP(루트 페이지) > JSX ==============================
return(<>
<BrowserRouter>
    <Header/>
    <Routes>
        <Route path="/" element={<HomePage/>} />
        <Route path="/menu" element={<MenuPage/>} />
        <Route path="/cart" element={<CartPage/>} />
    </Routes>
</BrowserRouter>
</>)
}// jsx end