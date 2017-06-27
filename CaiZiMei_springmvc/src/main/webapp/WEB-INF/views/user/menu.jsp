<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/menu.css">
</head>
<body>
	<nav>
		<div id="id-div-menu">
			<ul class="ul-menu">
				<li class="li-menu"><a href="<%=request.getContextPath()%>/user/menu/idea" class="a-menu"><span class="span-menu">經營理念</span></a></li>
				<li class="li-menu"><a href="<%=request.getContextPath()%>/user/menu/vision" class="a-menu"><span class="span-menu">公司願景</span></a></li>
				<li class="li-menu"><a href="<%=request.getContextPath()%>/user/menu/mission" class="a-menu"><span class="span-menu">公司使命</span></a></li>
				<li class="li-menu"><a href="<%=request.getContextPath()%>/user/menu/territory" class="a-menu"><span class="span-menu">事業版圖</span></a></li>
			</ul>
		</div>
	</nav>
</body>
</html>