<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重設密碼 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/secure/all.css" type="text/css" />
</head>
<body>
	<div class="container-fluid">
		<div class="container top">
			<div class="logo">
				<a href="<%=request.getContextPath()%>/"><img src="<%=request.getContextPath()%>/images/caizimei_logo_gray.svg"></a>
			</div>
			<div class="title">
				<h3>采姿美管理系統</h3>
			</div>
		</div>
		<div class="container form">
			<form action="<c:url value='/secure/reset-password.do' />" method="post">
				<div class="form-group sub-title">重設密碼</div>
				<div class="form-group">
					<label for="id-input-ad-password">驗證碼</label>
					<input type="password" id="id-input-ad-password-random" class="form-control" name="ad_password_random">
				</div>
				<div class="form-group">
					<label for="id-input-ad-password-new">新密碼</label>
					<input type="password" id="id-input-ad-password-new" class="form-control" name="ad_password_new">
				</div>
				<div class="form-group">
					<label for="id-input-ad-password-new-again">新密碼</label>
					<input type="password" id="id-input-ad-password-new-again" class="form-control" name="ad_password_new_again">
				</div>
				<div class="form-group" style="margin-top:30px">
					<span class="error">${error}</span>
					<input type="submit" id="id-input-submit" class="btn btn-primary" style="float:right" value="重設">
				</div>
			</form>
		</div>
	</div>
</body>
</html>