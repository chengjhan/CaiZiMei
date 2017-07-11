<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>個人資訊 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
</head>
<body>
	<table border="1">
		<tr>
			<td>姓名</td>
			<td>${admin.a_lastname} ${admin.a_firstname}</td>
		</tr>
		<tr>
			<td>信箱</td>
			<td>${admin.a_email}</td>
		</tr>
		<tr>
			<fmt:formatDate value="${admin.a_signup_time}" var="a_signup_time" pattern="yyyy-MM-dd hh:mm:ss" />
			<td>註冊時間</td>
			<td>${a_signup_time}</td>
		</tr>
		<tr>
			<td>登入次數</td>
			<td>${admin.a_signin_number}</td>
		</tr>
	</table>
	<p>
		<a href="<%=request.getContextPath()%>/admin/edit">編輯個人資訊</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/">首頁</a>
	</p>
</body>
</html>