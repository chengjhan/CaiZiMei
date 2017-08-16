<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>header</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap/dashboard/dashboard.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css" type="text/css" />
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<div class="logo">
					<img src="<%=request.getContextPath()%>/images/caizimei_logo_white.svg">
				</div>
				<a class="navbar-brand" href="<%=request.getContextPath()%>/">采姿美管理系統</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${empty admin}">
							<li><a href="<%=request.getContextPath()%>/secure/sign-in">登入</a></li>
						</c:when>
						<c:when test="${not empty admin}">
							<li><a href="<%=request.getContextPath()%>/admin/profile">設定</a></li>
							<li><a href="<%=request.getContextPath()%>/secure/sign-out">登出</a></li>
						</c:when>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>