<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>menu-personal</title>
</head>
<body>
	<div class="col-sm-3 col-md-2 sidebar">
		<ul class="nav nav-sidebar">
			<li id="id-li-admin-profile"><a href="<%=request.getContextPath()%>/admin/profile">個人資訊</a></li>
			<li id="id-li-admin-edit"><a href="<%=request.getContextPath()%>/admin/edit">編輯個人資訊</a></li>
			<li id="id-li-admin-change-password"><a href="<%=request.getContextPath()%>/admin/change-password">變更密碼</a></li>
		</ul>
	</div>
	
	<!-- load -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/menu.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>