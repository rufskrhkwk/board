<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="cpath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title><meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h2>Welcome</h2>
		<div class="panel panel-default">
			<div class="panel-heading">Spring Member</div>
			<div class="panel-body">
				<!-- "isAnonymous()" 인증받지 않은 이용자 = 로그인하지 않은 경우 -->
				<sec:authorize access="isAnonymous()">
					<form action="${cpath}/memberLogin" method="post">
						<table class="table table-hover table-bordered">
								<tr>
									<td>아이디</td>
									<td><input type="text" id="id" name="id"></td>
								</tr>
								<tr>
									<td>비밀번호</td>
									<td><input type="password" id="pw" name="pw">
										<!-- 서버에 들어온 요청이 실제 서버에서 허용한 요청이 맞는지 확인하기 위한 토큰 -->
										<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center"> <button type='submit'>로그인</button>
									</td>
								</tr>
						</table>
					</form>
				</sec:authorize>
				
				<!--"isAuthenticated()" 인증받은 사용자 = 로그인한 경우-->
				<sec:authorize access="isAuthenticated()">
					<label><sec:authentication property="principal"/>님 방문을 환영합니다.</label>
					<button onclick="location.href='${cpath}/logout'">로그아웃</button>
					<button onclick="location.href='${cpath}/list'">게시판으로 가기</button>
				</sec:authorize>
						
				
			</div>
			<div class="panel-footer">스프링 연습</div>
		</div>
	</div>
</body>
</html>