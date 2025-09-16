// REACT 실습1 : Task1과 Profile 컴포넌트를 완성하시오.

// Fecth 이용하여 서버로 부터 받은 데이터/자료 
const data = [
  {
    name: 'Hedy Lamarr',
    imageUrl:'https://i.pravatar.cc/150?img=47'
  },
  {
    name: 'Grace Hopper',
    imageUrl: 'https://i.pravatar.cc/150?img=48'
  }
];

export default function Task1( props ){
  return (<>
  {/*
    <h3>1. {data[0].name}</h3>
    <img src={data[0].imageUrl}/>
    <h3>2. {data[1].name}</h3>
    <img src={data[1].imageUrl}/>
    <Profile 아무거나 속성명(props) = { data[0]} />
    export default에서 props의 속성명에 대한 데이터를 정의한다. 
    */}
    <Profile user = { data[0] } />
    <Profile user = { data[1] } />

   </>)
}

function Profile( props ) {
    // 함수 목적 : 재사용성, 중복제거, 매개변수에 따른 서로 다른 결과물
  return (<> 
    {/*<h3>1. {props.속성명.name}</h3>*/}
    <h3>{props.user.name}</h3>
    <img src={props.user.imageUrl}/>
  </> );
}