<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/all.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/home.css">
<title>采姿美</title>
</head>
<body>
<!-- header -->
	<jsp:include page="header.jsp" />
	
<!-- menu -->
	<c:import url="menu.jsp"/>
	
<!-- page -->
	<div id="page">
		<div id="id-div-page-content">
			
		</div>
	</div>
	
<!-- footer -->
	<c:import url="footer.jsp"/>
</body>
</html>