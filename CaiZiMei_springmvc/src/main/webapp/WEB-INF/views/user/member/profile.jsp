<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:url value="/" var="root" />
	<table border="1">
		<tr>
			<td>信箱</td>
			<td>${user.m_username}</td>
		</tr>
		<tr>
			<td>姓名</td>
			<td>${user.m_lastname} ${user.m_firstname}</td>
		</tr>
		<tr>
			<td>生日</td>
			<td>${user.m_birth}</td>
		</tr>
		<tr>
			<td>性別</td>
			<td>${user.m_sex}</td>
		</tr>
		<tr>
			<td>身高</td>
			<td>${user.m_height}</td>
		</tr>
		<tr>
			<td>體重</td>
			<td>${user.m_weight}</td>
		</tr>
		<tr>
			<td>電話</td>
			<td>${user.m_localphone}</td>
		</tr>
		<tr>
			<td>手機</td>
			<td>${user.m_mobilephone}</td>
		</tr>
		<tr>
			<td>地址</td>
			<td>${user.m_zipcode} ${user.m_country} ${user.m_city} ${user.m_region} ${user.m_address}</td>
		</tr>
		<tr>
			<td>註冊時間</td>
			<td>${user.m_signup_time}</td>
		</tr>
		<tr>
			<td>登入次數</td>
			<td>${user.m_signin_number}</td>
		</tr>
		<tr>
			<td>上次登入IP</td>
			<td>${lastSignInIp}</td>
		</tr>
		<tr>
			<td>上次登入時間</td>
			<td>${lastSignInTime}</td>
		</tr>
	</table>
	<p>
		<a href="${root}user/member/update">修改個人資料</a>
	</p>
</body>
</html>