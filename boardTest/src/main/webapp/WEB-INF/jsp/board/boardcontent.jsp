<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:set var="cpath" value="${pageContext.request.contextPath}"/>
<c:set var="secret" value="${vo.secret}"/>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
 	function goDelete(idx){
 		location.href="${cpath}/boardDelete?idx="+idx;
 	}
 	
 	function goUpdate(idx){
 		location.href="${cpath}/boardUpdateForm/${vo.idx}";
 	}

</script>

<sec:authorize access="principal.username!='${vo.writer}' and !hasRole('ROLE_ADMIN')">
<!-- 관리자가 아니고 작성자도 아닌경우 비밀글인지 확인. 
	 * 참고 : JSP tag properties 중 ifAllGranted, ifAnyGranted, ifNotGranted 삭제됨	-->
	<script type="text/javascript">
		if(secret==true){ //비밀글인 경우
	 		alert('권한이 없습니다.');
	 		history.go(-1);
	 	}
	</script>
</sec:authorize>

</head>
<body>
<div class="container">
	<h2>게시글 조회2</h2>
	<div class="panel panel-default">
		<div class="panel-heading">Spring Board</div>
		<div class="panel-body">
			<table class="table table-bordered">
			<tr>
				<td>제목</td>
				<td>${vo.title}</td>
			</tr>
			<tr>
				<td>글번호</td>
				<td>${vo.idx}</td>
			</tr>
			<tr>
				<td>작성일</td>
				<td>${fn:split(vo.indate," ")[0]}</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${vo.writer}</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>${fn:replace(vo.contents,newLineChar,"<br>")}</td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td> 
					<c:forEach var="item" items="${file}">
			              <li> <a href="${cpath}/filedownload/${item.filename}">${item.originalName}</a></li>
				    </c:forEach>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<sec:authorize access="principal.username=='${vo.writer}' or hasRole('ROLE_ADMIN')">
						<button class="btn btn-primary btn-sm" onclick="goUpdate(${vo.idx})">수정</button>
						<button class="btn btn-danger btn-sm" onclick="goDelete(${vo.idx})">삭제</button>
					</sec:authorize>
					<button class="btn btn-success btn-sm" onclick="location.href='${cpath}/list'">리스트</button>
				</td>
			</tr>
			</table>
		</div>
		<div class="panel-footer">스프링 연습  </div>
		</div>
	</div>
</body>
</html>