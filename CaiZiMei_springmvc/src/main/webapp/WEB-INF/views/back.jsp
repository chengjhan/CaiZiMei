<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:url value="/" var="root" />
	<p>
		<a href="${root}country/list.do">國家</a>
	</p>
	<p>
		<a href="${root}city/list.do">城市</a>
	</p>
	<p>
		<a href="${root}clinic/list.do">診所</a>
	</p>
	<p>
		<a href="${root}clinic/search">診所查詢</a>
	</p>
	<p>
		<a href="${root}member/search">會員查詢</a>
	</p>
</body>
</html>