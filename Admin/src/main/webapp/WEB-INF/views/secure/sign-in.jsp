<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登入 | 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/shortcut_icon_black.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/secure/all.css" type="text/css" />
</head>
<body>
	<div class="container-fluid">
		<div class="container top">
			<div class="logo">
				<a href="<%=request.getContextPath()%>/"><img src="<%=request.getContextPath()%>/images/logo_black.svg"></a>
			</div>
			<div class="title">
				<h3>采姿美管理系統</h3>
			</div>
		</div>
		<div class="container form">
			<form action="<c:url value='/secure/sign-in.do' />" method="post">
				<div class="form-group sub-title">登入</div>
				<div class="form-group">
					<label for="ad_username" class="control-label">帳號</label>
					<input type="text" id="ad_username" class="form-control" name="ad_username">
				</div>
				<div class="form-group">
					<label for="ad_password" class="control-label">密碼</label>
					<input type="password" id="ad_password" class="form-control" name="ad_password">
					<a href="<%=request.getContextPath()%>/secure/forget-password">忘記密碼？</a>
				</div>
				<div class="form-group" style="margin-top:20px">
					<span class="error">${error}</span>
					<input type="submit" class="btn btn-primary" style="float:right" value="登入">
				</div>
			</form>
		</div>
	</div>
</body>
</html>