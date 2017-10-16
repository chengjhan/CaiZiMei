<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>sub-menu</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/about/sub-menu.css" type="text/css" />
</head>
<body>
	<div class="col-sm-2 sub-sidebar">
		<ul class="nav nav-sidebar">
			<li id="id-li-about-introduction"><a href="<%=request.getContextPath()%>/about/introduction">采姿美介紹</a></li>
			<li id="id-li-about-idea"><a href="<%=request.getContextPath()%>/about/idea">經營理念</a></li>
			<li id="id-li-about-vision"><a href="<%=request.getContextPath()%>/about/vision">公司願景</a></li>
			<li id="id-li-about-mission"><a href="<%=request.getContextPath()%>/about/mission">公司使命</a></li>
			<li id="id-li-about-territory"><a href="<%=request.getContextPath()%>/about/territory">事業版圖</a></li>
		</ul>
	</div>
	
	<!-- load -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>