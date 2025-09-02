package example.day02;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component // 스프링 컨테이너(메모리)에 빈(객체) 등록
public class ChatHandler extends TextWebSocketHandler {

    // 1. 클라이언트 소켓이 서버소켓으로부터 연결을 성공했을때 실행되는 메소드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("[서버] 클라이언트소켓과 연동 성공");
        // WebSocketSession 이란 : ws 기반으로 클라이언트가 요청한 정보가 저장된 객체
        // HttpSession 이란 : http 기반으로 클라이언트가 요청한 정보가 저장된 객체
        System.out.println("[클라이언트 웹소켓 객체] : " + session );
    }

    // 2. 클라이언트 소켓이 서버소켓 과 연결을 끊겼을때 실행되는 메소드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("[서버] 클라이언트소켓과 연동 종료 ");
    }

    // 3. 클라이언트 소켓이 서버소켓 에게 메시지를 보냈을때 실행되는 메소드
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("[서버] 클라이언트로부터 메시지가 도착");
    }
}
