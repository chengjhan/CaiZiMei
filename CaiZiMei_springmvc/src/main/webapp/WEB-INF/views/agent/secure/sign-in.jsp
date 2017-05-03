<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value='/agent/secure/sign-in.do' />" method="post">
		<div>
			<label for="id-au-username">帳號</label>
			<input type="text" id="id-au-username" name="au_username">
		</div>
		<div>
			<label for="id-au-password">密碼</label>
			<input type="password" id="id-au-password" name="au_password">
		</div>
		<div>
			<input type="submit" id="id-submit" value="登入">
		</div>
		<div style="color:red">${error}</div>
	</form>
</body>
</html>