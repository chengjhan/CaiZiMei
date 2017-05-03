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
	<form action="<c:url value='/admin/clinic-user/sign-up.do' />" method="post">
		<div>
			<label for="id-cu-username">帳號</label>
			<input type="text" id="id-cu-username" name="cu_username">
			<span id="id-cu-usernams-span" style="color: red"></span>
		</div>
		<div>
			<label for="id-cu-password">密碼</label>
			<input type="password" id="id-cu-password" name="cu_password">
		</div>
		<div>
			<label for="id-cu-password-again">確認密碼</label>
			<input type="password" id="id-cu-password-again">
		</div>
		<div>
			<label for="id-cu-lastname">姓氏</label>
			<input type="text" id="id-cu-lastname" name="cu_lastname">
		</div>
		<div>
			<label for="id-cu-firstname">名字</label>
			<input type="text" id="id-cu-firstname" name="cu_firstname">
		</div>
		<div>
			<label for="id-cu-email">信箱</label>
			<input type="text" id="id-cu-email" name="cu_email">
		</div>
		<div>
			<label for="id-cu-c-id">診所</label>
			<select id="id-cu-c-id" name="cu_c_id">
				<option value="0">請選擇診所</option>
			</select>
		</div>
		<div>
			<input type="submit" id="id-submit" value="註冊">
		</div>
	</form>
	<script>
		$(document).ready(function(){
			$.getJSON("${root}admin/clinic/select.ajax", function(data){
				$.each(data, function(index, clinic){
					var clinic_option = $("<option value=" + clinic.c_id + "></option>").append(clinic.c_name);
					$("#id-cu-c-id").append(clinic_option);
				});
			});
		});
	</script>
</body>
</html>