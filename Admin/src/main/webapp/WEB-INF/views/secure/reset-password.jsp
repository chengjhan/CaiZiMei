<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重設密碼 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/shortcut_icon_black.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/secure.css" type="text/css" />
</head>
<body>
	<div class="container-fluid">
		<div class="container top">
			<div class="logo">
				<a href="<%=request.getContextPath()%>/index"><img src="<%=request.getContextPath()%>/images/logo_black.svg" /></a>
			</div>
			<div class="title">
				<h3>采姿美管理系統</h3>
			</div>
		</div>
		<c:if test="${not empty error}">
			<div class="container form error-message">
				<span>${error}</span>
			</div>
		</c:if>
		<div class="container form">
			<form action="<c:url value='/secure/reset-password.do' />" method="post">
				<div class="form-group sub-title">重設密碼</div>
				<div class="form-group">
					<label for="ad_password_random">驗證碼</label>
					<input type="password" id="ad_password_random" class="form-control" name="ad_password_random" value="${ad_password_random}" autofocus />
				</div>
				<div class="form-group">
					<label for="ad_password_new">新密碼</label>
					<input type="password" id="ad_password_new" class="form-control" name="ad_password_new" value="${ad_password_new}" placeholder="請輸入 8 位字元以上並包含英文及數字的密碼" />
				</div>
				<div class="form-group">
					<label for="ad_password_new_again">確認密碼</label>
					<input type="password" id="ad_password_new_again" class="form-control" name="ad_password_new_again" value="${ad_password_new_again}" />
				</div>
				<div class="form-group" style="margin-top:30px">
					<input type="submit" class="btn btn-primary" style="float:right" value="重設" />
				</div>
			</form>
		</div>
	</div>
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/validation/jquery.validate.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/secure.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>