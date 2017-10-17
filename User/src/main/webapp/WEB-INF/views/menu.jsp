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
		<div class="div-menu">
			<ul class="ul-menu-left">
				<li id="id-li-about"><a href="<%=request.getContextPath()%>/about/introduction"><span>關於我們</span></a></li>
				<li id="id-li-team"><a href="<%=request.getContextPath()%>/team/introduction"><span>醫療團隊</span></a></li>
				<li id="id-li-activity"><a href="<%=request.getContextPath()%>/activity/introduction"><span>活動專區</span></a></li>
				<li id="id-li-knowleage"><a href="<%=request.getContextPath()%>/knowleage/introduction"><span>醫療新知</span></a></li>
				<li id="id-li-video"><a href="<%=request.getContextPath()%>/video/introduction"><span>相關影音</span></a></li>
			</ul>
		</div>
	</nav>
	
	<!-- load -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/menu.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>