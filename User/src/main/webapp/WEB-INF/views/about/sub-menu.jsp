<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>sub-menu</title>
</head>
<body>
	<div class="col-sm-2 sub-menu">
		<ul class="nav nav-sidebar">
			<li id="id-li-about-introduction"><a href="<%=request.getContextPath()%>/about/introduction"><span>采姿美介紹</span></a></li>
			<li id="id-li-about-idea"><a href="<%=request.getContextPath()%>/about/idea"><span>經營理念</span></a></li>
			<li id="id-li-about-vision"><a href="<%=request.getContextPath()%>/about/vision"><span>公司願景</span></a></li>
			<li id="id-li-about-mission"><a href="<%=request.getContextPath()%>/about/mission"><span>公司使命</span></a></li>
			<li id="id-li-about-territory"><a href="<%=request.getContextPath()%>/about/territory"><span>事業版圖</span></a></li>
			<li id="id-li-about-franchisee"><a href="<%=request.getContextPath()%>/about/franchisee"><span>加盟店</span></a></li>
		</ul>
	</div>
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/sub-menu.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>