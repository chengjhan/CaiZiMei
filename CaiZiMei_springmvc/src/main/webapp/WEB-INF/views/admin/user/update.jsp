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
	<form action="<c:url value='/admin/user/update.do' />"
		method="post">
		<div>
			<label for="id-adu-lastname">姓氏</label>
			<input type="text" id="id-adu-lastname" name="adu_lastname" value="${admin.adu_lastname}">
		</div>
		<div>
			<label for="id-adu-firstname">名字</label>
			<input type="text" id="id-adu-firstname" name="adu_firstname" value="${admin.adu_firstname}">
		</div>
		<div>
			<label for="id-adu-eng-name">英文名</label>
			<input type="text" id="id-adu-eng-name" name="adu_eng_name" value="${admin.adu_eng_name}">
		</div>
		<div>
			<label for="id-adu-email">信箱</label>
			<input type="text" id="id-adu-email" name="adu_email" value="${admin.adu_email}">
		</div>
		<div>
			<label for="id-adu-mobilephone">手機</label>
			<input type="text" id="id-adu-mobilephone" name="adu_mobilephone" value="${admin.adu_mobilephone}">
		</div>
		<div>
			<input type="submit" id="id-submit" value="修改">
		</div>
	</form>
</body>
</html>