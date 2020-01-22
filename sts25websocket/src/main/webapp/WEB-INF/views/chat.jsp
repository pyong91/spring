<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	//접속버튼을 누르면 웹소켓 서버에 접속
	$(function(){
		$(".connect-btn").click(function(){
			var host = location.host;
			var uri = "ws://"+host+"/sts25/echo"
			var socket = new WebSocket(uri);
			window.socket = new WebSocket(uri);
			
			// 연결 시 예약 작업을 설정
			window.socket.onopen = function() {
				console.log("서버와 연결되었습니다.");
			};
			window.socket.onclose = function(){
				console.log("서버와 연결이 종료되었습니다.");
			};
			window.socket.onmessage = function(e) {
				console.log("메시지가 도착했습니다.");
				console.log(e.data);
			};
			window.socket.onerror = function() {
				console.log("연결 오류가 발생했습니다.");
			};
			
		});	
		
		// 전송버튼 처리
		$(".send-btn").click(function(){
			var text = $(".user-input").val();
			window.socket.send(text);
			$(".user-input").val("");
		});
	});
	//종료버튼을 누르면 웹소켓 서버 접속을 종료
		$(function(){
		$(".disconnect-btn").click(function(){
			window.socket.close(); // 종료코드
		});		
	})
</script>

<h1>웹소켓 클라이언트</h1>
<button class="connect-btn">접속</button>
<button class="disconnect-btn">종료</button>
<hr>
<input type="text" class="user-input">
<button class="send-btn">보내기</button>
