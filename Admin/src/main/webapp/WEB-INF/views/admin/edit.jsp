<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編輯個人資訊 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
</head>
<body>
	<form:form action="/Admin/admin/edit.do" method="post" modelAttribute="admin">
		<div>
			<form:label path="a_lastname">姓氏</form:label>
			<form:input id="id-input-a-lastname" path="a_lastname" />
		</div>
		<div>
			<form:label path="a_firstname">名字</form:label>
			<form:input id="id-input-a-firstname" path="a_firstname" />
		</div>
		<div>
			<form:label path="a_email">信箱</form:label>
			<form:input id="id-input-a-email" path="a_email" />
		</div>
		<div>
			<input type="submit" id="id-input-submit" value="變更">
		</div>
	</form:form>
</body>
</html>