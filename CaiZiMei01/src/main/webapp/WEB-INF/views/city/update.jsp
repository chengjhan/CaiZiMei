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
	<%@ page
		import="org.springframework.web.context.support.WebApplicationContextUtils"%>
	<%@ page import="com.caizimei.model.entity.*"%>
	<%@ page import="com.caizimei.model.service.*"%>
	<%@ page import="java.util.List"%>
	<%@ page import="java.util.ArrayList"%>
	<%
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);

		CountryService countryService = (CountryService) context.getBean("countryService");
		List<CountryBean> countryBeanList = countryService.select();
		List<String> countryStringList = new ArrayList<String>();
		for (CountryBean bean : countryBeanList) {
			countryStringList.add(bean.getCo_name());
		}
		pageContext.setAttribute("list_co_name", countryStringList);

		CityService cityService = (CityService) context.getBean("cityService");
		List<CityBean> cityBeanList = cityService.select();
		pageContext.setAttribute("select", cityBeanList);
	%>
	<form action="<c:url value='/city/update.controller' />" method="post">
		<div style="display: none">
			<input type="text" id="id-ci-id" name="ci_id" value="${param.ci_id}">
		</div>
		<div>
			<label for="id-co-name">國家</label>
			<select id="id-co-name" name="co_name">
				<option></option>
				<c:forEach var="co_name" items="${list_co_name}">
					<option>${co_name}</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<label for="id-ci-name">城市</label>
			<input type="text" id="id-ci-name" name="ci_name" value="${param.ci_name}">
		</div>
		<div>
			<input type="submit" id="id-submit" value="修改">
		</div>
	</form>
</body>
</html>