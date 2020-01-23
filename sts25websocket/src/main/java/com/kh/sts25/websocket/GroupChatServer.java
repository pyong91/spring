package com.kh.sts25.websocket;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.sts25.vo.ChatData;

import lombok.extern.slf4j.Slf4j;

/**
 *	그룹 채팅을 구현하기 위한 서버
 *	- 방(Room) 클래스를 생성
 *		- 방 안에 사용자(WebSocketSession)를 추가
 *	- 방 번호를 key, 방을 value로 저장 , Map<Integer, Room> 
 */
@Slf4j
public class GroupChatServer extends TextWebSocketHandler{
	//채팅방 저장소
	private Map<Integer, Room> roomList = new HashMap<>();
	
	//상태값
	public static final int enter = 0;
	public static final int exit = 1;
	public static final int message = 2;
	
	//메시지 해석도구
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//		입장 및 퇴장 , 메시징까지 모두 처리(방번호를 알아야 하므로)
//		만약, {번호:1,메시지:"안녕",상태:"메시지"} 이 왔다면 상태에 따라 처리
		
		String payload = message.getPayload();
//		payload는 JSON 문자열 형태
//		{"no":1,"text":"hello","status":2}
		ChatData data = mapper.readValue(payload, ChatData.class);
		
//		번호와 상태를 본다
//		- enter 상태라면 방이 없으면 생성한 후 입장
		if(data.getStatus() == enter) {//enter 상태라면
			int no = data.getNo();//방 번호를 구해라
			boolean exist = roomList.containsKey(no);//방이 있는지 찾아라
			if(!exist) {//방이 없으면
				Room room = new Room();//방 만들고
				roomList.put(no, room);//추가
			}
			roomList.get(no).add(session);//해당 번호의 방에 사용자 추가
		}
		else if(data.getStatus() == exit) {//exit 상태라면
			int no = data.getNo();//방 번호를 구해온다
			roomList.get(no).remove(session);//해당 번호의 방에서 사용자 제거
			if(roomList.get(no).isEmpty()) {//방이 비었다면
				roomList.remove(no);//방을 삭제하라
			}
		}
		else {//message 상태라면 : 해당 방을 찾아서 그 방에 broadcast 실행
			int no = data.getNo();
			roomList.get(no).broadcast(session, data.getText());
		}
		
		log.info("[Server Room List]");
		for(int no : roomList.keySet()) {
			Room room = roomList.get(no);
			log.info("-> no = {}, {}", no, room);
		}
	}
}
