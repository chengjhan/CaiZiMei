<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>加盟店 - 采姿美</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/shortcut_icon.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/container.css" type="text/css" />
</head>
<body>
	<!-- header -->
	<c:import url="../header.jsp" />
	
	<!-- menu -->
	<c:import url="../menu.jsp" />
	
	<!-- container -->
	<div class="container-fluid customize-container-fluid">
		<div class="row customize-row">
	
			<!-- sub-menu -->
			<c:import url="sub-menu.jsp" />
	
			<!-- main -->
			<main class="col-sm-10">
			
				<section>采姿美會員平台是以互聯網的架構而成立，提共給會員方便、快速、正確及優良的抗老及醫療常識。集結了優質的診所，讓會員隨時隨地都可傭有優質的諮詢通路，同時公司也創造出優良的產品，以滿足會員抗老及健康的需求。</section>
			
			</main>
			<!-- main end -->

		</div>
	</div>
	<!-- container end -->

	<!-- footer -->
	<c:import url="../footer.jsp" />
	
	<!-- load -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>