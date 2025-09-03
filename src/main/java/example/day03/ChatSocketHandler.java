package example.day03;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

// ******************  서버 웹소켓 역할 ******************
// @RestController @Service @Repository  // MVC패턴의 스프링 컨테이너(메모리)의 빈(객체) 등록
@Component // MVC패턴은 아니지만 스프링 컨테이너(메모리)의 빈(객체) 등록
public class ChatSocketHandler extends TextWebSocketHandler {
    // 1. 클라이언트 소켓 과 서버소켓이 연동 되었을때 이벤트
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("============= *클라이언트 소켓*이 들어왔다. ==================== ");
    }
    // 2. 클라이언트 소켓 과 서버소켓이 연동 끊겼을때 이벤트
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("============= *클라이언트 소켓*이 나갔다. ==================== ");
    }
    // 3. 클라이언트 소켓 으로 부터 메시지를 받았을때 이벤트
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("============= *클라이언트 소켓* 으로부터 메시지 받았다. ==================== ");
    }
} // class end
