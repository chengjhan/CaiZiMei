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
			<li id="id-li-team-doctor"><a href="<%=request.getContextPath()%>/team/doctor"><span>醫療團隊</span></a></li>
		</ul>
	</div>
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/sub-menu.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>