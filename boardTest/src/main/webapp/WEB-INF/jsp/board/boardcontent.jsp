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
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
 	function goDelete(idx){ //게시글 삭제
 		var frm = $("#frm")[0];
        frm.action = "${cpath}/boardDelete"+idx;
        frm.submit();
 	}
 	
 	function goUpdate(idx){ //게시글 업데이트
 		location.href="${cpath}/boardUpdateForm/"+idx;
 	}
 	
 	/* 댓글 불러오는 기능 
 	$(document).ready(function(){
 		loadcomment();
 	});
 	
 	function loadcomment(){
 		$.ajax({
 			url : "${cpath}/loadcommentAjax",
 			type: "get",
 			dataType : "json",
 			success : listView,
 			error : function(){alert("댓글 불러오기에 실패했습니다.");}
 		});
 	}
 	
 	function commentView(data){
 		
 	}*/

</script>

<sec:authorize access="principal.username!='${vo.writer}' and !hasRole('ROLE_ADMIN')">
<!-- 
	관리자가 아니고 작성자도 아닌경우 비밀글인지 확인. 
		* 참고 : JSP tag properties 중 ifAllGranted, ifAnyGranted, ifNotGranted 삭제됨
-->
	<script type="text/javascript">
		if(${vo.secret}==true){ //비밀글인 경우
			if(${matchResult}==false){ //원글 작성자가 아닌 경우 글내용을 조회할 수 없다.
		 		alert('권한이 없습니다.');
		 		history.go(-1);		
			}
	 	}
	</script>
</sec:authorize>

</head>
<body>
<div class="container">
	<h2>게시글 조회</h2>
	<div class="panel panel-default">
		<div class="panel-heading">Spring Board</div>
		<div class="panel-body">
			<table class="table table-bordered">
				<form id="frm" method="post"> <!-- 답글쓰기할때 제목과 글번호를 controller로 넘겨주기 위함. -->
		<!-- 게시글 내용 -->
					<tr>
						<td>제목</td>
						<td>${vo.title}</td>
					</tr>
					<tr>
						<td>글번호</td>
						<td><input type="text" name="idx" value="${vo.idx}" readonly="readonly" style="border:none"></td>
					</tr>
				</form>
				
				<tr>
					<td>작성일</td>
					<td>${fn:split(vo.indate," ")[0]}</td>
				</tr>
				
				<tr>
					<td>작성자</td>
					<td><input type="text" name="writer" value="${vo.writer}" readonly="readonly" style="border:none">
						<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/></td>
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
	<!-- 하단 버튼 -->
				<tr>
					<td colspan="2" align="center">
						<sec:authorize access="principal.username=='${vo.writer}' or hasRole('ROLE_ADMIN')">
							<button class="btn btn-primary btn-sm" onclick="goUpdate(${vo.idx})">수정</button>
							<button class="btn btn-danger btn-sm" onclick="goDelete(${vo.idx})">삭제</button>
						</sec:authorize>
						
						<button class="btn btn-primary btn-sm" onClick="location.href='${cpath}/boardInsertForm'">글쓰기</button>	
						<button class="btn btn-success btn-sm" onclick="location.href='${cpath}/replyInsert?idx=${idx}'">답글</button>
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