<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>목록 및 검색페이지</h1>

<c:choose>
	<c:when test="${not empty name}">
		<c:forEach var="productDto" items="${list}">
			<h3>${productDto}</h3>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<form action="search" method="get">
			<input type="text" name="name" required>
			<input type="submit" value="검색">
		</form>
	</c:otherwise>
</c:choose>


<h3><a href="./">돌아가기</a></h3>