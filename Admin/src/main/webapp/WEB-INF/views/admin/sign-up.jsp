<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>註冊 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
</head>
<body>
	<form:form action="/Admin/admin/sign-up.do" method="post" modelAttribute="adminBean">
		<div>
			<form:label path="ad_username">帳號</form:label>
			<form:input id="id-input-ad-username" path="ad_username" />
			<span id="id-span-ad-username" style="color: red"></span>
		</div>
		<div>
			<form:label path="ad_password">密碼</form:label>
			<form:password id="id-input-ad-password" path="ad_password" />
		</div>
		<div>
			<label for="id-input-ad-password-again">確認密碼</label>
			<input type="password" id="id-input-ad-password-again">
		</div>
		<div>
			<form:label path="ad_lastname">姓氏</form:label>
			<form:input id="id-input-ad-lastname" path="ad_lastname" />
		</div>
		<div>
			<form:label path="ad_firstname">名字</form:label>
			<form:input id="id-input-ad-firstname" path="ad_firstname" />
		</div>
		<div>
			<form:label path="ad_email">信箱</form:label>
			<form:input id="id-input-ad-email" path="ad_email" />
		</div>
		<div>
			<input type="submit" id="id-input-submit" value="註冊">
		</div>
	</form:form>
</body>
</html>