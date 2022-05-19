<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<script type="text/javascript">
 	function goDelete(idx){
 		location.href="${cpath}/boardDelete?idx="+idx;
 	}
 	
 	function goUpdate(idx){
 		location.href="${cpath}/boardUpdate/${vo.idx}";
 	}
 	
  </script>
</head>
<body>
<div class="container">
	<h2>Spring Web MVC01</h2>
	<div class="panel panel-default">
		<div class="panel-heading">Spring Board</div>
		<div class="panel-body">
			<!-- 게시판 글쓰기 화면 만들기 -->
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
			  
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <button type="submit">글쓰기</button>
			      <button type="submit">취소</button>
			    </div>
			  </div>
			</form>
			
		</div>
		<div class="panel-footer">스프링 연습</div>
	</div>
</div>
</body>
</html>