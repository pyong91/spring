<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>방명록 작성</h1>
	<form action="insert" method="post">
		<input type="text" name="content" required>
		<input type="submit" value="작성">
	</form>
</body>
</html>