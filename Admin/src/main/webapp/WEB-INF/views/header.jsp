<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>header</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap/dashboard.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/dashboard.css" type="text/css" />
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<div class="logo">
					<a href="<%=request.getContextPath()%>/index"><img src="<%=request.getContextPath()%>/images/logo_white.svg"></a>
				</div>
				<a class="navbar-brand" href="<%=request.getContextPath()%>/index">采姿美管理系統</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${empty admin}">
							<li><a href="<%=request.getContextPath()%>/secure/sign-in">登入</a></li>
						</c:when>
						<c:when test="${not empty admin}">
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${admin.ad_username}<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<%=request.getContextPath()%>/admin/profile">個人資訊</a></li>
									<li><a href="<%=request.getContextPath()%>/admin/edit">編輯個人資訊</a></li>
									<li><a href="<%=request.getContextPath()%>/admin/change-password">變更密碼</a></li>
									<li role="separator" class="divider"></li>
									<li><a href="<%=request.getContextPath()%>/secure/sign-out.do">登出</a></li>
								</ul>
							</li>
						</c:when>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>