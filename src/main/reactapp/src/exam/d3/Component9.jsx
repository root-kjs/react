import axios from 'axios';
export default function Component9( props ){
    // [1] axios --> fetch랑 비슷한 아이 
    // React.js 에서 주로 사용되는 REST API (JSON) 비동기 통신 함수 fetch(js내장함수) , axios -->> 
    // [2] 사용법 : https://cdnjs.com/libraries?q=react   
    // 1. 설치(node.js) : 라이브러리 추가(리액트 서버가 종료된 상태에서)
    // 2. 리액트 최상위 폴더로 터미널 열기
    // 3. npm install axios npm i axios 
    // https://www.npmjs.com/  --> 자바 그래이들 같은 곳
    // https://cdnjs.com/ :  각종 js관련 라이브러리 다운받는 곳
    // 테스트 : https://jsonplaceholder.typicode.com/
    // 옵션을 넣지 않아도 된다. 
    // 자바가 없는 상태에서 사용 가이드 : https://jsonplaceholder.typicode.com/guide/
    

    // GET TEST
    const onAxios1 = async() => {
        const response = await axios.get("https://jsonplaceholder.typicode.com/posts"); 
        console.log( response.status ); // HTTP응답상태코드 200 status
        console.log( response.data ); // HTTP 응답자료


        const response2 = await axios.get("https://jsonplaceholder.typicode.com/comments?postId=1");
        console.log(response2.data);
    }

    // POST TEST
    const onAxios2 = async() => {
        const obj = { title : "test" , body : "test" , userId : 1 }
        const response1 = await axios.post("https://jsonplaceholder.typicode.com/posts", obj);
        console.log( response1.data );
    }

    // PUT TEST
    const onAxios3 = async() => {
        const obj = { id: 1, title: "foo", body: "bar", usrId: 1 } // 임의 데이터 https://jsonplaceholder.typicode.com/guide/
        const response1 = await axios.put("https://jsonplaceholder.typicode.com/posts/1", obj);
        console.log( response1.data );
    }

    // DELETE TEST
    const onAxios4 = async() => {
        const response1 = await axios.delete("https://jsonplaceholder.typicode.com/posts/1");
        console.log( response1.data );
    }


    return(<>
        <h3> axios 예제1</h3>
        <button onClick={ onAxios1 }>axios GET</button>
        <button onClick={ onAxios2 }>axios POST</button>
        <button onClick={ onAxios3 }>axios PUT</button>
        <button onClick={ onAxios4 }>axios DELETE</button>
    </>)

}// func end