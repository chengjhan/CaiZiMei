<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>footer</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/footer.css" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/footer.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<footer>
		<div id="id-div-footer-top">
			<div id="id-div-footer-top-content">
				<div id="id-div-footer-top-content-logo">
					<a href="<%=request.getContextPath()%>/"><img class="img-icon" src="<%=request.getContextPath()%>/images/caizimei_logo_white.svg"></a>
				</div>
				<div>
					<a href="<%=request.getContextPath()%>/" class="a-catalog"><span class="span-breadcrumb">采姿美國際股份有限公司</span></a>
				</div>
			</div>
		</div>
		<div id="id-div-footer-middle">
			<div id="id-div-footer-middle-content">
				<div id="id-div-footer-middle-content-menu" class="div-footer-content-middle-left">
					<div id="id-div-footer-middle-content-menu-title" class="div-catalog">
						<a href="<%=request.getContextPath()%>/" class="a-catalog"><span class="span-catalog">首頁</span></a>
					</div>
					<ul class="ul-sub">
						<li class="li-sub"><a href="<%=request.getContextPath()%>/user/menu/idea" class="a-sub"><span class="span-sub">經營理念</span></a></li>
						<li class="li-sub"><a href="<%=request.getContextPath()%>/user/menu/vision" class="a-sub"><span class="span-sub">公司願景</span></a></li>
						<li class="li-sub"><a href="<%=request.getContextPath()%>/user/menu/mission" class="a-sub"><span class="span-sub">公司使命</span></a></li>
						<li class="li-sub"><a href="<%=request.getContextPath()%>/user/menu/territory" class="a-sub"><span class="span-sub">事業版圖</span></a></li>
					</ul>
				</div>
				<div id="id-div-footer-middle-content-sns" class="div-footer-content-middle-left">
					<div id="id-div-footer-middle-content-sns-title" class="div-catalog">
						<span class="span-catalog">社群</span>
					</div>
					<ul class="ul-sub">
						<li class="li-sub">
							<a href="https://www.facebook.com/" target="_blank">
								<!-- facebook icon svg -->
								<svg id="id-svg-facebook" version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="34px" height="34px" viewBox="0 0 57.8 57.7" enable-background="new 0 0 57.8 57.7" xml:space="preserve">
									<g>
										<circle fill="none" stroke="#CCCCCC" stroke-width="3.5" cx="28.9" cy="28.8" r="27.3"/>
										<path fill="#CCCCCC" d="M34.9,28.8h-3.8v13.9h-5.8V28.8h-2.9V24h2.9v-2.9c0-3.9,1.6-6.2,6.2-6.2h3.8v4.8H33c-1.8,0-1.9,0.7-1.9,1.9 l0,2.4h4.4L34.9,28.8z"/>
									</g>
								</svg>
							</a>
						</li>
						<li class="li-sub">
							<a href="https://www.youtube.com/channel/UCKcgCX6ljOxWAnWzW89M5ag" target="_blank">
								<!-- youtube icon svg -->				
								<svg id="id-svg-youtube" height="34px" version="1.1" viewBox="0 0 50 50" width="34px" xmlns="http://www.w3.org/2000/svg" xmlns:sketch="http://www.bohemiancoding.com/sketch/ns" xmlns:xlink="http://www.w3.org/1999/xlink"><title/><defs/>
									<g fill="none" fill-rule="evenodd" id="Page-1" stroke="none" stroke-width="1">
										<g fill="#CCCCCC" id="YouTube">
											<path d="M50,25 C50,11.1928806 38.8071194,0 25,0 C11.1928806,0 0,11.1928806 0,25 C0,38.8071194 11.1928806,50 25,50 C38.8071194,50 50,38.8071194 50,25 Z M47,25 C47,12.8497349 37.1502651,3 25,3 C12.8497349,3 3,12.8497349 3,25 C3,37.1502651 12.8497349,47 25,47 C37.1502651,47 47,37.1502651 47,25 Z M36.768327,30.7654774 C36.4698281,32.0627028 35.4087162,33.0191862 34.1319129,33.1618614 C31.1074781,33.4998058 28.0463955,33.5014844 24.9984613,33.4998058 C21.9508068,33.5014844 18.8894444,33.4998058 15.8652894,33.1618614 C14.5882064,33.0191862 13.5276539,32.0627028 13.2294348,30.7654774 C12.8047662,28.9179732 12.8047662,26.9020564 12.8047662,25.0002798 C12.8047662,23.0982233 12.8098018,21.0820268 13.2341906,19.2345226 C13.5326895,17.9372972 14.5932419,16.980534 15.8700452,16.8381386 C18.89448,16.5001942 21.9555627,16.4985156 25.0034969,16.5001942 C28.0511513,16.4985156 31.1125137,16.5001942 34.1366687,16.8381386 C35.4137518,16.980534 36.4748637,17.9372972 36.7730829,19.2345226 C37.1977514,21.0820268 37.1952336,23.0982233 37.1952336,25.0002798 C37.1952336,26.9020564 37.1927158,28.9179732 36.768327,30.7654774 Z M22.8047662,20.5 L29.5547662,24.3971143 L22.8047662,28.2942286 L22.8047662,20.5 Z M22.8047662,20.5" id="Oval-1"/>
										</g>
									</g>
								</svg>
							</a>
						</li>
					</ul>
				</div>
				<div id="id-div-footer-middle-content-clinic" class="div-footer-content-middle-left">
					<div id="id-div-footer-middle-content-menu-title" class="div-catalog">
						<span class="span-catalog">診所</span>
					</div>
					<ul class="ul-sub">
						<li class="li-sub"><a href="http://hollywood.tw" target="_blank" class="a-sub"><span class="span-sub">好萊塢診所</span></a></li>
						<li class="li-sub"><a href="http://1637.tw/07-2270748/" target="_blank" class="a-sub"><span class="span-sub">何彬彬牙醫診所</span></a></li>
						<li class="li-sub"><a href="http://www.fabulous-clinic.com" target="_blank" class="a-sub"><span class="span-sub">法泊時尚診所</span></a></li>
						<li class="li-sub"><a href="http://www.twinkle-clinic.tw" target="_blank" class="a-sub"><span class="span-sub">曼星醫美診所</span></a></li>
						<li class="li-sub"><a href="http://www.joya-beauty.com.tw" target="_blank" class="a-sub"><span class="span-sub">喬雅時尚診所</span></a></li>
						<li class="li-sub"><a href="http://diamondcosmetic.com.tw" target="_blank" class="a-sub"><span class="span-sub">晶鑽時尚診所</span></a></li>
						<li class="li-sub"><a href="http://www.dazzlingdental.com.tw" target="_blank" class="a-sub"><span class="span-sub">璀燦精品牙醫診所</span></a></li>
					</ul>
				</div>
				<div id="id-div-footer-middle-content-info">
					<div id="id-div-footer-middle-content-info-title" class="div-catalog">
						<span class="span-catalog">聯絡我們</span>
					</div>
					<ul class="ul-sub">
						<li class="li-sub li-sub-info"><div class="div-icon"><img src="<%=request.getContextPath()%>/images/icon_mail.svg" class="img-icon"></div><a href="mailto:czmgoldvip@gmail.com" target="_blank" class="a-sub a-sub-info-text"><span class="span-text">czmgoldvip@gmail.com</span></a></li>
						<li class="li-sub li-sub-info"><div class="div-icon"><img src="<%=request.getContextPath()%>/images/icon_phone.svg"" class="img-icon"></div><a href="tel:+886-2-27562282" target="_blank" class="a-sub a-sub-info-text"><span class="span-text">+886-2-27562282</span></a></li>
						<li class="li-sub li-sub-info"><div class="div-icon"><img src="<%=request.getContextPath()%>/images/icon_location.svg" class="img-icon"></div><a href="https://www.google.com.tw/maps/place/105%E5%8F%B0%E5%8C%97%E5%B8%82%E6%9D%BE%E5%B1%B1%E5%8D%80%E5%8D%97%E4%BA%AC%E6%9D%B1%E8%B7%AF%E4%BA%94%E6%AE%B5223%E8%99%9F/" target="_blank" class="a-sub a-sub-info-text"><span class="span-text">台北辦事處：台北市松山區南京東路五段223號13樓</span></a></li>
					</ul>
				</div>
			</div>
		</div>
		<div id="id-div-footer-bottom">
			<div id="id-div-footer-bottom-content">
				<div id="id-div-footer-bottom-content-copyright">
					<span class="span-copyright">© 2017 FUNUV All rights reserved.</span>
				</div>
			</div>
		</div>
	</footer>
</body>
</html>