<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/cmmn/jstl.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>PEG JAVA Frame 리스트-스크롤-01</title>
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/common.css" />"/>
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/table_list_detail.css" />"/>
<script type="text/javascript" src="<c:url value="resources/js/jquery-1.10.2.min.js" />"></script>
<script type="text/javascript" src="<c:url value="resources/js/sortable_table.js" />"></script>
<script type="text/javascript" src="<c:url value="resources/js/fixed_table_rc.js" />"></script>
<script type="text/javascript">
    $(function(){
    	
    });
    	
	function dataOnScroll() {
	   headerContent.scrollLeft = bodyContent.scrollLeft;
	}
</script>
</head>
<body>



<div class="listTblWrap">
    <div class="listTblWrap_Header" id="headerContent">
		<table summary="TEMPLATE_HEADER" class="tbl_Header_1">
			<caption>TEMPLATE_HEADER</caption>
			<thead>
				<tr>
					<th class="w100">1</th>
					<th class="w200">2</th>
					<th class="w100">3</th>
					<th class="w100">4</th>
					<th class="w83">5</th>
					<th class="w100">6</th>
					<th class="w100">7</th>
					<th class="w100">8</th>
					<th class="w100">9</th>
					<th class="w50">10</th>
					<th class="w50">11</th>
					<th class="w50">12</th>
					<th class="w50">13</th>
					<th class="w17"></th>
				</tr>
			</thead> 
		</table>
	</div>
	
	<div class="listTblWrap_Body" id="bodyContent" onscroll="dataOnScroll()">
		<table summary="TEMPLATE_BODY" class="tbl_Body_1">
			<caption>TEMPLATE_BODY</caption>
			<tbody>
				<tr>
					<td class="alCenter w100"><input type="checkbox" name="" id="" class="ipt_checkbox_1" title="" /></td>
					<td class="w200"><input type="text" name="" id="" class="ipt_text_1" value="tdata" /></td>
					<td class="w100">tdata</td>
					<td class="w100">tdata</td>
					<td class="w83">tdata</td>
					<td class="w100">tdata</td>
					<td class="w100">tdata</td>
					<td class="w100">tdata</td>
					<td class="w100">tdata</td>
					<td class="w50">10</td>
					<td class="w50">11</td>
					<td class="w50">12</td>
					<td class="w50">13</td>
				</tr>
				<tr>
					<td class="alCenter"><input type="checkbox" name="" id="" class="ipt_checkbox_1" title="" /></td>
					<td>
						<select name="" id="">
							<option value="1">A</option>
							<option value="2">B</option>
							<option value="3">C</option>
						</select>
					</td>
					<td>tdata</td>
					<td>tdata</td>
					<td>tdata</td>
					<td>tdata</td>
					<td class="alRight">30,000</td>
					<td class="alRight">40,000</td>
					<td>tdata</td>
					<td>10</td>
					<td>11</td>
					<td>12</td>
					<td>13</td>
				</tr>
				<tr>
					<td class="alCenter"><input type="checkbox" name="" id="" class="ipt_checkbox_1" title="" /></td>
					<td>tdata</td>
					<td>tdata</td>
					<td>tdata</td>
					<td>tdata</td>
					<td>tdata</td>
					<td class="alCenter">PEGSYSTEM</td>
					<td class="alCenter">2016~2017</td>
					<td>tdata</td>
					<td>10</td>
					<td>11</td>
					<td>12</td>
					<td>13</td>
				</tr>
				<tr>
					<td class="alCenter"><input type="checkbox" name="" id="" class="ipt_checkbox_1" title="" /></td>
					<td>tdata</td>
					<td>tdata</td>
					<td>tdata</td>
					<td>tdata</td>
					<td>tdata</td>
					<td>tdata</td>
					<td>tdata</td>
					<td>tdata</td>
					<td>10</td>
					<td>11</td>
					<td>12</td>
					<td>13</td>
				</tr>
				<tr>
					<td class="alCenter"><input type="checkbox" name="" id="" class="ipt_checkbox_1" title="" /></td>
					<td>tdata</td>
					<td>tdata</td>
					<td>tdata</td>
					<td>tdata</td>
					<td>tdata</td>
					<td>tdata</td>
					<td>tdata</td>
					<td>tdata</td>
					<td>10</td>
					<td>11</td>
					<td>12</td>
					<td>13</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>
	
</div>






</body>
</html>