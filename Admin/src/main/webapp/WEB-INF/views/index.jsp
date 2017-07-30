<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首頁 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
</head>
<body>
	<p>
		<a href="<%=request.getContextPath()%>/secure/sign-in">登入</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/secure/sign-out">登出</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/admin/sign-up">註冊</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/admin/profile">個人資訊</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/admin/change-password">變更密碼</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/admin/list">管理員一覽</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/country/list">國家一覽</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/state/list">區域一覽</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/city/list">城市一覽</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/office/list">辦事處一覽</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/franchisee/list">加盟店一覽</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/clinic/list">診所一覽</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/slider-main/list">主輪播圖片一覽</a>
	</p>
</body>
</html>