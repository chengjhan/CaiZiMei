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
	<form action="<c:url value='/admin/member/admin-sign-up.do' />" method="post">
		<div>
			<label for="id-m-username">帳號</label>
			<input type="text" id="id-m-username" name="m_username">
			<span id="id-m-username-span" style="color: red"></span>
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
			<label for="id-m-limit">權限</label>
			<select id="id-m-limit" name="m_limit">
				<option value="1">代理商</option>
				<option value="2">總公司</option>
			</select>
		</div>
		<div>
			<input type="submit" id="id-submit" value="註冊">
		</div>
	</form>
	<script>
		// 帳號驗證
		$("#id-m-username").blur(function(){
			var m_username = $("#id-m-username").val();
			var m_username_span = $("#id-m-username-span");
			m_username_span.empty();
			$.get("${root}member/select-username.ajax", { "m_username": m_username }, function(data){
				if(data == "1"){
					m_username_span.text("已使用");					
				}
			});
		});
	</script>
</body>
</html>