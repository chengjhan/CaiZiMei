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
	<form action="<c:url value='/user/purchase/order.do' />" method="post">
		<div>
			<label for="id-co-id">國家</label>
			<select id="id-co-id">
				<option value="0">請選擇國家</option>
			</select>
		</div>
		<div>
			<label for="id-ci-id">城市</label>
			<select id="id-ci-id">
				<option value="0">請選擇城市</option>
			</select>
		</div>
		<div>
			<label for="id-r-id">區域</label>
			<select id="id-r-id">
				<option value="0">請選擇區域</option>
			</select>
		</div>
		<div>
			<label for="id-p-c-id">診所</label>
			<select id="id-p-c-id" name="p_c_id">
				<option value="0">請選擇診所</option>
			</select>
		</div>
		<div>
			<input type="submit" id="id-submit" value="確定">
		</div>
	</form>
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
				var region_select = $("#id-r-id");
				region_select.empty();
				region_select.append("<option value='0'>請選擇區域</option>");
				$.each(data, function(index, region){
					var region_option = $("<option value=" + region.r_id + "></option>").append(region.r_name);
					region_select.append(region_option);
				});
			});
		});
		
		$("#id-r-id").change(function(){
			var r_id = $("#id-r-id").val();
			$.getJSON("${root}admin/clinic/select-by-region.ajax", {"c_r_id": r_id}, function(data){
				var clinic_select = $("#id-p-c-id");
				clinic_select.empty();
				clinic_select.append("<option value='0'>請選擇診所</option>");
				$.each(data, function(index, clinic){
					var clinic_option = $("<option value=" + clinic.c_id + "></option>").append(clinic.c_name);
					clinic_select.append(clinic_option);
				});
			});
		});
	</script>
</body>
</html>