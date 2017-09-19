<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首頁 | 采姿美</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/shortcut_icon.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/3.4.2/css/swiper.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" type="text/css" />
</head>
<body>
	<!-- header -->
	<jsp:include page="header.jsp" />
	
	<!-- menu -->
	<c:import url="menu.jsp" />
	
	<!-- main -->
	<main>

		<!-- section-1 -->
		<section id="section-1">
			<div class="slider-main swiper-container">
				<div class="swiper-wrapper"></div>
				<div class="swiper-pagination"></div>
				<div class="swiper-button-next"></div>
				<div class="swiper-button-prev"></div>
			</div>
		</section>
		<!-- section-1 end -->
		
		<!-- section-2 -->
		<section id="section-2">
			<div class="video-related" style="float:left">
				<h4>相關影音</h4>
				<div class="embed-responsive embed-responsive-16by9">
					<iframe class="embed-responsive-item" frameborder="0" allowfullscreen></iframe>
				</div>
			</div>
			<div class="slider-franchisee swiper-container" style="float:right">
				<h4>加盟店資訊</h4>
				<div class="swiper-wrapper"></div>
				<div class="swiper-pagination"></div>
				<div class="swiper-button-next"></div>
				<div class="swiper-button-prev"></div>
			</div>
		</section>
		<!-- section-2 end -->
		
		<!-- section-3 -->
		<section id="section-3">
			<div class="slider-recent swiper-container" style="float:left">
				<h4>近期活動</h4>
				<div class="swiper-wrapper"></div>
				<div class="swiper-pagination"></div>
				<div class="swiper-button-next"></div>
				<div class="swiper-button-prev"></div>
			</div>
			<div class="slider-sale swiper-container" style="float:right">
				<h4>優惠活動</h4>
				<div class="swiper-wrapper"></div>
				<div class="swiper-pagination"></div>
				<div class="swiper-button-next"></div>
				<div class="swiper-button-prev"></div>
			</div>
		</section>
		<!-- section-3 end -->
		
		<!-- section-4 -->
		<section id="section-4">
			<div class="slider-knowledge swiper-container" style="float:left">
				<h4>醫療新知</h4>
				<div class="swiper-wrapper"></div>
				<div class="swiper-pagination"></div>
				<div class="swiper-button-next"></div>
				<div class="swiper-button-prev"></div>
			</div>
			<div class="slider-team swiper-container" style="float:right">
				<h4>醫療團隊</h4>
				<div class="swiper-wrapper"></div>
				<div class="swiper-pagination"></div>
				<div class="swiper-button-next"></div>
				<div class="swiper-button-prev"></div>
			</div>
		</section>
		<!-- section-4 end -->
		
		<!-- section-5 -->
		<section id="section-5">
			<div id="id-map"></div>
		</section>
		<!-- section-5 end -->
		
	</main>
	<!-- main end -->
	
	<!-- footer -->
	<c:import url="footer.jsp" />
	
	<!-- load -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/3.4.2/js/swiper.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/js/index-map.js" type="text/javascript" charset="utf-8"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAJznZ1ht-uJFa-tBJBpYYtzQ2609ba2Eg&callback=initMap" async defer></script>
</body>
</html>