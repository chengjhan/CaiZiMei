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
		List<String> cityStringList = new ArrayList<String>();
		for (CityBean bean : cityBeanList) {
			cityStringList.add(bean.getCi_name());
		}
		pageContext.setAttribute("ci_name_list", cityStringList);

		ClinicService clinicService = (ClinicService) context.getBean("clinicService");
		List<ClinicBean> list = clinicService.select();
		pageContext.setAttribute("select", list);
	%>
	<form action="<c:url value='/clinic/update.controller' />" method="post">
		<div style="display: none">
			<input type="text" name="c_id" value="${param.c_id}">
		</div>
		<div>
			<label for="id-c-name">名稱</label>
			<input type="text" id="id-c-name" name="c_name" value="${param.c_name}">
		</div>
		<div>
			<label for="id-c-eng-name">英文</label>
			<input type="text" id="id-c-eng-name" name="c_eng_name" value="${param.c_eng_name}">
		</div>
		<div>
			<label for="id-c-telephone">電話</label>
			<input type="text" id="id-c-telephone" name="c_telephone" value="${param.c_telephone}">
		</div>
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
			<select id="id-ci-name" name="ci_name">
				<option></option>
				<c:forEach var="ci_name" items="${ci_name_list}">
					<option>${ci_name}</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<label for="id-c-address">地址</label>
			<input type="text" id="id-c-address" name="c_address" value="${param.c_address}">
		</div>
		<div>
			<label for="id-c-url">網址</label>
			<input type="text" id="id-c-url" name="c_url" value="${param.c_url}">
		</div>
		<div>
			<input type="submit" id="id-submit" value="修改">
		</div>
	</form>
</body>
</html>