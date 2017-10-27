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
				<li id="id-li-team"><a href="<%=request.getContextPath()%>/team/doctor"><span>專業團隊</span></a></li>
				<li id="id-li-news"><a href="<%=request.getContextPath()%>/news/recent"><span>最新消息</span></a></li>
				<li id="id-li-info"><a href="<%=request.getContextPath()%>/info/knowleage"><span>相關資訊</span></a></li>
			</ul>
		</div>
	</nav>
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/menu.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>