<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編輯加盟店資訊 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
</head>
<body>
	<form:form action="/Admin/franchisee/edit.do" method="post" modelAttribute="franchiseeBean">
		<table border="1">
			<tr style="display:none">
				<td>流水號</td>
				<td><form:hidden path="fr_id" /></td>
			</tr>
			<tr>
				<td>名稱</td>
				<td><form:input path="fr_name" /></td>
			</tr>
			<tr>
				<td>英文名稱</td>
				<td><form:input path="fr_eng_name" /></td>
			</tr>
			<tr>
				<td>電話</td>
				<td><form:input path="fr_localphone" /></td>
			</tr>
			<tr>
				<td>國家</td>
				<td>
					<form:select id="id-input-fr-co-id" path="fr_CountryBean">
						<form:option value="0" label="請選擇國家" />
						<form:options items="${countryList}" itemValue="co_id" itemLabel="co_name" />
					</form:select>
				</td>
			</tr>
			<tr>
				<td>區域</td>
				<td>
					<form:select id="id-input-fr-st-id" path="fr_StateBean">
						<form:option value="0" label="請選擇區域" />
						<form:options items="${stateList}" itemValue="st_id" itemLabel="st_name" />
					</form:select>
				</td>
			</tr>
			<tr>
				<td>城市</td>
				<td>
					<form:select id="id-input-fr-ci-id" path="fr_CityBean">
						<form:option value="0" label="請選擇城市" />
						<form:options items="${cityList}" itemValue="ci_id" itemLabel="ci_name" />
					</form:select>
				</td>
			</tr>
			<tr>
				<td>地址</td>
				<td><form:input path="fr_address" /></td>
			</tr>
			<tr>
				<td>網址</td>
				<td><form:input path="fr_url" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="變更" /></td>
			</tr>
		</table>
	</form:form>
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/franchisee/edit.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>