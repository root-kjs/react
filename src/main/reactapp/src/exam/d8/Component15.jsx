import axios from 'axios'; 
import { useRef } from 'react';
export default function Component15( props ){
    // 1. 일반 axios 설정 연결 테스트
    const axios1 = async() => {
        try{
            const response = await axios.get("http://localhost:8080/axios")
            const data = response.data;
            console.log( "1..." + data )
        }catch( e ){
            console.error(e)
        }
    }

    // 2. 로그인 :::: axios.post( url, body, option )
    const axios2 = async() => {
        try{
            const obj = { id : "qwe", pwd : "1234" }
            const option = { withCredentials : true  } // cors 세션/토큰 허용 옵션 설정 // 자바 : .allowCredentials(true)
            const response = await axios.post("http://localhost:8080/axios/login", obj, option);
            console.log( "2..."+response.data )
        }catch( e ){
            console.error(e)
        }
    }

    // 3.  내정보(로그인 세션 확인)
    const axios3 = async() => {
        try{
            const option = { withCredentials : true  }
            const response = await axios.get("http://localhost:8080/axios/info", option);
            console.log( "3..."+response.data )
        }catch( e ){
            console.error(e)
        }
    }

    // **************.  카카오 모빌리티 API 연동 테스트
    const kMobility = async() => {
        try{
            const REST_API_KEY ="c23eef8dd6c751e1490aee133c63b48f"; // REST API 키
            const option = { 
                headers: {"Authorization": `KakaoAK ${ REST_API_KEY }`} 
            }
            const response = await axios.get("https://apis-navi.kakaomobility.com/v1/directions?origin=127.10764191124568,37.402464820205246&destination=127.11056336672839,37.39419693653072&summary=false&waypoints=127.17354989857544,37.36629687436494&priority=RECOMMEND&car_fuel=GASOLINE&car_hipass=false&alternatives=false&road_details=false", option);
            console.log( "kakao_mobility" + response )
        }catch( e ){
            console.error(e);
        }
    }// 

    //4. 폼은 name 값과 dto 속성명과 똑같아야 함. 
    const formRef1 = useRef();
    const axios4 = async() => {
        try{
            const form = formRef1.current; // useRef에서 참조중인 dom 객체 한꺼번에 가져오기
            const option = {
                headers : { "Content-Type:":"application/x-www-form-urlencoded"}// 일반 폼 // chrome-extension://aejoelaoggembcahagimdiliamlcdmfm/index.html#requests 참고할 것 
            }
            const response = await axios.post("http://localhost:8080/axios/form", form, option);
            const data = response.data;
           console.log( "4...dlfq"+response.data )
        }catch(e){
            console.error(e);
        }
    }

    // 5. 첨부파일 폼
    
  const formRef2 = useRef();
    const axios5 = async() => {
        try{
            const form = formRef2.current; // useRef에서 참조중인 dom 객체 한꺼번에 가져오기
            const formData = new FormData( form )
            const option = { headers: { "Content-Type": "multipart/form-data" }  }//첨부 파일 보내는 형식 
            const response = await axios.post("http://localhost:8080/axios/formdata", formData, option);
            const data = response.data;
           console.log( "5...첨부파일 "+response.data )
        }catch(e){
            console.error(e);
        }
    }


// ============================= jsx ==========================================
    return(<>
    
    <h3> Axios CORS 설정 테ㅡ트</h3>
    {/*  CORS(Cross-Origin Resource Sharing) 정책 설정 반영  */}
    <button onClick={ axios1 }> axios1 </button> 
    {/*  */}
    <button onClick={ axios2 }> axios2 </button>
    <button onClick={ axios3 }> axios3 </button>
    <button onClick={ kMobility }> kakao Mobility </button>

    {/* 일반 폼 fetch 기본 타입 전송이 form */}
    <form ref={ formRef1 }> 
        <input name='id'/><br/>
        <input name='pwd'/><br/>
        <button onClick={ axios4 } type='button'> axios4 </button>
    </form>

     {/* 일반 폼 */}
    <form ref={ formRef2 }>
        <input name='file' type='file'/><br/>
         <button onClick={ axios5 } type='button'> axios5 </button>
    </form>

    </>)
}// 


/**
 *  카카오모빌리티 
 * 
 * // 테스트할 URL (줄바꿈 없이 한 줄로 입력)
const url = "https://apis-navi.kakaomobility.com/v1/directions?origin=127.10764191124568,37.402464820205246&destination=127.11056336672839,37.39419693653072&summary=false&waypoints=127.17354989857544,37.36629687436494&priority=RECOMMEND&car_fuel=GASOLINE&car_hipass=false&alternatives=false&road_details=false";

// API 요청 설정
fetch(url, {
    method: 'GET',
    headers: {
        // 인증 헤더 추가
        'Authorization': 'KakaoAK c23eef8dd6c751e1e1490aee133c63b48f'
    }
})
.then(response => response.json()) // 응답을 JSON 형태로 변환
.then(data => console.log(data))   // 콘솔에 결과 출력
.catch(error => console.error('Error:', error)); // 에러 발생 시 출력
 */
