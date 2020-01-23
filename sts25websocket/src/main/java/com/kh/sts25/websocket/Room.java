package com.kh.sts25.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

// 채팅방 클래스
public class Room {
	// 방에 접속한 사용자 목록
	private Set<WebSocketSession> userList = new HashSet<>();
	
	// 방에 신규 인원을 추가하는 메소드
	public void add(WebSocketSession session) throws IOException {
		this.userList.add(session);
		String id = (String)session.getAttributes().get("id");
		broadcast(session, "님이 접속하셨습니다");
	}
	
	// 방에 있는 인원을 삭제하는 메소드
	public void remove(WebSocketSession session) throws IOException {
		this.userList.remove(session);
		String id = (String)session.getAttributes().get("id");
		broadcast(session, "님이 퇴장하셨습니다");
	}
	
	// 방에 있는 인원에게 메시지를 전송하는 메소드
	public void broadcast(WebSocketSession user, String text) throws IOException {
		String id = (String) user.getAttributes().get("id");
		TextMessage message = new TextMessage("["+id+"]"+text);
		for (WebSocketSession session : userList) {
			session.sendMessage(message);
		}
	}
	
	// 방 인원수를 알려주는 메소드
	public int count() {
		return userList.size();
	}
	
	// 방이 비었는가?
	public boolean isEmpty() {
		return count() == 0;
	}
}
