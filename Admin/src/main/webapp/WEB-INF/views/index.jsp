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
	<c:url value="/" var="root" />
	<p>
		<a href="<%=request.getContextPath()%>/secure/sign-in">登入</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/secure/forget-password">忘記密碼</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/secure/sign-out.do">登出</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/admin/sign-up">註冊</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/admin/profile">個人資訊</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/admin/change-password">修改密碼</a>
	</p>
	<p>
		<a href="${root}admin/country/list.do">國家</a>
	</p>
	<p>
		<a href="${root}admin/city/list.do">城市</a>
	</p>
	<p>
		<a href="${root}admin/region/search">區域搜尋</a>
	</p>
	<p>
		<a href="${root}admin/clinic/list.do">診所</a>
	</p>
	<p>
		<a href="${root}admin/clinic/search">診所搜尋</a>
	</p>
</body>
</html>