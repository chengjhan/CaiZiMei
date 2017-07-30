<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增加盟店 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
</head>
<body>
	<form:form action="/Admin/franchisee/add.do" method="post" modelAttribute="baseBean">
		<table border="1">
			<tr>
				<td>名稱</td>
				<td><form:input path="ba_name" /></td>
			</tr>
			<tr>
				<td>英文名稱</td>
				<td><form:input path="ba_eng_name" /></td>
			</tr>
			<tr>
				<td>電話</td>
				<td><form:input path="ba_localphone" /></td>
			</tr>
			<tr>
				<td>國家</td>
				<td>
					<form:select id="id-input-ba-co-id" path="ba_CountryBean">
						<form:option value="0" label="請選擇國家" />
						<form:options items="${countryList}" itemValue="co_id" itemLabel="co_name" />
					</form:select>
				</td>
			</tr>
			<tr>
				<td>區域</td>
				<td>
					<form:select id="id-input-ba-st-id" path="ba_StateBean">
						<form:option value="0" label="請選擇區域" />
					</form:select>
				</td>
			</tr>
			<tr>
				<td>城市</td>
				<td>
					<form:select id="id-input-ba-ci-id" path="ba_CityBean">
						<form:option value="0" label="請選擇城市" />
					</form:select>
				</td>
			</tr>
			<tr>
				<td>地址</td>
				<td><form:input path="ba_address" /></td>
			</tr>
			<tr>
				<td>網址</td>
				<td><form:input path="ba_url" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="新增" /></td>
			</tr>
		</table>
	</form:form>

	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/base/add.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>