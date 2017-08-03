<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>忘記密碼 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/secure/forget-password.css" type="text/css" />
</head>
<body>
	<div class="container-fluid">
		<div class="container div-forget-password-form">
			<form action="<c:url value='/secure/forget-password.do' />" method="post">
				<div class="form-group">
					<label for="id-input-ad-email">信箱</label>
					<input type="text" id="id-input-ad-email" class="form-control" name="ad_email">
					<div><p style="font-size:13px">請輸入您註冊時所填的信箱，系統將發送驗證碼至您的信箱，以重設您的密碼。<p></div>
				</div>
				<div class="form-group" style="margin-top:20px">
					<span class="error">${error}</span>
					<input type="submit" id="id-input-submit" class="btn btn-primary" style="float:right" value="寄信">
				</div>
			</form>
		</div>
		<div class="container" style="width:300px">
			<a href="<%=request.getContextPath()%>/">首頁</a>
		</div>
	</div>
</body>
</html>