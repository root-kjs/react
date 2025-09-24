import { Link } from "react-router-dom";

export default function Header( props ){

// --------------------------------------해더 > jsx 영역--------------------------------------
    return(<>
        <h1><Link to ='/'>더조은카페</Link></h1>
        <ul>
            <li><Link to ='/menu'>메뉴</Link></li>
            <li><Link to ='/cart'>장바구니</Link></li>
        </ul>
    </>)
}//func end