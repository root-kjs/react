
console.log( "chatting.js open ");

// [1] 서버 웹 소켓과 연동하는 클라이언트 소켓 (객체) 생성
const client = new WebSocket("/chat"); // 자바의 WebSocketConfig 에서 정의한 주소 확인  

// [2] 서버 웹 소켓과 연동 성공했을떄 , 
// event 이란? 함수의 매개변수 이면서 *해당 이벤트 정보*를 담고 있는 객체
client.onopen = ( event ) => { 
    console.log( "===========서버 소켓과 연동 성공했다. ===========")
}
// [3] 서버 웹 소켓과 연동 끊겼을때
client.onclose = ( event )=> {
    console.log( "===========서버 소켓과 연동 끊겼다. ===========")
}
// [4] 서버 웹 소켓으로 부터 메시지를 받았을떄
client.onmessage = ( event ) => {
    console.log( "===========서버 소켓으로부터 메시지를 받았다. ===========")
}

