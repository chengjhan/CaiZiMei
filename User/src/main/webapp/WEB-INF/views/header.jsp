<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>header</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css" type="text/css" />
</head>
<body>
	<header>
		<div class="div-header">
			<div class="div-logo">
				<a href="<%=request.getContextPath()%>/"><img src="<%=request.getContextPath()%>/images/logo.svg"></a>
			</div>
			<div class="div-title">
				<a href="<%=request.getContextPath()%>/"><span>采姿美國際股份有限公司</span></a>
			</div>
		</div>
	</header>
	
	<!-- load -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>