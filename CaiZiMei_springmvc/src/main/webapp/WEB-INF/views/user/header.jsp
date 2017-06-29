<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>header</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	<header>
		<div id="id-div-header">
			<div id="id-div-logo">
				<a href="<%=request.getContextPath()%>/"><img src="<%=request.getContextPath()%>/images/caizimei_logo_132x131.svg" class="img-icon"></a>
			</div>
			<div id="id-div-title">
				<a class="a-title" href="<%=request.getContextPath()%>/"><span class="span-title">采姿美國際股份有限公司</span></a>
			</div>
		</div>
	</header>
</body>
</html>