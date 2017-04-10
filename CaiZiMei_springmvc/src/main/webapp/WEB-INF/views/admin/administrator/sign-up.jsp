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
	<form action="<c:url value='/admin/administrator/sign-up.do' />" method="post">
		<div>
			<label for="id-a-username">帳號</label>
			<input type="text" id="id-a-username" name="a_username">
			<span id="id-a-username-span" style="color: red"></span>
		</div>
		<div>
			<label for="id-a-password">密碼</label>
			<input type="password" id="id-a-password" name="a_password">
		</div>
		<div>
			<label for="id-a-password-again">確認密碼</label>
			<input type="password" id="id-a-password-again">
		</div>
		<div>
			<label for="id-a-lastname">姓氏</label>
			<input type="text" id="id-a-lastname" name="a_lastname">
		</div>
		<div>
			<label for="id-a-firstname">名字</label>
			<input type="text" id="id-a-firstname" name="a_firstname">
		</div>
		<div>
			<label for="id-a-email">信箱</label>
			<input type="text" id="id-a-email" name="a_email">
		</div>
		<div>
			<input type="submit" id="id-submit" value="註冊">
		</div>
	</form>
</body>
</html>