<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/cmmn/jstl.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>PEG JAVA Frame</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css" />"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/button_css.css" />"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/table_search.css" />"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/table_data.css" />"/>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.10.2.min.js" />"></script>
<script type="text/javascript">
function fn_search() {
	var f = document.searchForm;
	f.action = "<c:url value='/sample' />";
	f.method = "post";
	f.submit();
}
</script>



</head>
<body>


<!-- 버튼영역 -->
	<div class="btnWrap alRight w555" style="margin:10px 0 0 30px">
		<div class="btn_text_edit w50" title="수정">수정</div>
		<div class="btn_ImgBk_new w50" title="신규">신규</div>
		<div class="btn_text_save w50" title="저장">저장</div>
		<div class="btn_text_search w50" title="조회" onclick="fn_search();">조회</div>
	</div>
<!-- 버튼영역 -->
	
	
	
<!-- 검색영역 -->
<form name="searchForm">
	<div class="searchWrap mgT10 mgB10">
	    <table summary="search" class="tbl_search_1">
	        <tr>
	            <th>코드이름</th>
	            <td>
	                <input type="text" name="cd_nm" value="${param.cd_nm }" />
	            </td>
	            <th>사용여부</th>
	            <td>
	            	<select name="use_yn">
	            		<option value="%">전체</option>
	            		<option value="Y">Y</option>
	            		<option value="N">N</option>
	            	</select>
	            </td>
	        </tr>
	    </table>
	</div>
</form>
<!-- 검색영역 -->




<!-- 리스트 영역 -->
	<div class="detailTblWrap mgT30">
		<table summary="TEMPLATE_DETAIL" class="tbl_DetailTbl_1">
			<thead>
				<tr>
					<th class="w100">CD</th>
					<th class="w100">CD_NM</th>
					<th class="w100">LANG_SEQ</th>
					<th class="w100">DSC</th>
					<th class="w100">MGT_ITEM</th>
					<th class="w100">RMK</th>
					<th class="w100">USE_YN</th>
					<th class="w100">INQ_ORD</th>
					<th class="w100">ENT_UNO</th>
					<th class="w100">ENT_DTM</th>
					<th class="w100">UPDT_UNO</th>
					<th class="w100">UPD_DTM</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="code">
					<tr>
						<td>${code.cd }</td>
						<td class="alRight pdR3">${code.cdNm }</td>
						<td class="alRight pdR3">${code.langSeq }</td>
						<td class="alRight pdR3">${code.dsc }</td>
						<td class="alRight pdR3">${code.mgtItem }</td>
						<td class="alRight pdR3">${code.rmk }</td>
						<td class="alRight pdR3">${code.useYn }</td>
						<td class="alRight pdR3">${code.inqOrd }</td>
						<td class="alRight pdR3">${code.entUno }</td>
						<td class="alRight pdR3">${code.entDtm }</td>
						<td class="alRight pdR3">${code.updUno }</td>
						<td class="alRight pdR3">${code.updDtm }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<!-- 리스트 영역 -->







</body>
</html>