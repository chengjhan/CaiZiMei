<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/header.css">
<title>header</title>
</head>
<body>
	<c:url value="/" var="root" />
	<div id="header">
		<div id="id-div-logo">
			<a href="http://localhost:8080/CaiZiMei_springmvc/"><img class="img-icon" src="${root}images/logo_101x100.png"></a>
		</div>
		<div id="id-div-title">
			<a href="http://localhost:8080/CaiZiMei_springmvc/"><img class="img-icon" src="${root}images/title_168x50.png"></a>
		</div>
	</div>
</body>
</html>