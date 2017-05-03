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
	<form action="<c:url value='/admin/agent-user/sign-up.do' />" method="post">
		<div>
			<label for="id-au-username">帳號</label>
			<input type="text" id="id-au-username" name="au_username">
			<span id="id-au-username-span" style="color: red"></span>
		</div>
		<div>
			<label for="id-au-password">密碼</label>
			<input type="password" id="id-au-password" name="au_password">
		</div>
		<div>
			<label for="id-au-password-again">確認密碼</label>
			<input type="password" id="id-au-password-again">
		</div>
		<div>
			<label for="id-au-lastname">姓氏</label>
			<input type="text" id="id-au-lastname" name="au_lastname">
		</div>
		<div>
			<label for="id-au-firstname">名字</label>
			<input type="text" id="id-au-firstname" name="au_firstname">
		</div>
		<div>
			<label for="id-au-email">信箱</label>
			<input type="text" id="id-au-email" name="au_email">
		</div>
		<div>
			<label for="id-au-a-id">代理商</label>
			<select id="id-au-a-id" name="au_a_id">
				<option value="0">請選擇公司</option>
			</select>
		</div>
		<div>
			<input type="submit" id="id-submit" value="註冊">
		</div>
	</form>
	<script>
		$(document).ready(function(){
			$.getJSON("${root}admin/agent/select-by-status.ajax", function(data){
				$.each(data, function(index, agent){
					var agent_option = $("<option value=" + agent.a_id + "></option>").append(agent.a_name);
					$("#id-au-a-id").append(agent_option);
				});
			});
		});
	</script>
</body>
</html>