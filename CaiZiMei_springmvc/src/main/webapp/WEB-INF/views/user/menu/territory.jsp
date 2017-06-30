<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>采姿美 - 事業版圖</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_32x32.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/territory.css" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<!-- header -->
	<c:import url="../header.jsp" />
	
<!-- menu -->
	<c:import url="../menu.jsp" />
	
<!-- main -->
	<div id="main">
		<section id="section-0"></section>
		<section id="section-1">
			<div id="id-div-section-1-content">
				<img src="<%=request.getContextPath()%>/images/caizimei_territory_1002x382.jpg" class="img-territory">
			</div>
		</section>
		<section id="section-99"></section>
	</div>

<!-- footer -->
	<c:import url="../footer.jsp" />
</body>
</html>