package com.kh.sts25.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

/*
 *  로그인 된 사용자가 채팅할 수 있는 서버
 *   - 필요한 내용 : 다수의 접속한 사용자의 WebSocketSession 정보
 *   			 각각 사용자들의 HttpSession 정보
 */

@Slf4j
public class LoginChatServer extends TextWebSocketHandler{
	// 사용자 저장용 저장소
	private Set<WebSocketSession> userList = new HashSet<>();

	private void broadcast(String msg) throws IOException {
		// 전체 전송
		TextMessage message = new TextMessage(msg);
		for (WebSocketSession user : userList) {
			user.sendMessage(message);
		}
	}
	
	// 연결 수립 메소드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		userList.add(session); // 접속자 추가
		log.info("사용자 접속 - 현재 사용자 {}명",userList.size());
		log.info("session = {}", session.getAttributes());
		String id = (String) session.getAttributes().get("id");
		//id == null 비회원
//		String text = "사용자 접속 - 현재 사용자 "+userList.size()+"명";
		if(id != null) {
			String text = "[" + id + "]님이 접속하셨습니다";
			broadcast(text);
		}
		
	}
	
	// 연결 종료 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		userList.remove(session); // 접속자 제거
//		log.info("사용자 종료 - 현재 사용자 {}명",userList.size());
		String id = (String)session.getAttributes().get("id");
		if(id != null) {
			String text = "[" + id + "]님이 접속을 종료하셨습니다.";
			broadcast(text);
		}
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 목표 : 수신 메시지를 전체에게 전송
		log.info("message = {}", message);
		String id = (String) session.getAttributes().get("id");
		broadcast("["+id+"]"+message.getPayload());
	}
}
