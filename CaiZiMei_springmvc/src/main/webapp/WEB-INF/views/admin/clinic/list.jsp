<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<c:url value="/" var="root" />
	<form action="<c:url value='/admin/clinic/insert.do' />" method="post">
		<div>
			<label for="id-c-name">診所</label>
			<input type="text" id="id-c-name" name="c_name">
		</div>
		<div>
			<label for="id-c-eng-name">診所英文</label>
			<input type="text" id="id-c-eng-name" name="c_eng_name">
		</div>
		<div>
			<label for="id-c-localphone">電話</label>
			<input type="text" id="id-c-localphone" name="c_localphone_front">
			<input type="text" id="id-c-localphone" name="c_localphone_back">
		</div>
		<div>
			<label for="id-co-id">國家</label>
			<select id="id-co-id" style="width:150px">
				<option value="0">請選擇國家</option>
			</select>
		</div>
		<div>
			<label for="id-ci-id">城市</label>
			<select id="id-ci-id" style="width:150px">
				<option value="0">請選擇城市</option>
			</select>
		</div>
		<div>
			<label for="id-c-r-id">區域</label>
			<select id="id-c-r-id" name="c_r_id" style="width:150px">
				<option value="0">請選擇區域</option>
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
				<td>流水號</td>
				<td>名稱</td>
				<td>英文名稱</td>
				<td>電話</td>
				<td>國家</td>
				<td>城市</td>
				<td>區域</td>
				<td>地址</td>
				<td>緯度</td>
				<td>經度</td>
				<td>網址</td>
				<td>新增時間</td>
				<td>更新時間</td>
				<td>修改</td>
				<td>刪除</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bean" items="${clinicList}" varStatus="status">
				<fmt:formatDate value="${bean.c_insert_time}" var="c_insert_time_format" pattern="yyyy-MM-dd hh-mm-ss" />
				<fmt:formatDate value="${bean.c_update_time}" var="c_update_time_format" pattern="yyyy-MM-dd hh-mm-ss" />
				<c:url value="/admin/clinic/update" var="path">
					<c:param name="c_id" value="${bean.c_id}" />
					<c:param name="c_name" value="${bean.c_name}" />
					<c:param name="c_eng_name" value="${bean.c_eng_name}" />
					<c:param name="c_localphone" value="${bean.c_localphone}" />
					<c:param name="co_id" value="${bean.c_RegionBean.r_CityBean.ci_CountryBean.co_id}" />
					<c:param name="ci_id" value="${bean.c_RegionBean.r_CityBean.ci_id}" />
					<c:param name="r_id" value="${bean.c_RegionBean.r_id}" />
					<c:param name="c_address" value="${bean.c_address}" />
					<c:param name="c_url" value="${bean.c_url}" />
				</c:url>
				<tr>
					<td>${status.count}</td>
					<td>${bean.c_id}</td>
					<td>${bean.c_name}</td>
					<td>${bean.c_eng_name}</td>
					<td>${bean.c_localphone}</td>
					<td>${bean.c_RegionBean.r_CityBean.ci_CountryBean.co_name}</td>
					<td>${bean.c_RegionBean.r_CityBean.ci_name}</td>
					<td>${bean.c_RegionBean.r_name}</td>
					<td>${bean.c_address}</td>
					<td>${bean.c_latitude}</td>
					<td>${bean.c_longitude}</td>
					<td><a href="${bean.c_url}">${bean.c_url}</a></td>
					<td>${c_insert_time_format}</td>
					<td>${c_update_time_format}</td>
					<td><a href="${path}">修改</a></td>
					<td><a href="${root}admin/clinic/delete.do?c_id=${bean.c_id}">刪除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script>
		$(document).ready(function(){
			$.getJSON("${root}admin/country/select.ajax", function(data){
				$.each(data, function(index, country){
					var country_option = $("<option value=" + country.co_id + "></option>").append(country.co_name);
					$("#id-co-id").append(country_option);
				});
			});
		});
	
		$("#id-co-id").change(function(){
			var co_id = $("#id-co-id").val();
			$.ajax({
				url: '${root}admin/city/select-by-country.ajax?ci_co_id=' + co_id,
				type: 'get',
				dataType: 'json',
				success: function(data){
					var city_select = $("#id-ci-id");
					city_select.empty();
					city_select.append("<option value='0'>請選擇城市</option>");
					$.each(data, function(index, city){
						var city_option = $("<option value=" + city.ci_id + "></option>").append(city.ci_name);
						city_select.append(city_option);
					});
				}
			});
		});
		
		$("#id-ci-id").change(function(){
			var ci_id = $("#id-ci-id").val();
			$.getJSON("${root}admin/region/select-by-city.ajax", {"r_ci_id": ci_id}, function(data){
				var region_select = $("#id-c-r-id");
				region_select.empty();
				region_select.append("<option value='0'>請選擇區域</option>");
				$.each(data, function(index, region){
					var region_option = $("<option value=" + region.r_id + "></option>").append(region.r_name);
					region_select.append(region_option);
				});
			});
		});
	</script>
</body>
</html>