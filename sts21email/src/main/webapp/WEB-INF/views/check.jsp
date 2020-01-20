<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	// .email-form이 전송되면 send 주소로 비동기 신호를 전송(ajax)
	$(function(){
		// 처음에 인증 칸 숨김
		$(".validate-form").hide();
		
		
		$(".email-form").submit(function(e){
			e.preventDefault();
			
			$(this).find("input[type=submit]").prop("disabled", true);
			$(this).find("input[type=submit]").prop("value", "인증번호 발송중..");
			
			var url = $(this).attr("action");
			var method = $(this).attr("method");
			var data = $(this).serialize();
			
			$.ajax({
				url: url,
				type: "get",
				data: data,
				success:function(resp){
					console.log(resp);
					if(resp == "success") {
						$(".validate-form").show();
					}
					
				}
			});
		});
		
		
		// validate-form이 전송되면 / validate로 비동기 요청 전송
		$(".validate-form").submit(function(e){
			e.preventDefault();
			
			var url = $(this).attr("action");
			var method = $(this).attr("method");
			var data = $(this).serialize();
			
			$.ajax({
				url: url,
				type: "get",
				data: data,
				success:function(resp){
					console.log(resp);
					if(resp == "success"){
						alert("인증완료");
					}
					else {
						alert("인증실패");
					}
				}
			});
		});
	});
</script>

<h1>이메일 인증 시스템</h1>

<form class="email-form" action="send" method="post">
	<input type="email" name="email" placeholder="이메일 입력">
	<input type="submit" value="인증번호 보내기">
</form>

<form class="validate-form" action="validate" method="post">
	<input type="text" name="cert" placeholder="인증번호 입력">
	<input type="submit" value="인증하기">
</form>


