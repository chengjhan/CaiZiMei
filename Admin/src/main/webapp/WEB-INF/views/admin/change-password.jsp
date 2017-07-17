<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>變更密碼 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
</head>
<body>
	<form action="<c:url value='/admin/change-password.do' />" method="post">
		<div>
			<label for="id-input-a-password">舊密碼</label>
			<input type="password" id="id-input-a-password" name="a_password">
		</div>
		<div>
			<label for="id-input-a-password-new">新密碼</label>
			<input type="password" id="id-input-a-password-new" name="a_password_new">
		</div>
		<div>
			<label for="id-input-a-password-new-again">新密碼</label>
			<input type="password" id="id-input-a-password-new-again" name="a_password_new_again">
		</div>
		<div>
			<input type="submit" id="id-input-submit" value="變更">
		</div>
	</form>
</body>
</html>