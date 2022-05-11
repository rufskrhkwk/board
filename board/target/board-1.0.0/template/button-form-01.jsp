<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/cmmn/jstl.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>PEG JAVA Frame 버튼-01</title>
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/common.css" />"/>
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/button_css.css" />"/>

</head>
<body>



<div class="btnWrap alRight w555" style="margin:10px 0 0 30px">
	<div class="btn_text_edit w50 fl" title="수정">수정</div>
	<div class="btn_ImgBk_new w50" title="신규">신규</div>
	<div class="btn_text_save w50" title="저장">저장</div>
	<div class="btn_text_search w50" title="조회">조회</div>
</div>

<div class="btnWrap" style="margin:10px 0 0 30px">
	<ul class="btnSet">
		<li title="접수" class="setBtn_1">접수</li>
		<li title="접수취소" class="setBtn_2">접수취소</li>
	</ul>
	<ul class="btnSet">
		<li title="일괄(접수/계산)" class="setBtn_3">일괄(접수/계산)</li>
		<li title="계산" class="setBtn_4">계산</li>
	</ul>
	<ul class="btnSet">	
		<li title="산출완료" class="setBtn_5">산출완료</li>
		<li title="산출완료 취소" class="setBtn_6">산출완료 취소</li>
	</ul>
	<ul class="btnSet">
		<li title="엑셀다운로드" class="setBtn_7">엑셀다운로드</li>
		<li title="조회" class="setBtn_8">조회</li>
	</ul>
</div>


</body>
</html>