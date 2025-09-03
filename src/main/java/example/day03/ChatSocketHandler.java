package example.day03;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.*;

// ******************  서버 웹소켓 역할 ******************
// @RestController @Service @Repository  // MVC패턴의 스프링 컨테이너(메모리)의 빈(객체) 등록
@Component // MVC패턴은 아니지만 스프링 컨테이너(메모리)의 빈(객체) 등록
public class ChatSocketHandler extends TextWebSocketHandler {

    // Map컬렉션 : .put( key, value )    .get( key )        .remove( key )
    // List컬렉션 : .add( value )       .get(  인덱스 )     .remove( 인덱스 )

    // * 접속된 클라이언트소켓들을 서버가 가지고 있을 예정
    private static final Map< String , List<WebSocketSession> > 접속명단 = new Hashtable<>();
    // { 0 : [ "유재석" , "강호동" ] , 1 : [ "서장훈" , "김희철"] }
    // key : 방번호 ,,,,, value : 해당 key(방)의 접속된 클라이언트들/리스트

    // [*] JSON 타입을 자바 타입 을 *변환* 해주는 라이브러리 객체 , ObjectMapper
    // 주요 메소드
    // 1. objectMapper.readValue( json문자열 , MAP.class ) : 문자열(json) --> MAP
    // 2. objectMapper.writeValueAsString( map객체 ) : MAP객체 --> 문자열(json)
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 1. 클라이언트 소켓 과 서버소켓이 연동 되었을때 이벤트
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("============= *클라이언트 소켓*이 들어왔다. ==================== ");
    }

    // 2. 클라이언트 소켓 과 서버소켓이 연동 끊겼을때 이벤트
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("============= *클라이언트 소켓*이 나갔다. ==================== ");
        // 2-1 : 접속이 끊긴 세션(클라이언트소켓)의 속성 (부가정보)를 확인한다.
        String room = (String) session.getAttributes().get("room"); // Object
        String nickName = (String) session.getAttributes().get("nickName"); // 강제타입변환 : (새로운타입)값
        // 2-2 : 만약에 방과 닉네임이 일치한 데이터가 접속명단에 존재하면  세션 제거
        if( room != null && nickName != null ){
            List< WebSocketSession > list = 접속명단.get( room ); // 해당 방의 key(방번호) 접속(목록)꺼내기
            list.remove( session );
            // 2-3 : 세션이 퇴장 했을때 알림 메시지[4] 보내기
            alarmMessage( room , nickName+"이 퇴장 했습니다.");
        } // 2-2 end
    } // func end

    // 3. 클라이언트 소켓 으로 부터 메시지를 받았을때 이벤트
    @Override
    protected void handleTextMessage( WebSocketSession session, TextMessage message ) throws Exception {
        System.out.println("============= *클라이언트 소켓* 으로부터 메시지 받았다. ==================== ");
        System.out.println( message.getPayload() ); // 3-1 : 클라이언트가 보낸 메시지 확인
        // 3-2 : 자바는 JSON을 모르기 때문에 JSON형식을 MAP 타입으로 변환
        // * Restful API 인 @ResponseBody @RequestBody 는 자동으로 JSON <---> MAP 변환 제공 , 웹소켓은 안됨
        Map<String,String> msg = objectMapper.readValue( message.getPayload() , Map.class );
        // 3-3 : 만약에 메시지에서 타입(type) 이 'join' 이면
        if( msg.get("type").equals("join") ){
            String room = msg.get("room"); // 방번호
            String nickName = msg.get("nickName"); // 접속자명
            // 3-4 현재 메시지를 보내온 클라이언소켓(세션)에 부가정보(방번호 와 접속자명) 속성 추가 , 로그인 세션 비슷
            session.getAttributes().put( "room" , room );       // 브라우저 세션 vs HTTP 세션 vs 웹소켓 세션
            session.getAttributes().put( "nickName" , nickName );
            // 3-5 접속명단에 등록하기
            if( 접속명단.containsKey( room ) ){ // 만약에 등록할 방번호(key) 가 존재하면
                접속명단.get( room ).add( session ); // 해당하는 방번호에 클라이언트소켓 (세션) 추가
            }else{ // 존재하지 않으면
                List< WebSocketSession > list = new Vector<>();
                list.add( session ); // 새로운 목록에 세션 추가
                접속명단.put( room , list ); // 새로운 방번호(key) 새로운 목록(list) 을 map(접속명단) 에 등록
            }
            // 3-6 접속 성공한 닉네임을 [4]알림메시지 보내기
            alarmMessage( room , nickName+"이 입장 했습니다.");

        } // 3-3 end
        // 3-7 : 만약에 메시지에서 타입(type) 이 'msg' 이면
        else if( msg.get("type").equals("msg") ){
            // 3-8 : 메시지를 보낸 세션의 방번호 확인
            String room = (String)session.getAttributes().get("room");
            // 3-9 : 메시지를 보낸 세션의 같은방 번호의 목록들에게 메시지 보내기
            for( WebSocketSession client : 접속명단.get( room ) ){
                client.sendMessage( message ); // 서버가 받은 메시지를 클라이언트들에게 다시 전달
            } // for end
        } // 3-7 end

        System.out.println( 접속명단 ); // 확인

    } // func end


    // 4. 개발자(우리) 가 만든 서비스 *추가* 알림 메소드 , 접속[3-6]/퇴장[2-3] 했을떄 실행
    public void alarmMessage( String room , String message ) throws Exception  {
        // String room : 몇번방에?? , String message : 메시지내용??
        // throws : 예외처리 던지기, 해당 메소드에서 모든 예외/오류를 해당 메소드를 호출한곳으로 반환
        // 4-1 : 보내고자 하는 정보를 map 타입으로 구성
        Map< String , String > msg = new HashMap<>();
        msg.put("type" , "alarm" );
        msg.put("message" , message );
        // 4-2 : map 타입을 JSON형식의 문자열타입 으로 변환 , objectMapper
        String sendMsg = objectMapper.writeValueAsString(msg);
        // 4-3 : 현재 같은방(key)에 위치한 모든 세션들에게 '알람' 메시지 보내기
        for (WebSocketSession session : 접속명단.get(room) ) {
            session.sendMessage( new TextMessage(sendMsg) );
        }
    } // func end

} // class end














