<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編輯個人資訊 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
</head>
<body>
	<form action="<c:url value='/admin/edit.do' />" method="post">
		<div>
			<label for="id-input-a-lastname">姓氏</label>
			<input type="text" id="id-input-a-lastname" name="a_lastname" value="${admin.a_lastname}">
		</div>
		<div>
			<label for="id-input-a-firstname">名字</label>
			<input type="text" id="id-input-a-firstname" name="a_firstname" value="${admin.a_firstname}">
		</div>
		<div>
			<label for="id-input-a-email">信箱</label>
			<input type="text" id="id-input-a-email" name="a_email" value="${admin.a_email}">
		</div>
		<div>
			<label for="id-input-a-mobilephone">手機</label>
			<input type="text" id="id-input-a-mobilephone" name="a_mobilephone" value="${admin.a_mobilephone}">
		</div>
		<div>
			<input type="submit" id="id-input-submit" value="修改">
		</div>
	</form>
</body>
</html>