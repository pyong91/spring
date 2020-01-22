<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/secom.js"></script>

<form action="login" method="post">
	<input type="text" name="id" required><br><br>
	<input type="password" name="pw" required><br><br>
	<input type="submit" value="로그인">
</form>