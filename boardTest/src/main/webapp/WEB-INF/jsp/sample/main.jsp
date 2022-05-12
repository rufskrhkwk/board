<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/cmmn/jstl.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css" />"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/user_style.css" />"/>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.10.2.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/sortable_table.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/fixed_table_rc.js" />"></script>
<script type="text/javascript">
    $(function(){
    	
    });
    	
function dataOnScroll() {
	headerContent.scrollLeft = bodyContent.scrollLeft;
}


function fn_search() {
	var f = document.searchForm;
	f.action = "<c:url value='/sample' />";
	f.submit();
}
</script>
</head>
<body>
	
	<div class="btnWrap alRight w1000 mgT10 mgL10">
		<div class="btn_text_edit w50 fl" title="수정">수정</div>
		<div class="btn_ImgBk_new w50" title="신규">신규</div>
		<div class="btn_text_save w50" title="저장">저장</div>
		<div class="btn_text_search w50" title="조회" onclick="fn_search();">조회</div>
	</div>
	
	<div class="searchWrap mgT10 mgB10 mgL10">
		<form name="searchForm" method="post">
		    <table summary="search" class="tbl_search_1">
		        <tr>
		            <th>코드명</th>
		            <td>
		                <input type="text" name="cd_nm" value="${param.cd_nm }" />
		            </td>
		            <th>사용여부</th>
		            <td>
		            	<select name="use_yn">
		            		<option value="%">전체</option>
		            		<option value="Y" <c:if test="${param.use_yn eq 'Y' }">selected="selected"</c:if>>Y</option>
		            		<option value="N" <c:if test="${param.use_yn eq 'N' }">selected="selected"</c:if>>N</option>
		            	</select>
		            </td>
		        </tr>
		    </table>
		</form>
	</div>
	
	<div class="detailTblWrap mgT30 mgL10">
		<table summary="TEMPLATE_DETAIL" class="tbl_DetailTbl_1">
			<thead>
				<tr>
					<th class="w100">아이템01</th>
					<th class="w100">아이템02</th>
					<th class="w200">아이템03</th>
					<th class="w100">아이템04</th>
					<th class="w100">아이템05</th>
					<th class="w100">아이템06</th>
					<th class="w100">아이템07</th>
					<th class="w100">아이템08</th>
					<th class="w100">아이템09</th>
					<th class="w100">아이템10</th>
					<th class="w100">아이템11</th>
					<th class="w100">아이템12</th>
					<th class="w100">아이템13</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sample }" var="code">
					<tr>
						<td class="w100 alCenter">${code.cdId }</td>
						<td class="w100 alCenter">${code.cd }</td>
						<td class="w200 alCenter">${code.cdNm }</td>
						<td class="w100 alCenter">${code.langSeq }</td>
						<td class="w100 alCenter">${code.dsc }</td>
						<td class="w100 alCenter">${code.mgtItem }</td>
						<td class="w100 alCenter">${code.rmk }</td>
						<td class="w100 alCenter">${code.useYn }</td>
						<td class="w100 alCenter">${code.inqOrd }</td>
						<td class="w100 alCenter">${code.entUno }</td>
						<td class="w100 alCenter">${code.entDtm }</td>
						<td class="w100 alCenter">${code.updUno }</td>
						<td class="w100 alCenter">${code.updDtm }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
</body>
</html>