import { useEffect, useState } from "react";
import axios from "axios";

//(에러):1 Access to XMLHttpRequest at 'http://localhost:8080/board' from origin 'http://localhost:5173' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.

// https://docs.google.com/spreadsheets/d/1he5j8jtydzWlxf5-GbMu3BmuSshf074Xu5_JWEXwvcY/edit?gid=1486375196#gid=1486375196
export default function Component10( props ){

    // [1.1] 등록(POST) : 입력받은 데이터들을 관리하는 useState 정의
    const[ bcontent, setBcontent ] = useState('');
    const[ bwriter, setBwriter ] = useState('');

  // [2.1] 전체조회 : 출력할 useState 정의
    const[ boards, setBoards ] = useState([ {  bno : 1, bcontent :"test", bwriter : "test" } ]);

    // [1.2] 등록(POST) 함수 : axios 통신
    const boadWrite = async() => {
        // 1) 입력받은 데이터 객체화 
        const obj = { bcontent, bwriter }
        const response = await axios.post("http://localhost:8080/board", obj); // 서버가 달라서 상대경로를 못찾아 절대경로를 넣어줌
        //console.log( response.data );
        //boardPrint(); // 게시물 등록하고 나면, 리스트 출력에 바로 반영

        setBoards( [...boards, obj] );
        setBcontent('');
        setBwriter('');

    }//f end

    // [2.2] 전체조회 : 출력함수
    // async function

    const boardPrint = async() =>{
        const response = await axios.get("http://localhost:8080/board");
        setBoards( response.data )// 확인용
    }

    useEffect( () => { boardPrint()} , [ ] ) // 최초 1회 실행

    // [3] 삭제
    const deleteBoard = async( deleteBno ) => {
        const newboards = boards.filter( (board) => { return board.bno != deleteBno } ); 
        const response = await axios.delete(`http://localhost:8080/board?bno=${deleteBno}`)
        setBoards( [...newboards] )
    }
    
    return(<>

    {/* // [1.3] 등록(POST) html */}
        <h3> 스프링서버의 boardService13(d7) Axios 통신</h3>
        <input onChange={ (e) => {setBcontent(e.target.value)} } placeholder="내용"/>   
        <input onChange={ (e) => {setBwriter(e.target.value)} }  placeholder="작성자"/>   
        <button onClick={ boadWrite }>등록</button>

        {
//// [2.2] 전체조회 : 출력함수
            boards.map(( board ) => { return <div> {board.bno} {board.bcontent} {board.bwriter} <button onClick={ () => { deleteBoard(board.bno) } }>삭제</button></div>  })
        }
    </>)

}//c end