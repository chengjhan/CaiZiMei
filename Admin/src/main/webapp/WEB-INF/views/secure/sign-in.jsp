<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登入 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/secure/sign-in.css" type="text/css" />
</head>
<body>
	<div class="container-fluid">
		<div style="width:60px;margin:0 auto">
			<img src="<%=request.getContextPath()%>/images/caizimei_logo_gray.svg" style="width:100%">
		</div>
		<h3 class="container" style="width:300px;margin:0 auto;font-size:30px">采姿美後台管理系統</h3>
		<div class="container" style="width:300px;font-size:20px;margin-bottom:5px">登入</div>
		<div class="container div-sign-in-form">
			<form action="<c:url value='/secure/sign-in.do' />" method="post">
				<div class="form-group">
					<label for="id-input-ad-username" class="control-label">帳號</label>
					<input type="text" id="id-input-ad-username" class="form-control" name="ad_username">
				</div>
				<div class="form-group">
					<label for="id-input-ad-password" class="control-label">密碼</label>
					<input type="password" id="id-input-ad-password" class="form-control" name="ad_password">
					<a href="<%=request.getContextPath()%>/secure/forget-password">忘記密碼？</a>
				</div>
				<div class="form-group" style="margin-top:20px">
					<span class="error">${error}</span>
					<input type="submit" id="id-input-submit" class="btn btn-primary" style="float:right" value="登入">
				</div>
			</form>
		</div>
		<div class="container" style="width:300px">
			<a href="<%=request.getContextPath()%>/">首頁</a>
		</div>
	</div>
</body>
</html>