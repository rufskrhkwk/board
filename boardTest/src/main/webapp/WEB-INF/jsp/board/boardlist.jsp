<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="cpath" value="${pageContext.request.contextPath}"/>
<sec:authentication property="principal.username" var="userId"/>
<c:set var='role' value="${SPRING_SECURITY_CONTEXT.authentication.principal.memberAuthority}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="form-group">게시판
				<sec:authorize access="isAuthenticated()">
					<button onclick="location.href='${cpath}/logout'">로그아웃</button> 
				</sec:authorize>
				</div>
				<div align="right">
					<button class="btn btn-primary btn-sm" onClick="location.href='${cpath}/boardInsertForm'">글쓰기</button>	
				</div>
			</div>
	<!-- 게시글 목록 -->		
			<div class="panel-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>글번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일자</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="vo" items="${list}">
							<tr>
								<td>${vo.idx}</td>
								
								<c:choose> <!-- 비밀글이 아니거나 작성자 본인, 관리자는 모든 글을 볼 수  있다. -->
									<c:when test="${vo.secret=='false' || userId == vo.writer || role == 'ROLE_ADMIN'}">
										<td><a href="${cpath}/boardContent/${vo.idx}">${vo.title}</td>
									</c:when>
									<c:otherwise>
										<td>비밀글입니다.</td>
									</c:otherwise>
								</c:choose>
								
								<td>${vo.writer}</td>
								<td>${vo.indate}</td>
								<td>${vo.count}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
	<!-- 하단 페이지 이동 버튼 -->			
				<div align="center">
					<ul class="btn-group pagination">
					    <c:if test="${pageMaker.prev }">
						    <li>
						        <a href='<c:url value="/list?currentPage=${pageMaker.startPage-1}"/>'><i class="fa fa-chevron-left"></i>Prev</a>
						    </li>
					    </c:if>
					    <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
						    <li>
						        <a href='<c:url value="/list?currentPage=${pageNum}&searchType=${keyword.searchType}&keyword=${keyword.keyword}"/>'><i class="fa">${pageNum}</i></a>
						    </li>
					    </c:forEach>
					    <c:if test="${pageMaker.next && pageMaker.endPage >0 }">
						    <li>
						        <a href='<c:url value="/list?currentPage=${pageMaker.endPage+1}"/>'><i class="fa fa-chevron-right"></i>Next</a>
						    </li>
					    </c:if>
					</ul>
				</div>
				
	<!-- 검색 기능 -->		
				<div>
					<form name="search" action="/boardTest/list">
						<table class="pull-right">
							<tr>
								<td><select class="form-control" name="searchType">
										<option value="title">제목</option>
										<option value="writer">작성자</option>
										<option value="contents">내용</option>
								</select></td>
								<td><input type="text" class="form-control"
									placeholder="검색어 입력" name="keyword" maxlength="100"></td>
								<td><button type="submit" class="btn btn-success">검색</button></td>
							</tr>
						</table>
					</form>
				</div>
				
			</div>
			<div class="panel-footer">board test</div>
		</div>
	</div>
</body>
</html>

