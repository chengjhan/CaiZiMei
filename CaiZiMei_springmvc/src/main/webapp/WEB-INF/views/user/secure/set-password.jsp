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
	<form action="<c:url value='/user/secure/set-password.do' />" method="post">
		<div>
			<label for="id-m-password">驗證碼</label>
			<input type="password" id="id-m-password" name="m_password">
		</div>
		<div>
			<label for="id-m-password-new">新密碼</label>
			<input type="password" id="id-m-password-new" name="m_password_new">
		</div>
		<div>
			<label for="id-m-password-new-again">新密碼</label>
			<input type="password" id="id-m-password-new-again" name="m_password_new_again">
		</div>
		<div>
			<input type="submit" id="id-submit" value="確定">
		</div>
	</form>
</body>
</html>