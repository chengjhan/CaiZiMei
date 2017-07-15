<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增城市 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
</head>
<body>
	<form:form action="/Admin/city/add.do" method="post" modelAttribute="cityBean">
		<table border="1">
			<tr>
				<td>國家</td>
				<td>
					<form:select id="id-input-ci-co-id" path="ci_CountryBean">
						<form:option value="0" label="請選擇國家" />
						<form:options items="${countryList}" itemValue="co_id" itemLabel="co_name" />
					</form:select>
				</td>
			</tr>
			<tr>
				<td>區域</td>
				<td>
					<form:select id="id-input-ci-s-id" path="ci_StateBean">
						<form:option value="0" label="請選擇區域" />
					</form:select>
				</td>
			</tr>
			<tr>
				<td>城市</td>
				<td><form:input path="ci_name" /></td>
			</tr>
			<tr>
				<td>排序</td>
				<td><form:input path="ci_rank" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="新增" /></td>
			</tr>
		</table>
	</form:form>
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/city/add.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>