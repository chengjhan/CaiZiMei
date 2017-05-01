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
	<form action="<c:url value='/admin/administrator/update.do' />"
		method="post">
		<div>
			<label for="id-a-lastname">姓氏</label>
			<input type="text" id="id-a-lastname" name="a_lastname" value="${admin.a_lastname}">
		</div>
		<div>
			<label for="id-a-firstname">名字</label>
			<input type="text" id="id-a-firstname" name="a_firstname" value="${admin.a_firstname}">
		</div>
		<div>
			<label for="id-a-eng-name">英文名</label>
			<input type="text" id="id-a-eng-name" name="a_eng_name" value="${admin.a_eng_name}">
		</div>
		<div>
			<label for="id-a-email">信箱</label>
			<input type="text" id="id-a-email" name="a_email" value="${admin.a_email}">
		</div>
		<div>
			<label for="id-a-mobilephone">手機</label>
			<input type="text" id="id-a-mobilephone" name="a_mobilephone" value="${admin.a_mobilephone}">
		</div>
		<div>
			<input type="submit" id="id-submit" value="修改">
		</div>
	</form>
</body>
</html>