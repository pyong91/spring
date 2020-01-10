<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach var="dto" items="${list}">
	<h5>${dto.id} ${dto.title}</h5>
</c:forEach>