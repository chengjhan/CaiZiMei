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
	<form action="<c:url value='/member/sign-up.controller' />" method="post">
		<div>
			<label for="id-m-username">帳號</label>
			<input type="text" id="id-m-username" name="m_username">
		</div>
		<div>
			<label for="id-m-password">密碼</label>
			<input type="password" id="id-m-password" name="m_password">
		</div>
		<div>
			<label for="id-m-password-again">確認密碼</label>
			<input type="password" id="id-m-password-again">
		</div>
		<div>
			<label for="id-m-lastname">姓氏</label>
			<input type="text" id="id-m-lastname" name="m_lastname">
		</div>
		<div>
			<label for="id-m-firstname">名字</label>
			<input type="text" id="id-m-firstname" name="m_firstname">
		</div>
		<div>
			<label for="id-m-birth">生日</label>
			<input type="text" id="id-m-birth" name="m_birth_year">
			<input type="text" id="id-m-birth" name="m_birth_month">
			<input type="text" id="id-m-birth" name="m_birth_date">
		</div>
		<div>
			<label for="id-m-sex">性別</label>
			<select id="id-m-sex" name="m_sex">
				<option value="1">男</option>
				<option value="0">女</option>
			</select>
		</div>
		<div>
			<label for="id-m-height">身高</label>
			<input type="text" id="id-m-height" name="m_height">
		</div>
		<div>
			<label for="id-m-weight">體重</label>
			<input type="text" id="id-m-weight" name="m_weight">
		</div>
		<div>
			<label for="id-m-telephone">電話</label>
			<input type="text" id="id-m-telephone" name="m_telephone_front">
			<input type="text" id="id-m-telephone" name="m_telephone_back">
		</div>
		<div>
			<label for="id-m-address">地址</label>
			<input type="text" id="id-m-address" name="m_address">
		</div>
		<div>
			<label for="id-m-email">信箱</label>
			<input type="text" id="id-m-email" name="m_email">
		</div>
		<div>
			<input type="submit" id="id-submit" value="註冊">
		</div>
	</form>
</body>
</html>