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
	<form action="<c:url value='/admin/secure/forget-password.do' />" method="post">
		<div>
			<label for="id-a-email">信箱</label>
			<input type="text" id="id-a-email" name="a_email">
		</div>
		<div>
			<input type="submit" id="id-submit" value="寄信">
		</div>
	</form>
</body>
</html>