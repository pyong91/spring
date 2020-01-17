<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<form method="post">
<article class="w-50">
	<div class="row">
		<h2>회원가입</h2>
	</div>
	<div class="row-left">
		<label for="id-input">아이디:</label>
		<input id="id-input" class="block-item input-item" type="text" name="id" required>
	</div>
	<div class="row-left">
		<label for="pw-input">비밀번호:</label>
		<input id="pw-input" class="block-item input-item" type="password" name="pw" required>
	</div>
	<div class="row-left">
		<label for="name-input">이름:</label>
		<input id="name-input" class="block-item input-item" type="text" name="name" required>
	</div>
	<div class="row-left">
		<label for="phone-input">전화번호:</label>
		<input id="phone-input" class="block-item input-item" type="tel" name="phone" required>
	</div>
	
	<div class="row-left">
		<label for="post-input">우편번호:</label>
		<div>
			<input id="post-input" class="input-item" type="text" name="post" size="6" placeholder="우편번호">
			<input class="btn" type="button" value="우편번호 찾기">
		</div>
	</div>
	<div class="row-left">
		<input id="basic-addr-input" class="block-item input-item" type="text" name="basic_addr" placeholder="기본주소">
	</div>
	<div class="row-left">
		<input id="extra-addr-input" class="block-item input-item" type="text" name="extra_addr" placeholder="상세주소">
	</div>
	<div class="row">
		<input class="btn" type="submit" value="가입하기">
		<input class="btn" type="reset" value="지우기"> 
	</div>
</article>
</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>