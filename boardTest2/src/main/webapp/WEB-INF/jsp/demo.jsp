<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<div class="detailTblWrap">
		<table class="tbl_list_1" summary="TEMPLATE_DETAIL" >
			<tr>
				<th class="w100">cd_id</th>
				<th class="w100">cd</th>
				<th class="w100">cd_nm</th>
				<th class="w100">use_yn</th>
				<th class="w100">ent_uno</th>
				<th class="w100">ent_dtm</th>
				<th class="w120">upd_uno</th>
				<th class="w120">upd_dtm</th>
			</tr>
			<c:forEach items="${example }" var="data">
				<tr>
					<td class="alCenter">${data.cdId }</td>
					<td class="alCenter">${data.cd }</td>
					<td class="alCenter">${data.cdNm }</td>
					<td class="alCenter">${data.useYn }</td>
					<td class="alCenter">${data.entUno }</td>
					<td class="alCenter">${data.entDtm }</td>
					<td class="alCenter">${data.updUno }</td>
					<td class="alCenter">${data.updDtm }</td>
				</tr>
			</c:forEach>
		</table>
	</div>

