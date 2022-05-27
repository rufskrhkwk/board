<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 게시글에 개행이 있는 경우를 위해서 추가해줌 -->
<% pageContext.setAttribute("newLineChar", "\n"); %>

<c:set var="cpath" value="${pageContext.request.contextPath}"/>
<sec:authentication property="principal.username" var="userId"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<!-- 404에러. jquery multifile 내용을 더 찾아봐서 수정할 것.-->
<script src="//github.com/fyneworks/multifile/blob/master/jQuery.MultiFile.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready( function() {
		$("input[type=file]").change(function () {
			var fileInput = document.getElementById("filename");
			var files = fileInput.files;
			//첨부파일은 2개까지만 가능하고, 그 이상 파일을 선택하면 선택한 파일이 초기화됨.
			if(files.length>2){
				alert('첨부파일은 2개까지만 가능합니다.');
				fileInput.value = null;
			}
		});
	});
</script>

<sec:authorize access="isAnonymous()">
		<!-- 로그인한 이용자만 글을 쓸 수 있다.-->
		<meta http-equiv="refresh" content="0;url=${cpath}/">
		<!-- meta태그는 브라우저 history에 기록이 남지 않음. -->
</sec:authorize>

</head>
<body>
	<div class="container">
		<h2>게시판 글쓰기</h2>
		<div class="panel panel-default">
			<div class="panel-heading">Spring Board</div>
			<div class="panel-body">
			
				<!-- 게시판 글쓰기 화면 만들기 -->
				<form:form commandName="boardVO" class="form-horizontal" action="${cpath}/boardInsert" method="post" enctype="multipart/form-data">
					
					<div class="form-group">
						<!-- 답글의 경우 groupno가 있다. groupno는 원글의 idx -->
						<input type="hidden" name="groupno" value="${vo.idx}">
						<input type="hidden" name="groupdepth" value="${vo.groupdepth}">
						
						<!-- 답글의 제목은 기본적으로 원글과 같은 제목이지만 수정할 수 있다. -->
						<label class="control-label col-sm-2" for="title">제목:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="title" name="title" value="[답글] ${vo.title}">
							<form:errors path="title" />
							<!-- 비밀글의 답글로는 비밀글만 쓸 수 있다. -->
							<c:choose>
								<c:when test="${vo.secret}">
									<input type="checkbox" name="secret" value="true" checked="checked" onclick="return false;">비밀글
								</c:when>
								<c:otherwise>
									<input type="checkbox" name="secret" value="true">비밀글
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-2" for="contents">내용:</label>
					    <div class="col-sm-10">
					    	<textarea class="form-control" row="5" id="contents" name="contents"></textarea>
					    	<form:errors path="contents" />
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
							<input type="file" id="filename" name="filename" placeholder="첨부파일"  multiple="multiple" maxlength="2" style="border:0 solid black;"/>
							<div class="file-list"></div>
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
					
				</form:form>
			</div>
			<div class="panel-footer">스프링 연습</div>
		</div>
	</div>
</body>
</html>