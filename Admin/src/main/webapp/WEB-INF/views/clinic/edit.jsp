<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編輯診所資訊 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
</head>
<body>
	<form:form action="/Admin/clinic/edit.do" method="post" modelAttribute="clinicBean">
		<table border="1">
			<tr style="display:none">
				<td>流水號</td>
				<td><form:hidden path="cl_id" /></td>
			</tr>
			<tr>
				<td>診所</td>
				<td><form:input path="cl_name" /></td>
			</tr>
			<tr>
				<td>診所英文</td>
				<td><form:input path="cl_eng_name" /></td>
			</tr>
			<tr>
				<td>電話</td>
				<td><form:input path="cl_localphone" /></td>
			</tr>
			<tr>
				<td>國家</td>
				<td><form:input path="cl_CountryBean" /></td>
			</tr>
			<tr>
				<td>區域</td>
				<td><form:input path="cl_StateBean" /></td>
			</tr>
			<tr>
				<td>城市</td>
				<td><form:input path="cl_CityBean" /></td>
			</tr>
			<tr>
				<td>地址</td>
				<td><form:input path="cl_address" /></td>
			</tr>
			<tr>
				<td>網址</td>
				<td><form:input path="cl_url" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="新增" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>