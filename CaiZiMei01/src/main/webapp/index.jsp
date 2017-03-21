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
		<a href="<c:url value='/member/sign-in.jsp' />">登入</a>
		<a href="${root}member/sign-in">登入</a>
	</p>
	<p>
		<a href="<c:url value='/member/sign-up.jsp' />">註冊</a>
	</p>
	<p>
		<a href="${root}member/sign-out.controller">登出</a>
	</p>
	<p>
		<a href="<c:url value='/member/update.jsp' />">修改會員資料</a>
	</p>
	<p>
		<a href="<c:url value='/member/update-password.jsp' />">修改密碼</a>
	</p>
</body>
</html>