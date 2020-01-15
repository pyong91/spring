<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1>업로드 페이지</h1>
<!-- 
	파일 올릴 때 필수 : enctype="multipart/form-data"
 -->
<form action="upload3" method="post" enctype="multipart/form-data">
	<input type="text" name="name" placeholder="작성자"><br><br>
<!-- 
	multiple : 여러개 선택 가능
	accept : 확장자 제한
 -->
	<input type="file" name="file" multiple accept="image/*"><br><br>
	<input type="submit" value="업로드">
</form>