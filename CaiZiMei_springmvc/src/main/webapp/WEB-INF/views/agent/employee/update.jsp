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
	<form action="<c:url value='/agent/employee/update.do' />" method="post">
		<div>
			<label for="id-e-lastname">姓氏</label>
			<input type="text" id="id-e-lastname" name="e_lastname" value="${agent.e_lastname}">
		</div>
		<div>
			<label for="id-e-firstname">名字</label>
			<input type="text" id="id-e-firstname" name="e_firstname" value="${agent.e_firstname}">
		</div>
		<div>
			<label for="id-e-eng-name">英文名</label>
			<input type="text" id="id-e-eng-name" name="e_eng_name" value="${agent.e_eng_name}">
		</div>
		<div>
			<label for="id-e-email">信箱</label>
			<input type="text" id="id-e-email" name="e_email" value="${agent.e_email}">
		</div>
		<div>
			<label for="id-e-mobilephone">手機</label>
			<input type="text" id="id-e-mobilephone" name="e_mobilephone" value="${agent.e_mobilephone}">
		</div>
		<div>
			<input type="submit" id="id-submit" value="修改">
		</div>
	</form>
</body>
</html>