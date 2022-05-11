<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<script type="text/javascript" src="<c:url value="/validator" />"></script>
<validator:javascript formName="demoForm" staticJavascript="false" xhtml="true" cdata="false"/>


<script type="text/javascript">
function fn_submit() {
	var form = document.sampleForm;
	if(!validateDemoForm(form)) {
		return;
	} else {
		form.submit();
	}
}
</script>



</head>
<body>


	<form:form commandName="test" name="sampleForm" method="post" action="test01">
		<table>
			<tr>
				<th>이름</th>
				<td>
					<form:input title="String" path="sampleNm" size="60" cssStyle="width:100%" />
				</td>
			</tr>
			<tr>
				<th>생일</th>
				<td>
					<form:input title="Date" path="sampleDate" size="60" cssStyle="width:100%" />
				</td>
			</tr>
			<tr>
				<td>
					<button onclick="fn_submit(); return false;">테스트</button>
				</td>
			</tr>
		</table>
	</form:form>



</body>
</html>