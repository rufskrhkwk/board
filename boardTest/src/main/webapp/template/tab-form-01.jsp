<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/cmmn/jstl.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>PEG JAVA Frame 탭-01</title>
<script language="javascript" src="<c:url value="resources/js/jquery-1.10.2.min.js" />"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/common.css" />"/>
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/tab_set.css" />"/>
<script type="text/javascript">
$(function(){
				
	// TAB 메뉴와 상세화면 보이기
	$("li[id^='tab_']").click(function(){
		var index = $(this).index();
		var myID = $(this).attr("id");

		if (myID.indexOf("on") == -1) {
			//자신의 class 추가, 형제의 class 제거
			$(this).addClass("on").siblings().each(function(index, e){
				var clazz = $(e).attr("class");
				if (typeof clazz != 'undefined') {
					$(e).removeClass(clazz);			
				}
			});	

			//자신의 내용 보이기, 형제의 내용 감추기
			$("div[id^='tabPanel_']").each(function(i, e){
				if (i == index) {
					$(e).show();
				} else {
					$(e).hide();
				}
			});
		}
	});
		
});
</script>
</head>
<body>



	<div class="tabWrap">
		<ul class="tabSet w500">
			<li id="tab_1" class="on">전체</li>
			<li id="tab_2">구분1</li>
			<li id="tab_3">구분2</li>
			<li id="tab_4">구분3</li>
			<li id="tab_5">구분4</li>
		</ul>
	</div>
	<div class="tabPanelWrap w500">
		<div id="tabPanel_1">전체</div>
		<div id="tabPanel_2" class="disNone">구분1</div>
		<div id="tabPanel_3" class="disNone">구분2</div>
		<div id="tabPanel_4" class="disNone">구분3</div>
		<div id="tabPanel_5" class="disNone">구분4</div>
	</div>




</body>
</html>