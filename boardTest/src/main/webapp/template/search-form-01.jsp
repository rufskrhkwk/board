<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/cmmn/jstl.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>PEG JAVA Frame 검색창-01</title>
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/common.css" />"/>
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/table_search.css" />"/>
<script type="text/javascript">

</script>
</head>
<body>





<div class="searchWrap mgT10 mgB10">
    <table summary="search" class="tbl_search_1">
        <tr>
            <th>기준년도</th>
            <td>
                <input type="text" name="" id="" value="2018" />
            </td>
            <th>사번</th>
            <td>
                <input type="text" name="" id="" value="1589445" style="width:88%"/>
                <div class="btn_Icon_search" title="찾기"></div>
            </td>
        </tr>
        <tr>
            <th>정산구분</th>
            <td>
                <select name="" id="">
                    <option value="1">중도퇴사</option>
                    <option value="2">계속근로</option>
                </select>
            </td>
            <th class="color1">진행상태</th>
            <td>
                <select name="" id="">
                    <option value="1">미제출</option>
                    <option value="2">제출</option>
                    <option value="3">접수</option>
                </select>
            </td>
        </tr>
    </table>
</div>


</body>
</html>