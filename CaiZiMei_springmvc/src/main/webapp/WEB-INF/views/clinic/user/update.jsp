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
	<form action="<c:url value='/clinic/user/update.do' />" method="post">
		<div>
			<label for="id-cu-lastname">姓氏</label>
			<input type="text" id="id-cu-lastname" name="cu_lastname" value="${clinic.cu_lastname}">
		</div>
		<div>
			<label for="id-cu-firstname">名字</label>
			<input type="text" id="id-cu-firstname" name="cu_firstname" value="${clinic.cu_firstname}">
		</div>
		<div>
			<label for="id-cu-eng-name">英文名</label>
			<input type="text" id="id-cu-eng-name" name="cu_eng_name" value="${clinic.cu_eng_name}">
		</div>
		<div>
			<label for="id-cu-email">信箱</label>
			<input type="text" id="id-cu-email" name="cu_email" value="${clinic.cu_email}">
		</div>
		<div>
			<label for="id-cu-mobilephone">手機</label>
			<input type="text" id="id-cu-mobilephone" name="cu_mobilephone" value="${clinic.cu_mobilephone}">
		</div>
		<div>
			<input type="submit" id="id-submit" value="修改">
		</div>
	</form>
</body>
</html>