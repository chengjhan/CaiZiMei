<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>menu</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/menu.css" type="text/css" />
</head>
<body>
	<nav>
		<div id="id-div-menu">
			<ul class="ul-menu">
				<li id="id-li-idea" class="li-menu li-menu-left"><a href="<%=request.getContextPath()%>/menu/idea" class="a-menu-left"><span class="span-menu">經營理念</span></a></li>
				<li id="id-li-vision" class="li-menu li-menu-left"><a href="<%=request.getContextPath()%>/menu/vision" class="a-menu-left"><span class="span-menu">公司願景</span></a></li>
				<li id="id-li-mission" class="li-menu li-menu-left"><a href="<%=request.getContextPath()%>/menu/mission" class="a-menu-left"><span class="span-menu">公司使命</span></a></li>
				<li id="id-li-territory" class="li-menu li-menu-left"><a href="<%=request.getContextPath()%>/menu/territory" class="a-menu-left"><span class="span-menu">事業版圖</span></a></li>
				<li id="id-li-introduction" class="li-menu li-menu-left"><a href="<%=request.getContextPath()%>/menu/introduction" class="a-menu-left"><span class="span-menu">關於我們</span></a></li>
			</ul>
		</div>
	</nav>
	
	<!-- load -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/menu.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>