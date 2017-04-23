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
	<p>Welcome ${user.m_username}</p>
	<p>登入次數: ${user.m_signin_number}</p>
	<p>上次登入IP: ${lastSignInIp}</p>
	<p>上次登入時間: ${lastSignInTime}</p>
	<p>
		<a href="${root}user/secure/sign-in">登入</a>
	</p>
	<p>
		<a href="${root}user/secure/forget-password">忘記密碼</a>
	</p>
	<p>
		<a href="${root}user/secure/sign-out.do">登出</a>
	</p>
	<p>
		<a href="${root}user/member/sign-up">註冊</a>
	</p>
	<p>
		<a href="${root}user/member/profile">個人資訊</a>
	</p>
	<p>
		<a href="${root}user/member/update-password">修改密碼</a>
	</p>
	<p>
		<a href="${root}user/purchase/order">訂購</a>
	</p>
	<p>
		<a href="${root}user/purchase/select.do">訂購查詢</a>
	</p>
	<p>
		<a href="${root}agent/index">代理商系統</a>
	</p>
	<p>
		<a href="${root}admin/index">後台管理系統</a>
	</p>
</body>
</html>