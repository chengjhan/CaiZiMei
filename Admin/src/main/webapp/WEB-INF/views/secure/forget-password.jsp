<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>忘記密碼 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/shortcut_icon_black.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/secure.css" type="text/css" />
</head>
<body>
	<div class="container-fluid">
		<div class="container top">
			<div class="logo">
				<a href="<%=request.getContextPath()%>/index"><img src="<%=request.getContextPath()%>/images/logo_black.svg"></a>
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
			<form action="<c:url value='/secure/forget-password.do' />" method="post">
				<div class="form-group sub-title">忘記密碼</div>
				<div class="form-group">
					<label for="ad_email">信箱</label>
					<input type="text" id="ad_email" class="form-control" name="ad_email" value="${ad_email}" autofocus />
					<div><p style="font-size:13px;padding:5px">請輸入您的信箱，系統將發送驗證碼，以重設您的密碼。<p></div>
				</div>
				<div class="form-group" style="margin-top:20px">
					<input type="submit" class="btn btn-primary" style="float:right" value="發送" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>