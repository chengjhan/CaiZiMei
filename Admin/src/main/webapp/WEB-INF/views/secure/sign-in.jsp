<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登入 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
</head>
<body>
	<form action="<c:url value='/secure/sign-in.do' />" method="post">
		<div>
			<label for="id-input-a-username">帳號</label>
			<input type="text" id="id-input-a-username" name="a_username">
		</div>
		<div>
			<label for="id-input-a-password">密碼</label>
			<input type="password" id="id-input-a-password" name="a_password">
		</div>
		<div>
			<input type="submit" id="id-input-submit" value="登入">
		</div>
		<div style="color:red">${error}</div>
	</form>
	<p>
		<a href="<%=request.getContextPath()%>/secure/forget-password">忘記密碼</a>
	</p>
</body>
</html>