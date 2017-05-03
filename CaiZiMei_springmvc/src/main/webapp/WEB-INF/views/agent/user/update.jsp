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
	<form action="<c:url value='/agent/user/update.do' />" method="post">
		<div>
			<label for="id-au-lastname">姓氏</label>
			<input type="text" id="id-au-lastname" name="au_lastname" value="${agent.au_lastname}">
		</div>
		<div>
			<label for="id-au-firstname">名字</label>
			<input type="text" id="id-au-firstname" name="au_firstname" value="${agent.au_firstname}">
		</div>
		<div>
			<label for="id-au-eng-name">英文名</label>
			<input type="text" id="id-au-eng-name" name="au_eng_name" value="${agent.au_eng_name}">
		</div>
		<div>
			<label for="id-au-email">信箱</label>
			<input type="text" id="id-au-email" name="au_email" value="${agent.au_email}">
		</div>
		<div>
			<label for="id-au-mobilephone">手機</label>
			<input type="text" id="id-au-mobilephone" name="au_mobilephone" value="${agent.au_mobilephone}">
		</div>
		<div>
			<input type="submit" id="id-submit" value="修改">
		</div>
	</form>
</body>
</html>