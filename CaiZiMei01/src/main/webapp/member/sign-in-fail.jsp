<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p style="color:red">帳號或密碼錯誤</p>
	<form action="<c:url value='/member/sign-in.controller' />" method="post">
		<div>
			<label for="id-m-username">帳號</label>
			<input type="text" id="id-m-username" name="m_username">
		</div>
		<div>
			<label for="id-m-password">密碼</label>
			<input type="password" id="id-m-password" name="m_password">
		</div>
		<div>
			<input type="submit" id="id-submit" value="登入">
		</div>
	</form>
</body>
</html>