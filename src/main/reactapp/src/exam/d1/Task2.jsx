import Task1 from "./Task1"
import "./Task2.css"
const product =[
    { title : "무선 키보드1", price : 45000, stock : true }, 
    { title : "무선 키보드2", price : 5000, stock : false }, 
    { title : "무선 키보드3", price : 10000, stock : true }
]

export default function Task2( props ){
    // 하위 컴포넌트 호출 및 의(props) 속성명의 객체를 정의하여 자료 전달(여기서 반복문을 돌림!)
    return (<>
        <div class="products">
           {/* <Product pdinfo = {product[0]}/>
            <Product pdinfo = {product[1]}/>
            <Product pdinfo = {product[2]}/>
            */}
            <Product 
            title = { product[0].title }
            price = { product[0].price }
            stock = { product[0].stock }
            />
            <Product 
            title = { product[1].title }
            price = { product[1].price }
            stock = { product[1].stock }
            />
        </div>
        </>)    
}

// 하위 컴포넌트 : 제품 1개 개별의 정보를 구성하는 컴포넌트 
function Product ( { title, price, stock } ){ //props
    // console.log( props );
    // props구문 분해 , props 현재 상태 ==> pdinfo : { title : "무선 키보드1", price : 45000, stock : true } }
    // 변수나 상수로 쪼개는 작업을 ----> 구문 분해
    // const { title, price, stock } = props.pdinfo; 
   // console.log( title );

    // const stock = props.pdinfo.stock === true ? "재고있음" : "재고없음";
    //{props.pdinfo.price.toLocaleString()}
    //{props.pdinfo.stock == true ? "재고있음(O)" : "재고없음(X)"}
    return (<>
        <h3>{title}</h3>
        <p>{price}</p>
        { /* <p>{stock}</p> */}
        <p>{stock == true ? "재고있음(O)" : "재고없음(X)"}</p>
    </>)
}


//// [1] 해당 .jsx 파일내 대표(defalut) 컴포넌트 만들기
// export default function Task2( props ){
// return (<> 
//     <div>
//         { /* 하위 컴포넌트 호출 과 동시에 props속성 자료 전달 */}
//         <InfoCard product = { products[0] } />
//     </div>
// </>)
// } // func end 
// // [2] 하위 컴포넌트 : 제품1개당 정보 구성하는 컴포넌트
// function InfoCard( props ){
//     console.log( props );
//     return (<>
//         <ul>
//             <li> { props.product.title } </li>
//             <li> { props.product.price.toLocaleString() } </li>
//             <li> { props.product.inStock == true ? '재고있음' : '재고없음' }</li>
//         </ul>
//     </>)
// }





//// [1] 해당 .jsx 파일내 대표(defalut) 컴포넌트 만들기
// export default function Task2( props ){
// return (<> 
//     <div>
//         { /* 하위 컴포넌트 호출 과 동시에 props속성 자료 전달 */}
//         <InfoCard product = { products[0] } />
//     </div>
// </>)
// } // func end 
// // [2] 하위 컴포넌트 : 제품1개당 정보 구성하는 컴포넌트
// function InfoCard( props ){
//     const { title , price , inStock } = props.product
//     return (<>
//         <ul>
//             <li> { title } </li>
//             <li> { price.toLocaleString() } </li>
//             <li> { inStock == true ? '재고있음' : '재고없음' }</li>
//         </ul>
//     </>)
// }


//// [1] 해당 .jsx 파일내 대표(defalut) 컴포넌트 만들기
// export default function Task2( props ){
// return (<> 
//     <div>
//         { /* 하위 컴포넌트 호출 과 동시에 props속성 자료 전달 */}
//         <InfoCard 
//             title = { products[0].title } 
//             price = { products[0].price } 
//             inStock = { products[0].inStock }
//         />
//     </div>
// </>)
// } // func end 
// // [2] 하위 컴포넌트 : 제품1개당 정보 구성하는 컴포넌트
// function InfoCard( { title , price , inStock } ){
//     return (<>
//         <ul>
//             <li> { title } </li>
//             <li> { price.toLocaleString() } </li>
//             <li> { inStock == true ? '재고있음' : '재고없음' }</li>
//         </ul>
//     </>)
// }