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
	<form action="<c:url value='/service/specialist/update.do' />" method="post">
		<div>
			<label for="id-s-lastname">姓氏</label>
			<input type="text" id="id-s-lastname" name="s_lastname" value="${service.s_lastname}">
		</div>
		<div>
			<label for="id-s-firstname">名字</label>
			<input type="text" id="id-s-firstname" name="s_firstname" value="${service.s_firstname}">
		</div>
		<div>
			<label for="id-s-eng-name">英文名</label>
			<input type="text" id="id-s-eng-name" name="s_eng_name" value="${service.s_eng_name}">
		</div>
		<div>
			<label for="id-s-email">信箱</label>
			<input type="text" id="id-s-email" name="s_email" value="${service.s_email}">
		</div>
		<div>
			<label for="id-s-mobilephone">手機</label>
			<input type="text" id="id-s-mobilephone" name="s_mobilephone" value="${service.s_mobilephone}">
		</div>
		<div>
			<input type="submit" id="id-submit" value="修改">
		</div>
	</form>
</body>
</html>