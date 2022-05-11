<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/cmmn/jstl.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title><tiles:getAsString name="title"/></title>
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/style.css" />"/>
<script type="text/javascript">
    $(function(){
    	
    });
</script>
</head>
<body>


	<tiles:insertAttribute name="header" />
	
		
	<tiles:insertAttribute name="content" />	 


	<tiles:insertAttribute name="footer" />


</body>
</html>