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

#div-logo {
	height: 50px;
	margin-top: 15px;
	margin-bottom: 15px;
	float: left;
}

#img-logo {
	max-width: 100%;
	max-height: 100%;
}

#div-title {
	height: 25px;
	margin-top: 27.5px;
	margin-bottom: 27.5px;
	margin-left: 7px;
	float: left;
}

#img-title {
	max-width: 100%;
	max-height: 100%;
}

#menu {
	width: 100%;
	height: 35px;
	background-color: #AA0000;
}

#div-menu {
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
</style>
</head>
<body>
	<div id="header">
		<div id="div-logo">
			<a href="http://localhost:8080/CaiZiMei_springmvc/"><img id="img-logo" src="images/logo_101x100.png"></a>
		</div>
		<div id="div-title">
			<a href="http://localhost:8080/CaiZiMei_springmvc/"><img id="img-title" src="images/title_168x50.png"></a>
		</div>
	</div>
	<div id="menu">
		<div id="div-menu">
			<a class="menu-content">經營理念</a>
			<a class="menu-content">公司願景</a>
			<a class="menu-content">公司使命</a>
			<a class="menu-content">事業版圖</a>
		</div>
	</div>
	<div id="page"></div>
	<div id="footer"></div>
</body>
</html>