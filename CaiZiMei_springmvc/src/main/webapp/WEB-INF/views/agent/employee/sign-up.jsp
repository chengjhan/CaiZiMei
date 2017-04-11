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
	<form action="<c:url value='/agent/employee/sign-up.do' />" method="post">
		<div>
			<label for="id-e-username">帳號</label>
			<input type="text" id="id-e-username" name="e_username">
			<span id="id-e-username-span" style="color: red"></span>
		</div>
		<div>
			<label for="id-e-password">密碼</label>
			<input type="password" id="id-e-password" name="e_password">
		</div>
		<div>
			<label for="id-e-password-again">確認密碼</label>
			<input type="password" id="id-e-password-again">
		</div>
		<div>
			<label for="id-e-lastname">姓氏</label>
			<input type="text" id="id-e-lastname" name="e_lastname">
		</div>
		<div>
			<label for="id-e-firstname">名字</label>
			<input type="text" id="id-e-firstname" name="e_firstname">
		</div>
		<div>
			<label for="id-e-email">信箱</label>
			<input type="text" id="id-e-email" name="e_email">
		</div>
		<div>
			<label for="id-e-com-id">公司</label>
			<select id="id-e-com-id" name="e_com_id">
				<option value="0">請選擇公司</option>
			</select>
		</div>
		<div>
			<input type="submit" id="id-submit" value="註冊">
		</div>
	</form>
	<script>
		$(document).ready(function(){
			$.getJSON("${root}admin/company/select.ajax", function(data){
				$.each(data, function(index, company){
					var company_option = $("<option value=" + company.com_id + "></option>").append(company.com_name);
					$("#id-e-com-id").append(company_option);
				});
			});
		});
	</script>
</body>
</html>