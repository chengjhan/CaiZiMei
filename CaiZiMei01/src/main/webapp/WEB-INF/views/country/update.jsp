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
	<%@ page import="org.springframework.web.context.WebApplicationContext"%>
	<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
	<%@ page import="com.caizimei.model.entity.*"%>
	<%@ page import="com.caizimei.model.service.*"%>
	<%@ page import="java.util.List"%>
	<%
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);

		CountryService countryService = (CountryService) context.getBean("countryService");
		List<CountryBean> list = countryService.select();
		request.setAttribute("select", list);
	%>
	<form action="<c:url value='/country/update.do' />" method="post">
		<div style="display:none">
			<input type="text" id="id-co-id" name="co_id" value="${param.co_id}">
		</div>
		<div>
			<label for="id-co-name">國家</label>
			<input type="text" id="id-co-name" name="co_name" value="${param.co_name}">
		</div>
		<div>
			<label for="id-co-countrycode">代碼</label>
			<input type="text" id="id-co-countrycode" name="co_countrycode" value="${param.co_countrycode}">
		</div>
		<div>
			<input type="submit" id="id-submit" value="修改">
		</div>
	</form>
</body>
</html>