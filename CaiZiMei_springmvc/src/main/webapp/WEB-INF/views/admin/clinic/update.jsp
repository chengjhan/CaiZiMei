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
	<c:url value="/" var="root" />
	<form action="<c:url value='/admin/clinic/update.do' />" method="post">
		<div style="display: none">
			<input type="text" name="c_id" value="${param.c_id}">
		</div>
		<div>
			<label for="id-c-name">診所</label>
			<input type="text" id="id-c-name" name="c_name" value="${param.c_name}">
		</div>
		<div>
			<label for="id-c-eng-name">診所英文</label>
			<input type="text" id="id-c-eng-name" name="c_eng_name" value="${param.c_eng_name}">
		</div>
		<div>
			<label for="id-c-localhone">電話</label>
			<input type="text" id="id-c-localphone" name="c_localphone" value="${param.c_localphone}">
		</div>
		<div>
			<label for="id-co-id">國家</label>
			<select id="id-co-id" style="width:150px"></select>
		</div>
		<div>
			<label for="id-ci-id">城市</label>
			<select id="id-ci-id" style="width:150px"></select>
		</div>
		<div>
			<label for="id-c-r-id">區域</label>
			<select id="id-c-r-id" name="c_r_id" style="width:150px"></select>
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
	<script>
		$(document).ready(function(){
			
			// return country
			$.getJSON("${root}admin/country/select.ajax", function(data){
				var country_select = $("#id-co-id");
				country_select.append("<option value='0'>請選擇國家</option>");
				$.each(data, function(index, country){
					var country_option = $("<option value=" + country.co_id + "></option>").append(country.co_name);
					if(country.co_id == "${param.co_id}"){
						country_option.attr("selected", "selected");
					}
					country_select.append(country_option);
				});
			});
			
			// return city
			$.getJSON("${root}admin/city/select-by-country.ajax", {"ci_co_id": "${param.co_id}"}, function(data){
				var city_select = $("#id-ci-id");
				city_select.empty();
				city_select.append("<option value='0'>請選擇城市</option>");
				$.each(data, function(index, city){
					var city_option = $("<option value=" + city.ci_id + "></option>").append(city.ci_name);
					if(city.ci_id == "${param.ci_id}"){
						city_option.attr("selected", "selected");
					}
					city_select.append(city_option);
				});
			});
			
			// return region			
			$.getJSON("${root}admin/region/select-by-city.ajax", {"r_ci_id": "${param.ci_id}"}, function(data){
				var region_select = $("#id-c-r-id");
				region_select.empty();
				region_select.append("<option value='0'>請選擇區域</option>");
				$.each(data, function(index, region){
					var region_option = $("<option value=" + region.r_id + "></option>").append(region.r_name);
					if(region.r_id == "${param.r_id}"){
						region_option.attr("selected", "selected");
					}
					region_select.append(region_option);
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
					city_select.append("<option value='o'>請選擇城市</option>");
					$.each(data, function(index, city){
						var city_option = $("<option value=" + city.ci_id + "></option>").append(city.ci_name);
						if(city.ci_id == "${param.ci_id}"){
							city_option.attr("selected", "selected");
						}
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