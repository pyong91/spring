<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>session/home.jsp</h1>

<h3>id : ${id}</h3>

<h4>
	<a href="${pageContext.request.contextPath}/session/set">세션값 설정하기</a>
</h4>
<h4>
	<a href="${pageContext.request.contextPath}/session/remove">세션값 제거하기</a>
</h4>

<c:choose>
	<c:when test="${not empty id}">Login</c:when>
	<c:otherwise>Logout</c:otherwise>
</c:choose>