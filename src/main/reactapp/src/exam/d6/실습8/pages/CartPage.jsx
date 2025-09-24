export default function CartPage( props ){
    
// -------------------------------------- 장바구니 페이지 > jsx 영역--------------------------------------
    return(<>
        <h1>장바구니</h1>
        <ul>
            <li>제품번호 : </li>
            <li>제품명 : </li>
            <li>가격 : </li>
            <li>수량 : <input name="qty"/>
                <button name="plus" onClick="" type="button"> + </button> 
                <button name="minus" onClick=""> - </button> 
            </li>
        </ul>
    </>)
}//func end