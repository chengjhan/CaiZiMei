<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>sub-menu</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sub-menu.css" type="text/css" />
</head>
<body>
	<div class="col-sm-2 sub-sidebar">
		<ul class="nav nav-sidebar">
			<li id="id-li-news-recent"><a href="<%=request.getContextPath()%>/news/recent"><span>近期活動</span></a></li>
			<li id="id-li-news-sale"><a href="<%=request.getContextPath()%>/news/sale"><span>優惠活動</span></a></li>
		</ul>
	</div>
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/sub-menu.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>