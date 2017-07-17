<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編輯國家資訊 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
</head>
<body>
	<form:form action="/Admin/country/edit.do" method="post" modelAttribute="countryBean">
		<table border="1">
			<tr style="display:none">
				<td>流水號</td>
				<td><form:hidden path="co_id" /></td>
			</tr>
			<tr>
				<td>代碼</td>
				<td><form:input path="co_iso" /></td>
			</tr>
			<tr>
				<td>名稱</td>
				<td><form:input path="co_name" /></td>
			</tr>
			<tr>
				<td>電話碼</td>
				<td><form:input path="co_phonecode" /></td>
			</tr>
			<tr>
				<td>排序</td>
				<td><form:input path="co_rank" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="變更" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>