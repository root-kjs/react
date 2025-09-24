import { useState } from "react";

export default function MenuPage( props ){
    /** 샘플 제품목록 */
    const menus = [
        { id: 1, name: "아메리카노", price: 3000 }, 
        { id: 2, name: "카페라떼", price: 4000 },
        { id: 3, name: "카푸치노", price: 4500 }
    ];

    /** 출력한 데이터를 관리할 useState */
    const[ memus1 , setMenus1 ] = useState( menus );
    

// -------------------------------------- 메뉴페이지 > jsx 영역--------------------------------------
    return(<>
    <h1>메뉴</h1>
        <form>
            { 
            menus.map( ( menu ) => { return <ul>
                <li>제품번호 : { menu.id }</li>
                <li>제품명 : { menu.name }</li>
                <li>가격 : { menu.price.toLocaleString() } </li>
                <li>수량 : <input name='qty'/>
                    <button name='plus' onClick='' type='button'> + </button> 
                    <button name='minus' onClick=''> - </button> 
                </li>
                <li> 
                    <button onClick="">장바구니 담기 </button> 
                </li>
            </ul>})
            }
        </form>
    </>)
}//func end