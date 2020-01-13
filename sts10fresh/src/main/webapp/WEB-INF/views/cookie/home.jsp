<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
   

<h1>cookie/home.jsp</h1>

<h3>cookie : ${cookie.id}</h3>
<h3>name : ${cookie.id.name}</h3>
<h3>value : ${cookie.id.value}</h3>
<h3>value : ${URLDecoder.decode(cookie.id.value,'UTF-8')}</h3>

<input type="text" name="id" value="${cookie.id.value}">

<c:if test="${empty cookie.id}">
0	광고를 출력하세요!
</c:if>


<h3><a href="create">쿠키 생성하기</a></h3>
<h3><a href="remove">쿠키 제거하기</a></h3>