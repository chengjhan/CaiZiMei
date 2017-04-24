<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<c:url value="/" var="root" />
	<form action="<c:url value='/service/specialist/sign-up.do' />" method="post">
		<div>
			<label for="id-s-username">帳號</label>
			<input type="text" id="id-s-username" name="s_username">
			<span id="id-s-usernams-span" style="color: red"></span>
		</div>
		<div>
			<label for="id-s-password">密碼</label>
			<input type="password" id="id-s-password" name="s_password">
		</div>
		<div>
			<label for="id-s-password-again">確認密碼</label>
			<input type="password" id="id-s-password-again">
		</div>
		<div>
			<label for="id-s-lastname">姓氏</label>
			<input type="text" id="id-s-lastname" name="s_lastname">
		</div>
		<div>
			<label for="id-s-firstname">名字</label>
			<input type="text" id="id-s-firstname" name="s_firstname">
		</div>
		<div>
			<label for="id-s-email">信箱</label>
			<input type="text" id="id-s-email" name="s_email">
		</div>
		<div>
			<label for="id-s-c-id">診所</label>
			<select id="id-s-c-id" name="s_c_id">
				<option value="0">請選擇診所</option>
			</select>
		</div>
		<div>
			<input type="submit" id="id-submit" value="註冊">
		</div>
	</form>
</body>
</html>