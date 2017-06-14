<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>采姿美</title>
<style>
html, body {
	margin: 0;
}

#header {
	width: 75%;
	height: 80px;
	padding-bottom: 10px;
	margin: 0 auto;
}

#id-div-logo {
	height: 50px;
	margin-top: 15px;
	margin-bottom: 15px;
	float: left;
}

#id-img-logo {
	max-width: 100%;
	max-height: 100%;
}

#id-div-title {
	height: 25px;
	margin-top: 27.5px;
	margin-bottom: 27.5px;
	margin-left: 7px;
	float: left;
}

#id-img-title {
	max-width: 100%;
	max-height: 100%;
}

#menu {
	width: 100%;
	height: 35px;
	background-color: #AA0000;
}

#id-div-menu {
	width: 75%;
	margin: 0 auto;
	line-height: 35px;
	text-align: center;
}

.menu-content {
	color: white;
	margin-right: 10px;
	float: left;
	display: inline-block;
}

#page {
	height: 500px;
	background-color: #DDDDDD;
}

#id-div-page-content {
	width: 75%;
	height: 500px;
	background-color: white;
	margin: 0 auto;
}

#id-div-footer-top {
	width: 100%;
	height: 100px;
	background-color: #AA0000;
}

#id-div-footer-middle {
	width: 100%;
	height: 135px;
	background-color: #AA0000;
}

#id-div-footer-middle-content {
	width: 75%;
	height: 150px;
	margin: 0 auto;
}

#id-div-footer-middle-content-menu {
	margin-right: 100px;
	float: left;
}

#id-div-footer-middle-content-menu-title {
	padding-bottom: 10px;
}

#id-div-footer-middle-content-sns-title {
	padding-bottom: 10px;
}

#id-div-footer-middle-content-sns {
	float: left;
}

#id-div-footer-middle-content-info {
	float: right;
}

#id-div-footer-middle-content-info-mail {
	height: 42.5px;
}

#id-div-footer-middle-content-info-phone {
	height: 42.5px;
}

#id-div-footer-middle-content-info-location {
	height: 42.5px;
}

.div-icon {
	width: 35px;
	margin-right: 7.5px;
	float: left;
}

.img-icon {
	max-width: 100%;
	max-height: 100%;
}

.div-text {
	float: left;
}

.p-title {
	color: white;
	font-size: 16px;
	margin: 0;
}

.p-sub {
	color: white;
	font-size: 14px;
	margin:0;
}

.p-text {
	color: white;
	font-size: 12px;
	margin: 0;
}

#id-div-footer-bottom {
	width: 100%;
	height: 40px;
	background-color: #AA0000;
}

#id-div-footer-bottom-content {
	width: 75%;
	border-top: gray 1px solid;
	margin: 0 auto;
}

#id-div-footer-bottom-content-copyright {
	margin-top: 12px;
	display: inline;
}

.p-copyright {
	color: white;
	font-size: 12px;
	display: inline;
}
</style>
</head>
<body>
	<div id="header">
		<div id="id-div-logo">
			<a href="http://localhost:8080/CaiZiMei_springmvc/"><img id="id-img-logo" src="images/logo_101x100.png"></a>
		</div>
		<div id="id-div-title">
			<a href="http://localhost:8080/CaiZiMei_springmvc/"><img id="id-img-title" src="images/title_168x50.png"></a>
		</div>
	</div>
	<div id="menu">
		<div id="id-div-menu">
			<a class="menu-content">經營理念</a>
			<a class="menu-content">公司願景</a>
			<a class="menu-content">公司使命</a>
			<a class="menu-content">事業版圖</a>
		</div>
	</div>
	<div id="page">
		<div id="id-div-page-content">
			
		</div>
	</div>
	<div id="footer">
		<div id="id-div-footer-top"></div>
		<div id="id-div-footer-middle">
			<div id="id-div-footer-middle-content">
				<div id="id-div-footer-middle-content-menu">
					<div id="id-div-footer-middle-content-menu-title">
						<p class="p-title">首頁</p>
					</div>
					<div id="id-div-footer-middle-content-menu-sub">
						<p class="p-sub">經營理念</p>
						<p class="p-sub">公司願景</p>
						<p class="p-sub">公司使命</p>
						<p class="p-sub">事業版圖</p>
					</div>
				</div>
				<div id="id-div-footer-middle-content-sns">
					<div id="id-div-footer-middle-content-sns-title">
						<p class="p-title">社群</p>
					</div>
					<div>
						<div id="id-div-facebook" class="div-icon">
							<img class="img-icon" src="images/icon_facebook_35x35.png">
						</div>
						<div id="id-div-line" class="div-icon">
							<img class="img-icon" src="images/icon_line_35x35.png">
						</div>
					</div>
				</div>
				<div id="id-div-footer-middle-content-info">
					<div id="id-div-footer-middle-content-info-mail">
						<div id="id-div-mail" class="div-icon">
							<img class="img-icon" src="images/icon_mail_35x35.png">
						</div>
						<div class="div-text">
							<p class="p-text">czmgoldvip@gmail.com</p>
							<p class="p-text">czmgoldvip@qq.com</p>
						</div>
					</div>
					<div id="id-div-footer-middle-content-info-phone">
						<div id="id-div-phone" class="div-icon">
							<img class="img-icon" src="images/icon_phone_35x35.png">
						</div>
						<div class="div-text">
							<p class="p-text">+886-2-27562282</p>
						</div>
					</div>
					<div id="id-div-footer-middle-content-info-location">
						<div id="id-div-location" class="div-icon">
							<img class="img-icon" src="images/icon_location_35x35.png">
						</div>
						<div class="div-text">
							<p class="p-text">台北辦事處：台北市松山區南京東路五段223號13樓</p>
							<p class="p-text">大陸總管理處：廣東省珠海市香洲區拱北情侶南路仁恒濱海中心1棟2單元1101</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="id-div-footer-bottom">
			<div id="id-div-footer-bottom-content">
				<div id="id-div-footer-bottom-content-copyright">
					<p class="p-copyright">© 2017 FUNUV All rights reserved.</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>