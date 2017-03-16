<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
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
		pageContext.setAttribute("list_co_name", countryStringList);

		CityService cityService = (CityService) context.getBean("cityService");
		List<CityBean> cityBeanList = cityService.select();
		List<String> cityStringList = new ArrayList<String>();
		for (CityBean bean : cityBeanList) {
			cityStringList.add(bean.getCi_name());
		}
		pageContext.setAttribute("list_ci_name", cityStringList);

		ClinicService clinicService = (ClinicService) context.getBean("clinicService");
		List<ClinicBean> clinicBeanList = clinicService.select();
		pageContext.setAttribute("select", clinicBeanList);
	%>
	<c:url value="/" var="root" />
	<form action="<c:url value='/clinic/insert.controller' />" method="post">
		<div>
			<label for="id-c-name">名稱</label>
			<input type="text" id="id-c-name" name="c_name">
		</div>
		<div>
			<label for="id-c-eng-name">英文</label>
			<input type="text" id="id-c-eng-name" name="c_eng_name">
		</div>
		<div>
			<label for="id-c-telephone">電話</label>
			<input type="text" id="id-c-telephone" name="c_telephone_front">
			<input type="text" id="id-c-telephone" name="c_telephone_back">
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
			<select id="id-ci-name" name="ci_name">
				<option></option>
			</select>
		</div>
		<div>
			<label for="id-c-address">地址</label>
			<input type="text" id="id-c-address" name="c_address">
		</div>
		<div>
			<label for="id-c-url">網址</label>
			<input type="text" id="id-c-url" name="c_url">
		</div>
		<div>
			<input type="submit" id="id-submit" value="新增">
		</div>
	</form>
	<table border="1">
		<thead>
			<tr>
				<td>編號</td>
				<td>名稱</td>
				<td>英文名稱</td>
				<td>電話</td>
				<td>國家</td>
				<td>城市</td>
				<td>地址</td>
				<td>緯度</td>
				<td>經度</td>
				<td>網址</td>
				<td>修改</td>
				<td>刪除</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bean" items="${select}">
				<c:url value="/clinic/update.jsp" var="path">
					<c:param name="c_id" value="${bean.c_id}" />
					<c:param name="c_name" value="${bean.c_name}" />
					<c:param name="c_eng_name" value="${bean.c_eng_name}" />
					<c:param name="c_telephone" value="${bean.c_telephone}" />
					<c:param name="co_name" value="${bean.c_CityBean.ci_CountryBean.co_name}" />
					<c:param name="ci_name" value="${bean.c_CityBean.ci_name}" />
					<c:param name="c_address" value="${bean.c_address}" />
					<c:param name="c_url" value="${bean.c_url}" />
				</c:url>
				<tr>
					<td>${bean.c_id}</td>
					<td>${bean.c_name}</td>
					<td>${bean.c_eng_name}</td>
					<td>${bean.c_telephone}</td>
					<td>${bean.c_CityBean.ci_CountryBean.co_name}</td>
					<td>${bean.c_CityBean.ci_name}</td>
					<td>${bean.c_address}</td>
					<td>${bean.c_latitude}</td>
					<td>${bean.c_longitude}</td>
					<td><a href="${bean.c_url}">${bean.c_url}</a></td>
					<td><a href="${path}">修改</a></td>
					<td><a href="${root}clinic/delete.controller?c_id=${bean.c_id}">刪除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script>
// 		$("#id-co-name").change(function(){
// 			var co_name = $("#id-co-name").val();
// 			var city_select = $("#id-ci-name");
// 			$.getJSON("${root}/city/select.ajax", {"co_name": co_name}, function(data){
// 				city_select.empty();
// 				$.each(data, function(index, city){
// 					var city_option = $("<option></option>").append(city.ci_name);
// 					city_select.append(city_option);
// 				});
// 			});
// 		});
		
		$("#id-co-name").change(function(){
			var co_name = $("#id-co-name").val();
			var city_select = $("#id-ci-name");
			$.ajax({
				url: '${root}city/select.ajax?co_name=' + co_name,
				type: 'get',
				dataType: 'json',
				success: function(data){
					city_select.empty();
					$.each(data, function(index, city){
						var city_option = $("<option></option>").append(city.ci_name);
						city_select.append(city_option);
					});
				}
			});
		});
	</script>
</body>
</html>