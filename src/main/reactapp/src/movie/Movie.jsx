import axios from "axios";
import { useState, useEffect } from "react"
import "./movie.css"

export default function Movie( props ){
    // ( 1 ) 영화 등록
    //  컴포넌트 파일 나누는것을 나중에 알아버렸다 ㅎㅎㅎㅎ ㅜㅜ
    /* ====================================== useState 정의 ==================================== */
    const[ movies, setMovies ] = useState( [{ 
        mno: 1, mname: "인셉션", msupervision: "크리스토퍼 놀란", mgenre: "SF/액션",mintro: "타인의 꿈에 들어가 생각을 훔치는 이야기", nickname: "놀란짱팬",
        mdate: "2025-09-19", mpwd: "1234" }] );

    const[ comments, setComments ] = useState( [{ 
        cno: 3, mno: 5, ccomment: "영상미랑 OST가 진짜 미쳤다... 특히 마지막 장면에서 눈물 줄줄 흘렸어요ㅠㅠ",
        nickname: "감성폭발", mpwd: "9876", cdate: "2025-06-10"}] )

    const [selectedMovie, setSelectedMovie] = useState('');

    // ( 2 ) 영화 목록 --------------------------------------------------------------

    // 1. 출력할 데이터들을 axios 이용하여 스프링에게 요청
    const printMovie = async() => {
        const response = await axios.get("http://localhost:8080/movie");
        setMovies( response.data );
        //console.log( response );
        // 영화 목록을 성공적으로 불러온 후, 첫 번째 영화를 자동으로 선택
        if(response.data.length > 0) {
            setSelectedMovie(response.data[0]); // 
        }
    }
    // 2. 의존성배열이 비어있는 경우 ☆☆☆ 최초 1번만☆☆☆ 실행
    useEffect( ()=>{ printMovie() } , [] ) 
   // console.log( movies );

   // (2.4) 영화소개 처음 나오는 디폴트 페이지
   // const defaultmovie = movies[0];

   // ( 3 ) 특정영화 > 댓글 목록 --------------------------------------------------------------

    // 1. 출력할 데이터들을 axios 이용하여 스프링에게 요청
    const printComment = async( mno ) => {
        const response = await axios.get("http://localhost:8080/comment?mno="+mno);
        setComments( response.data );
        //setComments( [...newBoards ] );
        console.log( response );
    }

    // 2. 의존성배열이 비어있는 경우 ☆☆☆ 최초 1번만☆☆☆ 실행
    // useEffect( ()=>{ printComment() } , [] ) 

   // 3. movies가 변경될 때마다 printComment() 호출
   // --> 선택된 영화가 변경될 때마다 해당 영화의 댓글을 불러오는 useEffect
    useEffect(() => {
        if(selectedMovie) {
            printComment(selectedMovie.mno);
        }
    }, [selectedMovie]);

   // ( 4 ) 영화목록 > 영화제목 클릭시 우측 영화상세정보 변경 ---------------------------------------------------
    const handleMovieClick = (movie) => {
        setSelectedMovie(movie);
    }

    // =================================  .jsx영역  ========================================
    return(<>
    <h1>부귀영화 <span>익명 영화 토론 플랫폼</span></h1>
    <div class="both_wrap">
       
        {/* -------------- 좌측영역 시작 -------------- */}
        <div class="left_wrap">
            <AddMovie />
            <h3>영화 목록</h3>
            <table>
                <caption></caption>
                <thead>
                    <tr>
                        <th>No</th>
                        <th>영화제목</th>
                        <th>감독</th>
                        <th>장르</th>
                        <th>글쓴이</th>
                        <th>등록일</th>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    {// (2.3) 영화리스트 맵 반복문 출력 시작 ---------------
                        movies.map( (movie) => { return <tr>
                            <th>{movie.mno}</th>
                            <th><a onClick={ ()=>{ handleMovieClick(movie) } }>{movie.mname}</a></th>
                            <td>{movie.msupervision}</td>
                            <td>{movie.mgenre}</td>
                            <td>{movie.nickname}</td>
                            <td>{movie.mdate}</td>
                            <th><button onClick="">삭제</button></th>
                        </tr>
                        })
                    }
                </tbody>
            </table>
        </div>
        {/* -------------- 좌측영역 끝! -------------- */}
        {/* -------------- 우측영역 시작 ------------- */}
        
        {

        <div class="right_wrap">
        <h3>영화 토론</h3>
            <dl class="movie_info">
                <dt> { selectedMovie.mname } </dt>
                <dd><b>감독</b><span> { selectedMovie.msupervision } </span></dd>
                <dd><b>장르</b><span> { selectedMovie.mgenre } </span></dd>
                <dd><b>소개</b><span> { selectedMovie.mintro } </span></dd>
            </dl> 
            <div class="writer"> 글쓴이 :  { selectedMovie. nickname } / 등록일 : { selectedMovie. mdate } /  <button onClick="">삭제</button></div>
              <table> {/* -------------- 댓글목록 시작 ------------- */}
                <caption></caption>
                <thead>
                    <tr>
                        <th>작성자</th>
                        <th>토론내용</th>
                        <th>작성일</th>
                        <th>비고</th>
                    </tr>
                </thead>  
            <tbody>
                {
            comments.map( (comment) => { return <tr>
                <td>{comment.nickname}</td>
                <td class="comment">{comment.ccomment}</td>
                <td>{comment.cdate}</td>
                <th><button onClick="">삭제</button></th>
            </tr>
            })
             }
               </tbody>
            </table>  

        </div>
        }
        
        
        {/* -------------- 우측영역 끝! ------------- */}
    </div>
    </>)

}// f end

// 1. 영화등록
function AddMovie( props ){

    // 1. 영화 입력데이터 관리하는 useState 정의
    const[ mname , mnameSet ] = useState('');
    const[ msupervision , msupervisionSet ] = useState('');
    const[ mgenre , mgenreSet ] = useState('');
    const[ mintro , mintroSet ] = useState('');
    const[ nickname , nicknameSet ] = useState('');
    const[ mpwd , mpwdSet ] = useState('');


// [1.2] 등록(POST) 함수 : axios 통신
    const AddMovie1 = async() => {
        // 1) 입력받은 데이터 객체화 
        const obj = { mname, msupervision, mgenre, mintro, nickname, mpwd  }
        const response = await axios.post("http://localhost:8080/movie", obj); // 서버가 달라서 상대경로를 못찾아 절대경로를 넣어줌
        //console.log( response.data );
        printMovie(); // 게시물 등록하고 나면, 리스트 출력에 바로 반영

        mnameSet('');
        msupervisionSet('');
        mgenreSet('');
        mintroSet('');
        nicknameSet('');
        mpwdSet('');

    }//f end

    return ( <>
        <div class="add_wrap">
            <h3>영화 등록</h3>
            <table>
                <tbody>
                   <tr>
                        <td>
                            <input type="text" name="mname" onChange={ (e) => {setBcontent(e.target.value)} } placeholder="영화제목"/>
                            <input type="text" name="msupervision" onChange={ (e) => {setBcontent(e.target.value)} } placeholder="감독이름"/>
                            <input type="text" name="mgenre" onChange={ (e) => {setBcontent(e.target.value)} } placeholder="영화장르"/>
                            <input type="text" name="nickname" onChange={ (e) => {setBcontent(e.target.value)} } placeholder="글쓴이(닉네임)"/>
                            <input type="password" name="mpwd" onChange={ (e) => {setBcontent(e.target.value)} } placeholder="비밀번호(4자리)"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <textarea type="text" name="mintro" onChange={ (e) => {setBcontent(e.target.value)} } placeholder="영화 간단소개글"/>
                            <button onClick={ AddMovie1 }>등록</button>
                        </td>
                   </tr>
                </tbody>
            </table>
        </div>

    </>)
}// func end