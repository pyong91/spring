<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>방명록 보기</h1>
<c:forEach var="memoDto" items="${list}">
	<!-- type = date(날짜만) time(시간만) both(둘다) -->
	<fmt:parseDate var="t" value="${memoDto.when}" pattern="yyyy-MM-dd HH:mm:ss.S"/>
	<h5>${memoDto}</h5>
	<h5>
		<small>(<fmt:formatDate value="${t}" type="both" dateStyle="short"/>)</small>
	</h5>
</c:forEach>
<h3><a href="${pageContext.request.contextPath}">돌아가기</a></h3>