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
</head>
<body>
	<form action="<c:url value='/secure/sign-in.do' />" method="post" class="form-horizontal" style="width:400px">
		<div class="form-group">
			<label for="id-input-ad-username" class="col-sm-2 control-label">帳號</label>
			<div class="col-sm-10">
				<input type="text" id="id-input-ad-username" class="form-control" name="ad_username">
			</div>
		</div>
		<div class="form-group">
			<label for="id-input-ad-password" class="col-sm-2 control-label">密碼</label>
			<div class="col-sm-10">
				<input type="password" id="id-input-ad-password" class="form-control" name="ad_password">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" id="id-input-submit" class="btn btn-default" value="登入">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<a href="<%=request.getContextPath()%>/secure/forget-password">忘記密碼</a>
			</div>
		</div>
		<div style="color:red">${error}</div>
	</form>
</body>
</html>