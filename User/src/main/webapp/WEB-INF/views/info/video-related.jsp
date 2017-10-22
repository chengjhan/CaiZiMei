<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>相關影音 - 采姿美</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/shortcut_icon.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/info/all.css" type="text/css" />
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
			
				<section>
					<c:forEach var="bean" items="${videoList}">
						<div class="video-related">
							<h5>${bean.vi_name}</h5>
							<div class="embed-responsive embed-responsive-16by9">${bean.vi_tag}</div>
						</div>
					</c:forEach>
				</section>
				
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