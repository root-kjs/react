export default function MenuPage( props ){

    // ** 서버로 받은 제품목록 가정한다. **
    const menu = [
        { id: 1, name: "아메리카노", price: 3000 }, 
        { id: 2, name: "카페라떼", price: 4000 },
        { id: 3, name: "카푸치노", price: 4500 },
    ];


//======================== APP > JSX==============================
return(<>
<h1>메뉴</h1>
   {
    menu.map((m) =>{ return<div>
        {m.id} {m.name} {m.price}
    </div>} )
   }
</>)
}// jsx end