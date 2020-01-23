<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
<script>


function connect(){
	var uri = "${pageContext.request.contextPath}/server";
	window.socket = new SockJS(uri);
	
	// 예약작업(callback) 지정
	window.socket.onopen = function(){
		console.log("연결성공");
	};
	window.socket.onclose = function(){
		console.log("연결종료");
	};
	window.socket.onerror = function(){};
	window.socket.onmessage = function(){};
}

function disconnect(){
	window.socket.close();
}

function sendMessage(text) {
	window.socket.send(text);
}

function displayMessage() {
	
}

</script>


<h1>sock.js를 사용하는 클라이언트</h1>