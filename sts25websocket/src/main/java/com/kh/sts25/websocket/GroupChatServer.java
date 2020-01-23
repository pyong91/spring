package com.kh.sts25.websocket;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.sts25.vo.ChatData;

/*
 *	그룹 채팅을 구현하기 위한 서버
 *	 - 방(Room) 클래스를 생성
 *	   -> 방 안에 사용자(WebSocketSession)를 추가
 *	 - 방 번호를 key, 방을 value로 저장, Map<Integer, Room>
 */
public class GroupChatServer extends TextWebSocketHandler{
	// 채팅방 저장소
	private Map<Integer, Room> roomList = new HashMap<>();
	
	// 상태값
	public static final int ENTER = 0;
	public static final int EXIT = 1;
	public static final int MESSAGE = 2;
	
	// 해석도구
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 입장 및 퇴장, 메시징까지 모두 처리(방번호를 알아야 하므로)
		// 만약, {번호:1,메시지:"안녕",상태:"입퇴장,메시지"} 이 왔다면
		
		String payload = message.getPayload();
		// payload는 JSON 문자열 형태
		// {"no":1,"text":"hello","status":2}
		ChatData data = mapper.readValue(payload, ChatData.class);
		
		// 번호와 상태를 본다
		// ENTER 상태라면 방이 없으면 생성 후 입장
		if(data.getStatus() == ENTER) {
			int no = data.getNo();
			boolean exist = roomList.containsKey(no);
			if(!exist) {
				Room room = new Room();	// 방생성
				roomList.put(no, room);	// 방목록에 방추가
			}
			roomList.get(no).add(session);	// 방에 사용자 추가
		}
		else if(data.getStatus() == EXIT) {
			int no = data.getNo(); // 방번호
			roomList.get(no).remove(session); // 사용자 제거
			if(roomList.get(no).isEmpty()) {
				roomList.remove(no);
			}
		}
		else { // message 상태라면 : 해당 방을 찾아서 그 방에 broadcast() 실행
			int no = data.getNo();
			roomList.get(no).broadcast(session, data.getText());
		}
	}
}
