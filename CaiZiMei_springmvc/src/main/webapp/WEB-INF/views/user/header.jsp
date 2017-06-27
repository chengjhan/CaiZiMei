<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>header</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/header.css">
</head>
<body>
	<header>
		<div id="id-div-header">
			<div id="id-div-logo">
				<a href="<%=request.getContextPath()%>/"><img class="img-icon" src="<%=request.getContextPath()%>/images/logo_132x131.svg"></a>
			</div>
			<div id="id-div-title">
				<a class="a-title" href="<%=request.getContextPath()%>/"><span class="span-title">采姿美國際股份有限公司</span></a>
			</div>
		</div>
	</header>
</body>
</html>