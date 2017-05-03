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
	<form action="<c:url value='/admin/agent/update.do' />" method="post">
		<div style="display: none">
			<input type="text" id="id-a-id" name="a_id" value="${param.a_id}">
		</div>
		<div>
			<label for="id-a-name">代理商</label>
			<input type="text" id="id-a-name" name="a_name" value="${param.a_name}">
		</div>
		<div>
			<label for="id-a-localphone">電話</label>
			<input type="text" id="id-a-localphone" name="a_localphone" value="${param.a_localphone}">
		</div>
		<div>
			<input type="submit" id="id-submit" value="修改">
		</div>
	</form>
</body>
</html>