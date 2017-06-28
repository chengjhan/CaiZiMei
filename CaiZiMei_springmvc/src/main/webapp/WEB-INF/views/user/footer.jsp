<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>footer</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/footer.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/footer.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<footer>
		<div id="id-div-footer-top">
			<div id="id-div-footer-top-content">
				<div id="id-div-footer-top-content-logo">
					<a href="<%=request.getContextPath()%>/"><img class="img-icon" src="<%=request.getContextPath()%>/images/logo_white_132x131.svg"></a>
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
					<div id="id-div-footer-middle-content-menu-sub">
						<ul class="ul-sub">
							<li class="li-sub"><a href="<%=request.getContextPath()%>/user/menu/idea" class="a-sub"><span class="span-sub">經營理念</span></a></li>
							<li class="li-sub"><a href="<%=request.getContextPath()%>/user/menu/vision" class="a-sub"><span class="span-sub">公司願景</span></a></li>
							<li class="li-sub"><a href="<%=request.getContextPath()%>/user/menu/mission" class="a-sub"><span class="span-sub">公司使命</span></a></li>
							<li class="li-sub"><a href="<%=request.getContextPath()%>/user/menu/territory" class="a-sub"><span class="span-sub">事業版圖</span></a></li>
						</ul>
					</div>
				</div>
				<div id="id-div-footer-middle-content-sns" class="div-footer-content-middle-left">
					<div id="id-div-footer-middle-content-sns-title" class="div-catalog">
						<span class="span-catalog">社群</span>
					</div>
					<div>
						<div id="id-div-facebook">
							<a href="https://www.facebook.com/" target="_blank">
								<!-- facebook icon svg -->							
								<svg id="id-svg-facebook" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" id="Capa_1" x="0px" y="0px" width="32px" height="32px" viewBox="0 0 612 612" style="enable-background:new 0 0 612 612;" xml:space="preserve">
									<g>
										<path d="M612,306C612,137.004,474.995,0,306,0C137.004,0,0,137.004,0,306c0,168.995,137.004,306,306,306    C474.995,612,612,474.995,612,306z M27.818,306C27.818,152.36,152.36,27.818,306,27.818S584.182,152.36,584.182,306    S459.64,584.182,306,584.182S27.818,459.64,27.818,306z" fill="#CCCCCC"/>
										<path d="M317.739,482.617V306h58.279l9.208-58.529h-67.487v-29.348c0-15.272,5.007-29.849,26.928-29.849h43.813v-58.418h-62.201    c-52.298,0-66.569,34.438-66.569,82.175v35.413h-35.885V306h35.885v176.617H317.739L317.739,482.617z" fill="#CCCCCC"/>
									</g>
								</svg>
							</a>
						</div>
					</div>
				</div>
				<div id="id-div-footer-middle-content-clinic" class="div-footer-content-middle-left">
					<div id="id-div-footer-middle-content-menu-title" class="div-catalog">
						<span class="span-catalog">診所</span>
					</div>
					<div id="id-div-footer-middle-content-menu-sub">
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
				</div>
				<div id="id-div-footer-middle-content-info">
					<div id="id-div-footer-middle-content-info-title" class="div-catalog">
						<span class="span-catalog">聯絡我們</span>
					</div>
					<div id="id-div-footer-middle-content-info-mail" class="div-info">
						<div id="id-div-mail" class="div-icon">
							<img src="<%=request.getContextPath()%>/images/icon_mail_filled_512x512.svg" class="img-icon" >
						</div>
						<div class="div-text">
							<a href="mailto:czmgoldvip@gmail.com" target="_blank" class="a-sub"><span class="span-text">czmgoldvip@gmail.com</span></a>
						</div>
					</div>
					<div id="id-div-footer-middle-content-info-phone" class="div-info">
						<div id="id-div-phone" class="div-icon">
							<img src="<%=request.getContextPath()%>/images/icon_phone_filled_512x512.svg" class="img-icon" >
						</div>
						<div class="div-text">
							<a href="tel:+886-2-27562282" target="_blank" class="a-sub"><span class="span-text">+886-2-27562282</span></a>
						</div>
					</div>
					<div id="id-div-footer-middle-content-info-location" class="div-info">
						<div id="id-div-location" class="div-icon">
							<img src="<%=request.getContextPath()%>/images/icon_location_filled_512x512.svg" class="img-icon" >
						</div>
						<div class="div-text">
							<a href="https://www.google.com.tw/maps/place/105%E5%8F%B0%E5%8C%97%E5%B8%82%E6%9D%BE%E5%B1%B1%E5%8D%80%E5%8D%97%E4%BA%AC%E6%9D%B1%E8%B7%AF%E4%BA%94%E6%AE%B5223%E8%99%9F/" target="_blank" class="a-sub"><span class="span-text">台北辦事處：台北市松山區南京東路五段223號13樓</span></a>
						</div>
					</div>
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