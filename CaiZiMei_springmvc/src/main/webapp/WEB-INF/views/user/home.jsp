<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>采姿美 - 首頁</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_32x32.ico" type="image/x-icon" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/3.4.2/css/swiper.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/home.css" type="text/css" />
</head>
<body>
	<!-- header -->
	<jsp:include page="header.jsp" />
	
	<!-- menu -->
	<c:import url="menu.jsp" />
	
	<!-- main -->
	<main>
<!-- 		<section id="section-0"></section> -->
		<section id="section-1">
			<div class="swiper-container">
				<div class="swiper-wrapper">
					<div class="swiper-slide"><a href="http://www.joya-beauty.com.tw" target="_blank" class="a-silder"><img src="<%=request.getContextPath()%>/images/clinic_joya_beauty_1000x380.png" class="img-slider"></a></div>
					<div class="swiper-slide"><a href="http://hollywood.tw" target="_blank" class="a-silder"><img src="<%=request.getContextPath()%>/images/clinic_hollywood_1000x380.jpg" class="img-slider"></a></div>
					<div class="swiper-slide"><a href="http://diamondcosmetic.com.tw" target="_blank" class="a-silder"><img src="<%=request.getContextPath()%>/images/clinic_diamondcosmetic_1000x380.jpg" class="img-slider"></a></div>
					<div class="swiper-slide"><a href="http://www.twinkle-clinic.tw" target="_blank" class="a-silder"><img src="<%=request.getContextPath()%>/images/clinic_twinkle_clinic_1000x380.jpg" class="img-slider"></a></div>
					<div class="swiper-slide"><a href="http://www.fabulous-clinic.com" target="_blank" class="a-silder"><img src="<%=request.getContextPath()%>/images/clinic_fabulous_clinic_1000x380.png" class="img-slider"></a></div>
					<div class="swiper-slide"><a href="http://1637.tw/07-2270748/" target="_blank" class="a-silder"><img src="<%=request.getContextPath()%>/images/clinic_1637_1000x380.jpg" class="img-slider"></a></div>
				</div>
				<!-- Add Pagination -->
				<div class="swiper-pagination"></div>
				<!-- Add Arrows -->
				<div class="swiper-button-next"></div>
				<div class="swiper-button-prev"></div>
			</div>
		</section>
		<section id="section-2">
			<div class="swiper-container" style="float:left">
				<div class="swiper-wrapper">
					<div class="swiper-slide"><iframe width="608" height="342" src="https://www.youtube.com/embed/zrNZARN_wXI" frameborder="0" allowfullscreen></iframe></div>
					<div class="swiper-slide"><iframe width="608" height="342" src="https://www.youtube.com/embed/RiDrdTmFzJs" frameborder="0" allowfullscreen></iframe></div>
					<div class="swiper-slide"><iframe width="608" height="342" src="https://www.youtube.com/embed/XnHGRpli9gA" frameborder="0" allowfullscreen></iframe></div>
				</div>
				<!-- Add Pagination -->
				<div class="swiper-pagination"></div>
				<!-- Add Arrows -->
				<div class="swiper-button-next"></div>
				<div class="swiper-button-prev"></div>
			</div>
			<div class="swiper-container" style="float:right">
				<div class="swiper-wrapper">
					<div class="swiper-slide"><iframe width="608" height="342" src="https://www.youtube.com/embed/zrNZARN_wXI" frameborder="0" allowfullscreen></iframe></div>
					<div class="swiper-slide"><iframe width="608" height="342" src="https://www.youtube.com/embed/RiDrdTmFzJs" frameborder="0" allowfullscreen></iframe></div>
					<div class="swiper-slide"><iframe width="608" height="342" src="https://www.youtube.com/embed/XnHGRpli9gA" frameborder="0" allowfullscreen></iframe></div>
				</div>
				<!-- Add Pagination -->
				<div class="swiper-pagination"></div>
				<!-- Add Arrows -->
				<div class="swiper-button-next"></div>
				<div class="swiper-button-prev"></div>
			</div>
		</section>
		<section id="section-3">
			<div id="id-map"></div>
		</section>
	</main>
	
	<!-- footer -->
	<c:import url="footer.jsp" />
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/3.4.2/js/swiper.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/home.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/js/home-slider.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/js/home-map.js" type="text/javascript" charset="utf-8"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAJznZ1ht-uJFa-tBJBpYYtzQ2609ba2Eg&callback=initMap" async defer></script>
</body>
</html>