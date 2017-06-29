<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>采姿美 - 首頁</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/shortcut_icon_32x32.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/all.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/home.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAJznZ1ht-uJFa-tBJBpYYtzQ2609ba2Eg&callback=initMap" async defer></script>
<script src="<%=request.getContextPath()%>/js/home.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<!-- header -->
	<jsp:include page="header.jsp" />
	
<!-- menu -->
	<c:import url="menu.jsp" />
	
<!-- main -->
	<div id="main">
		<section id="section-1">

		</section>
		<section id="section-2">
		
		</section>
		<section id="section-3">
			<div id="id-map"></div>
		</section>
	</div>
	
<!-- footer -->
	<c:import url="footer.jsp" />
</body>
</html>