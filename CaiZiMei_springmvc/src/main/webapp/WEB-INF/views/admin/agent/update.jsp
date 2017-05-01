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
	<form action="<c:url value='/admin/company/update.do' />" method="post">
		<div style="display: none">
			<input type="text" id="id-com-id" name="com_id" value="${param.com_id}">
		</div>
		<div>
			<label for="id-com-name">公司</label>
			<input type="text" id="id-com-name" name="com_name" value="${param.com_name}">
		</div>
		<div>
			<label for="id-com-localphone">電話</label>
			<input type="text" id="id-com-localphone" name="com_localphone" value="${param.com_localphone}">
		</div>
		<div>
			<input type="submit" id="id-submit" value="修改">
		</div>
	</form>
</body>
</html>