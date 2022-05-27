<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cpath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
	/* controller에서 지정한 메세지를 출력하고 url을 따라 이동합니다. */
		var msg = "${msg}";
		var url = "${cpath}"+"${url}";
		alert(msg);
		location.href = url;
	</script>
</head>
<body>

</body>
</html>