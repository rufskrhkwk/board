<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table>
		<tr>
			<th>cd_id</th>
			<th>cd</th>
			<th>cd_nm</th>
			<th>use_yn</th>
			<th>ent_uno</th>
			<th>ent_dtm</th>
			<th>upd_uno</th>
			<th>upd_dtm</th>
		</tr>
		<c:forEach items="${sample }" var="data">
			<tr>
				<td>${data.cdId }</td>
				<td>${data.cd }</td>
				<td>${data.cdNm }</td>
				<td>${data.useYn }</td>
				<td>${data.entUno }</td>
				<td>${data.entDtm }</td>
				<td>${data.updUno }</td>
				<td>${data.updDtm }</td>
			</tr>
		</c:forEach>
	</table>



</body>
</html>