<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%-- <c:set value="${pageContext.request.userPrincipal}" var="principal"/> --%>
<sec:authentication property="principal.username" var="userId"/>
<c:set var='role' value="${SPRING_SECURITY_CONTEXT.authentication.principal.memberAuthority}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	로그인한 사용자 페이지
<s:authorize ifAnyGranted="ROLE_USER">
<p> is Log-In</p>
</s:authorize>

<p>${userId}</p>
<p>${role}</p>
</body>
</html>