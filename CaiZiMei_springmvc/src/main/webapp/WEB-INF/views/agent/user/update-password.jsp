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
	<form action="<c:url value='/agent/user/update-password.do' />" method="post">
		<div>
			<label for="id-au-password">舊密碼</label>
			<input type="password" id="id-au-password" name="au_password">
		</div>
		<div>
			<label for="id-au-password-new">新密碼</label>
			<input type="password" id="id-au-password-new" name="au_password_new">
		</div>
		<div>
			<label for="id-au-password-new-again">新密碼</label>
			<input type="password" id="id-au-password-new-again" name="au_password_new_again">
		</div>
		<div>
			<input type="submit" id="id-submit" value="確定">
		</div>
	</form>
</body>
</html>