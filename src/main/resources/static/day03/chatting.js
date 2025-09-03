console.log( "chatting.js open ");
// [*] 익명의 이름(비회원제) , Math.floor( Math.random() * 끝값 ) + 시작값
const randomId = Math.floor( Math.random() * 1000 ) + 1
const nickName = `익명${randomId}` // 익명{난수} // 회원제 일경우 서버 로그인세션정보

// [*] 방번호( url queryString )
const params = new URL( location.href ).searchParams 
const room = params.get('room') || "0" // url 경로상의 room 번호 가져오기 없으면 0

// [1] 서버 웹 소켓과 연동하는 클라이언트 소켓 (객체) 생성
const client = new WebSocket("/chat"); // 자바의 WebSocketConfig 에서 정의한 주소 확인  

// [2] 서버 웹 소켓과 연동 성공했을떄 , 
// event 이란? 함수의 매개변수 이면서 *해당 이벤트 정보*를 담고 있는 객체
client.onopen = ( event ) => { 
    console.log( "===========서버 소켓과 연동 성공했다. ===========")
    // (1). ========= 방번호에 특정한 닉네임을 **등록하는** 메시지 보내기 =========
    let msg = { type : "join" , room : room , nickName : nickName   } // JSON 형식으로 문자열 메시지 보내기
    // JSON.stringify( ) : 객체(JSON) 타입 를 형식을 유지하고 문자열 타입으로 변환 
    // JSON.parse( )     : 문자열 타입을 객체(JSON) 타입으로 변환 
        // "3" 숫자형식의 문자타입         VS        3  숫자형식의 숫자타입
    client.send( JSON.stringify( msg ) );
}













// [3] 서버 웹 소켓과 연동 끊겼을때
client.onclose = ( event )=> {
    console.log( "===========서버 소켓과 연동 끊겼다. ===========")
}

// [4] 서버 웹 소켓으로 부터 메시지를 받았을떄
client.onmessage = ( event ) => {
    console.log( "===========서버 소켓으로부터 메시지를 받았다. ===========")
}

