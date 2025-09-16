import Task1 from "./Task1"

const product =[
    { title : "무선 키보드1", price : 45000, stock : true }, 
    { title : "무선 키보드2", price : 5000, stock : false }, 
    { title : "무선 키보드3", price : 10000, stock : true }
]

export default function Task2( props ){
    // 하위 컴포넌트 호출 및 의(props) 속성명의 객체를 정의하여 자료 전달(여기서 반복문을 돌림!)
    return (<>
            <Product pdinfo = {product[0]}/>
            <Product pdinfo = {product[1]}/>
            <Product pdinfo = {product[2]}/>
        </>)    
}

// 하위 컴포넌트 : 제품 1개 개별의 정보를 구성하는 컴포넌트 
function Product ( props ){
    //const stock = props.pdinfo.stock === true ? "재고있음" : "재고없음";
    return (<>
        <h3>{props.pdinfo.title}</h3>
        <p>{props.pdinfo.price.toLocaleString()}</p>
        { /* <p>{stock}</p> */}
        <p>{props.pdinfo.stock == true ? "재고있음(O)" : "재고없음(X)"}</p>
    </>)
}