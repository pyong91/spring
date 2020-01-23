<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	var enter = 0, exit = 1, message = 2;
	$(function(){
		//로그인 버튼을 누르면 로그인 처리 컨트롤러로 이동
		// - 입력된 ID를 같이 전송해줘야함
		$(".login-btn").click(function(){
			var text = $(".user-id").val();
			if(!text) return;
			location.href = "login4?id="+text+"&room=${param.room}";
		});
	});
</script>

<c:if test="${id != null}">
<script>
	$(function(){
		//로그아웃 버튼을 누르면 로그아웃 처리 컨트롤러로 이동
		$(".logout-btn").click(function(){
			location.href = "logout4?room=${param.room}";
// 			$(location).attr("href", "logout");
		});
		
		
		
// 		페이지가 로딩되면 웹소켓 서버에 접속
		connect();
		//페이지를 나가기 전에 웹소켓 서버 접속을 종료
		$(window).on("beforeunload", function(){
			sendMessage(exit);
			window.socket.close();//종료코드
		});
		
		//전송버튼 처리
		$(".send-btn").click(function(){
			var text = $(".user-input").val();//입력값을 불러오고
			if(!text) return;//미입력시 중단
			sendMessage(message, text);
			$(".user-input").val("");//입력창 초기화
		});
		
		//p태그 생성해서 본문에 추가
		function appendMessage(message){
			$("<p>").text(message).appendTo("#chat-content");
		}
		
// 		웹소켓 연결 함수
		function connect(){
			var host = location.host;
			var context = "${pageContext.request.contextPath}";
			var uri = "ws://"+host+context+"/group";
			console.log(uri);
// 			var socket = new WebSocket(uri);//연결코드
			window.socket = new WebSocket(uri);//연결코드
			
// 			연결 시 예약 작업을 설정
			window.socket.onopen = function(){
// 				console.log("서버와 연결되었습니다");
// 				appendMessage("서버와 연결되었습니다");
				sendMessage(enter);
			};
			window.socket.onclose = function(){
// 				console.log("서버와 연결이 종료되었습니다");
				appendMessage("서버와 연결이 종료되었습니다");
			};
			window.socket.onmessage = function(e){
// 				console.log("메시지가 도착했습니다");
// 				console.log(e.data);
				appendMessage(e.data);
			};
			window.socket.onerror = function(){
// 				console.log("연결 오류가 발생했습니다");
				appendMessage("연결 오류가 발생했습니다");
			};
		}
		
// 		메시지 전송 함수
		function sendMessage(status, text){
			var no = ${param.room};
			var message = {
				no:no,
				status:status,
				text:text
			};
			var value = JSON.stringify(message);
			window.socket.send(value);
		}
	});	
</script>  
</c:if>  
    
<h1>웹소켓 클라이언트(with 로그인, ${param.room} 번 방)</h1>
<c:choose>
	<c:when test="${id == null}">
		<input type="text" class="user-id" placeholder="아이디 입력">
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