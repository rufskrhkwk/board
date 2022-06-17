<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:set var="cpath" value="${pageContext.request.contextPath}"/>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<sec:authorize access="principal.username!='${vo.writer}' and !hasRole('ROLE_ADMIN')">
<!-- 관리자가 아니고 작성자도 아닌 경우 들어올 수 없음-->
		<meta http-equiv="refresh" content="0;url=${cpath}/list">
		<!-- meta태그는 브라우저 history에 기록이 남지 않음. -->
</sec:authorize>

</head>
<body>
	<div class="container">
		<h2>Spring Web MVC01</h2>
		<div class="panel panel-default">
			<div class="panel-heading">Spring Board</div>
			<div class="panel-body">
			
		<!-- 게시판 글 수정 화면 만들기 -->
				<form class="form-horizontal" action="${cpath}/boardUpdate" method="post">
					
					<input type="hidden" name="idx" value="${vo.idx}">
					
					<div class="form-group">
						<label class="control-label col-sm-2" for="title">제목:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="title" name="title" value="${vo.title}">
						</div>
					</div>
				  
					<div class="form-group">
						<label class="control-label col-sm-2" for="contents">내용:</label>
					    <div class="col-sm-10">
					    	<textarea class="form-control" row="5" id="contents" name="contents">${vo.contents}</textarea>
					    </div>
					</div>
				  
					<div class="form-group">
						<label class="control-label col-sm-2" for="writer">작성자: </label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="writer" name="writer" value=${vo.writer} readonly="readonly">
						</div>
					</div>
					
			<!-- 서버에 들어온 요청이 실제 서버에서 허용한 요청이 맞는지 확인하기 위한 토큰 -->
					<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
				  
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit">수정</button>
							<button type="reset" onclick="location.href='${cpath}/list'">취소</button>
						</div>
					</div>
				</form>
				
			</div>
			<div class="panel-footer">스프링 연습</div>
		</div>
	</div>
</body>
</html>