<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="cpath" value="${pageContext.request.contextPath}"/>
<sec:authentication property="principal.username" var="userId"/>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<sec:authorize access="isAnonymous()">
<!-- 로그인한 이용자만 글을 쓸 수 있다.-->
		<meta http-equiv="refresh" content="0;url=${cpath}/">
		<!-- meta태그는 브라우저 history에 기록이 남지 않음. -->
</sec:authorize>

</head>
<body>
	<div class="container">
		<h2>Spring Web MVC01</h2>
		<div class="panel panel-default">
			<div class="panel-heading">Spring Board</div>
			<div class="panel-body">
				<!-- 게시판 글쓰기 화면 만들기 -->
				<form class="form-horizontal" action="${cpath}/boardInsert" method="post" enctype="multipart/form-data">
					
					<div class="form-group">
						<label class="control-label col-sm-2" for="title">제목:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="title" name="title" placeholder="Enter title">
							<input type="checkbox" name="secret" value="true">비밀글
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-2" for="contents">내용:</label>
					    <div class="col-sm-10">
					    	<textarea class="form-control" row="5" id="contents" name="contents"></textarea>
					    </div>
					</div>
				  
					<div class="form-group">
						<label class="control-label col-sm-2" for="writer">작성자: </label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="writer" name="writer" value="${userId}" readonly="readonly">
						</div>
					</div>
				  
					<div class="form-group">
						<label class="control-label col-sm-2" for="writer">첨부파일: </label>
						<div class="col-sm-10">
							<input type="file" id="file1" name="filename" placeholder="첨부파일"  multiple="multiple" style="border:0 solid black;">
					    </div>
					</div>
					
					<!-- 서버에 들어온 요청이 실제 서버에서 허용한 요청이 맞는지 확인하기 위한 토큰 -->
					<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
				
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit">글쓰기</button>
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