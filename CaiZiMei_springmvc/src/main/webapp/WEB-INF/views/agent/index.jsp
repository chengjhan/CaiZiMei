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
	<p>
		<a href="${root}agent/secure/sign-in">登入</a>
	</p>
	<p>
		<a href="${root}agent/secure/forget-password">忘記密碼</a>
	</p>
	<p>
		<a href="${root}agent/secure/sign-out.do">登出</a>
	</p>
	<p>
		<a href="${root}agent/employee/profile">個人資訊</a>
	</p>
	<p>
		<a href="${root}agent/employee/update-password">修改密碼</a>
	</p>
	<p>
		<a href="${root}agent/member/search">會員搜尋</a>
	</p>
</body>
</html>