<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1>등록페이지</h1>
<form action="regist" method="post">
	<h5>상품명</h5>
	<input type="text" name="name" required>
	<h5>판매가</h5>
	<input type="text" name="price" required>
	<input type="submit" value="등록">
</form>
<h3><a href="./">돌아가기</a></h3>