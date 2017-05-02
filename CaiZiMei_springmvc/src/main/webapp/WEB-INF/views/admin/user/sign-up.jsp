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
	<form action="<c:url value='/admin/user/sign-up.do' />" method="post">
		<div>
			<label for="id-adu-username">帳號</label>
			<input type="text" id="id-adu-username" name="adu_username">
			<span id="id-adu-username-span" style="color: red"></span>
		</div>
		<div>
			<label for="id-adu-password">密碼</label>
			<input type="password" id="id-adu-password" name="adu_password">
		</div>
		<div>
			<label for="id-adu-password-again">確認密碼</label>
			<input type="password" id="id-adu-password-again">
		</div>
		<div>
			<label for="id-adu-lastname">姓氏</label>
			<input type="text" id="id-adu-lastname" name="adu_lastname">
		</div>
		<div>
			<label for="id-adu-firstname">名字</label>
			<input type="text" id="id-adu-firstname" name="adu_firstname">
		</div>
		<div>
			<label for="id-adu-email">信箱</label>
			<input type="text" id="id-adu-email" name="adu_email">
		</div>
		<div>
			<input type="submit" id="id-submit" value="註冊">
		</div>
	</form>
</body>
</html>