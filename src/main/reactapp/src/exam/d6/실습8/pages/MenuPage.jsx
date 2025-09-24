import axios from 'axios'; //  npm i axios
import { useEffect, useState } from "react";

export default function MenuPage( props ){
    /** 1. 샘플 제품목록 */
    const menuSample = [
        { id: 1, name: "아메리카노", price: 3000 }, { id: 2, name: "카페라떼", price: 4000 },{ id: 3, name: "카푸치노", price: 4500 }
    ];

    /** 2. useState 정의! --> 출력데이터 관리 */ 
    // useState : 컴포넌트에서 상태(state/값)를 관리하기 위한 훅, 다른 컴포넌트 들과 공유 안됨 , props 이용한 공유 가능
    // 상태(값)가 변경 되면 컴포넌트가 *리렌더링 됨*  // const [ 변수명 , set변수명 ] = useState( 초기값 );
    const[ memus , setMenus ] = useState( menuSample ); // 메뉴리스트데이터 --> 초기값은 위의 배열로 시작.
    //const[ qty , setQty ] = useState( "1" ); // 수량 +,- 데이터 
    const[ count , setCount ] = useState( 1 ); // 수량 +,- 데이터 

   

    /** 4. axios를 이용하여 스프링에게 데이터 요청 >>>> 현재 일단 패스!!...지만 적어본다..... */
    const printMenu = async() => {
        try{
        // 아래 axios get 요청 // 자바는 서버 포트가 다르니 전체 주소 기입 주의
        // 해당 컨트롤러 단에서 추후 아래 어노테이션 기입 필수  @CrossOrigin( value = "http://localhost:5173" ) // 리액트서버와 CORS통신(서로 다른 서버간의 요청/응답) 허용
        const response = await axios.get("http://localhost:8080/menu"); 
        const menusWithCount = response.data.map( (menu) => ({ ...menu, count:1 } )  );
        setMenus( menusWithCount ); // axios 요청값을 useState > setState 이용한 재렌더링!!!
        }catch( e ){ console.error(e); }
    }// func e

    /** 3. 메뉴 수량버튼(+/-), 인풋 변경시 상태변경 */
    // useEffect( () => { printMenu() } , [ ] ) //  // 최초 1회 실행 useEffect( ()=>{} , [의존성배열] )
    // 의존성배열에 대입된 count가 setCount가 될때 현재 useEffect (자동) 실행
    useEffect( () => { printMenu() } , [] )
    // useEffect( () => {  } , [ ] )

    /** 5. 수량 메소드 */
    // setXXX (새로운 값) ==> 만일 값이 변경 되었을 때 해당 컴포넌트 재실행(재렌더링) 
    // const countAdd = () => { setCount( count + 1 ) } // 리액트 재렌더링을 위한 setCount()함수 사용
    /** 5.1 사용자가 해당 메뉴의 수량버튼 클릭시, 해당 메뉴id와 수량을 매개변수로 전달  */
    const handleCount = ( id, changCount ) => {
        const selectMenu = memus.map(
            // ... 스프레드 연산자 --> 값의 변경이 아닌 주소가 변경됐을 때 작동이 됨/배열에 (...)스프레드 연산자 넣어줌, 참조값은 ...쓴다. 
            //setArray([...array, newUser]); // 리스트를 재렌더링
            (memu) => { if (memu.id === id) {
                 const newCount = memu.count + changCount;
                 return { ...memu, count: newCount > 0 ? newCount : 1 } //  최소 수량 유지
            } else return memu 
            }
        );
        setMenus( selectMenu );// 변경된 상태로 메뉴배열 저장됨,
    }//func 

    const countMinus = () => { if( count >= 2 ){ setCount( count - 1 ) } } // 관례적인 const상수를 사용하기 때문에 ++ 말고 + 1을 사용

    /** 6. 장바구니 담기 메소드 */
    const addCart = () => {

    }

// -------------------------------------- 메뉴페이지 > jsx 영역--------------------------------------
    return(<>
    <h1>메뉴</h1>
        { 
        memus.map( ( menu ) => { return <ul>
            { console.log( menu ) }
            <li>제품번호 : { menu.id }</li>
            <li>제품명 : { menu.name }</li>
            <li>가격 : { menu.price.toLocaleString() } </li> 
            <li>수량 :  { menu.count == null ? 1 : menu.count }
                {/* 사용자가 해당 메뉴의 수량버튼 클릭시, 해당 메뉴id와 수량을 매개변수로 전달 --> handleCount */}
                <button name='plus' onClick={ () => { handleCount( menu.id, 1 ) } } type='button'> + </button> 
                <button name='minus' onClick={ () => { handleCount( menu.id, -1 ) } } type='button'> - </button> 
            </li>
            <li> 
                {/* onClick='' */}
                <button  type='button'>장바구니 담기 </button> 
            </li>
        </ul>})
        }
    </>)
}//func end