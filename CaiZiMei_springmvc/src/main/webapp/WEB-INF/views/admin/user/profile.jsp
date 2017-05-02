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
			<td>姓名</td>
			<td>${admin.adu_lastname} ${admin.adu_firstname}</td>
		</tr>
		<tr>
			<td>英文名</td>
			<td>${admin.adu_eng_name}</td>
		</tr>
		<tr>
			<td>信箱</td>
			<td>${admin.adu_username}</td>
		</tr>
		<tr>
			<td>手機</td>
			<td>${admin.adu_mobilephone}</td>
		</tr>
		<tr>
			<td>註冊時間</td>
			<td>${admin.adu_signup_time}</td>
		</tr>
		<tr>
			<td>登入次數</td>
			<td>${admin.adu_signin_number}</td>
		</tr>
	</table>
	<p>
		<a href="${root}admin/user/update">修改個人資料</a>
	</p>
</body>
</html>