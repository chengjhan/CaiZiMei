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
	<%@ page import="com.caizimei.model.*"%>
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
		pageContext.setAttribute("co_name_list", countryStringList);
		
		CityService cityService = (CityService) context.getBean("cityService");
		List<CityBean> cityBeanList = cityService.select();
		pageContext.setAttribute("select", cityBeanList);
	%>
	<c:url value="/" var="root" />
	<form action="<c:url value='/city/insert.controller' />" method="post">
		<div>
			<label for="id-co-name">國家</label>
			<select id="id-co-name" name="co_name">
				<option></option>
				<c:forEach var="co_name" items="${co_name_list}">
					<option>${co_name}</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<label for="id-ci-name">城市</label>
			<input type="text" id="id-ci-name" name="ci_name">
		</div>
		<div>
			<input type="submit" id="id-submit" value="新增">
		</div>
	</form>
	<table border="1">
		<thead>
			<tr>
				<td>編號</td>
				<td>國家</td>
				<td>城市</td>
				<td>修改</td>
				<td>刪除</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bean" items="${select}">
				<c:url value="/city/update.jsp" var="path">
					<c:param name="ci_id" value="${bean.ci_id}" />
					<c:param name="co_name" value="${bean.ci_CountryBean.co_name}" />
					<c:param name="ci_name" value="${bean.ci_name}" />
				</c:url>
				<tr>
					<td>${bean.ci_id}</td>
					<td>${bean.ci_CountryBean.co_name}</td>
					<td>${bean.ci_name}</td>
					<td><a href="${path}">修改</a></td>
					<td><a href="${root}city/delete.controller?ci_id=${bean.ci_id}">刪除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>