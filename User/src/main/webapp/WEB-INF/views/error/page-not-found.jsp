<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找不到網頁 - 采姿美</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/shortcut_icon.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/error.css" type="text/css" />
</head>
<body>
	<!-- header -->
	<c:import url="../header.jsp" />
	
	<!-- menu -->
	<c:import url="../menu.jsp" />
	
	<!-- main -->
	<main>
		<section>
			<div>
				<p>您所尋找的頁面不存在</p>
			</div>
		</section>
	</main>
	<!-- main end -->
	
	<!-- footer -->
	<c:import url="../footer.jsp" />

	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.2.1.min.js"></script>
</body>
</html>