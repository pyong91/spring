<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1>회원가입 페이지</h1>

<form action="regist" method="post" enctype="multipart/form-data">
	<h3>아이디</h3>
	<input type="text" name="member_id">
	<h3>비밀번호</h3>
	<input type="password" name="member_pw">
	<h3>닉네임</h3>
	<input type="text" name="member_nick"><br><br>
	<input type="file" name="memberProfile" accept="image/*"><br><br>
	<input type="submit" value="등록"	>
</form>