<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(function(){
		// 로그인 버튼을 누르면 로그인 처리 컨트롤러로 이동
		//  - 입력된 ID를 같이 전송해줘야함
		$(".login-btn").click(function(){
			var text = $(".user-id").val();
			if(!text) return;
			location.href = "login?id=" + text;
		});
		
		// 로그아웃 버튼을 누르면 로그아웃 처리 컨트롤러로 이동
		$(".logout-btn").click(function(){
			location.href = "logout"; // 상대경로   // js
// 			$(location).attr("href", "logout"); // jquery
		});
		
		
		//접속버튼을 누르면 웹소켓 서버에 접속
		connect();
		
		// 전송버튼 처리
		$(".send-btn").click(function(){
			var text = $(".user-input").val();//입력값을 불러오고
			if(!text) return;//미입력시 중단
			window.socket.send(text);//전송
			$(".user-input").val("");//입력창 초기화
		});
	});
	//페이지를 나가기 전에 웹소켓 서버 접속을 종료
		$(function(){
		$(window).on("beforeunload", function(){
			window.socket.close(); // 종료코드
		});
		
	});
		// P태그 생성해서 본문체 추가
		function appendMessage(message){
			$("<p>").text(message).appendTo("#chat-content");
		}
		// 웹소켓 연결 함수
		function connect() {
			var host = location.host;
			var uri = "ws://"+host+"/sts25/loginchat"
			var socket = new WebSocket(uri);
			window.socket = socket;
			
			// 연결 시 예약 작업을 설정
			window.socket.onopen = function() {
				appendMessage("서버와 연결되었습니다.");
			};
			window.socket.onclose = function(){
				appendMessage("서버와 연결이 종료되었습니다.");
			};
			window.socket.onmessage = function(e) {
// 				console.log("메시지가 도착했습니다.");
				appendMessage(e.data);
			};
			window.socket.onerror = function() {
// 				console.log("연결 오류가 발생했습니다.");
				appendMessage("연결 오류가 발생했습니다.");
			};
		}
</script>

<h1>웹소켓 클라이언트(로그인)</h1>
<c:choose>	
	<c:when test="${id == null}">
		<input type="text" class="user-id" placeholder="id입력">
		<button class="login-btn">로그인</button>
	</c:when>
	<c:otherwise>
		<button class="logout-btn">로그아웃</button>
		<hr>
		<input type="text" class="user-input">
		<button class="send-btn">보내기</button>	
	</c:otherwise>
</c:choose>
<div id="chat-content"></div>
