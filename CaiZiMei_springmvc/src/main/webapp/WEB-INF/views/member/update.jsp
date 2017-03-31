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
	<form action="<c:url value='/member/update.do' />" method="post">
		<div>
			<label for="id-m-lastname">姓氏</label>
			<input type="text" id="id-m-lastname" name="m_lastname" value="${user.m_lastname}">
		</div>
		<div>
			<label for="id-m-firstname">名字</label>
			<input type="text" id="id-m-firstname" name="m_firstname" value="${user.m_firstname}">
		</div>
		<div>
			<label for="id-m-birth">生日</label>
			<input type="text" id="id-m-birth" name="m_birth" value="${user.m_birth}">
		</div>
		<div>
			<label for="id-m-sex">性別</label>
			<select id="id-m-sex" name="m_sex">
				<option value="0">--</option>
				<c:if test="${user.m_sex eq 0}">
					<option value="1">男</option>
					<option value="2">女</option>
				</c:if>
				<c:if test="${user.m_sex eq 1}">
					<option value="1" selected="selected">男</option>
					<option value="2">女</option>
				</c:if>
				<c:if test="${user.m_sex eq 2}">
					<option value="1">男</option>
					<option value="2" selected="selected">女</option>
				</c:if>
			</select>
		</div>
		<div>
			<label for="id-m-height">身高</label>
			<input type="text" id="id-m-height" name="m_height" value="${user.m_height}">
		</div>
		<div>
			<label for="id-m-weight">體重</label>
			<input type="text" id="id-m-weight" name="m_weight" value="${user.m_weight}">
		</div>
		<div>
			<label for="id-m-localphone">電話</label>
			<input type="text" id="id-m-localphone" name="m_localphone" value="${user.m_localphone}">
		</div>
		<div>
			<label for="id-m-mobilephone">手機</label>
			<input type="text" id="id-m-mobilephone" name="m_mobilephone" value="${user.m_mobilephone}">
		</div>
		<div>
			<label for="id-m-zipcode">郵遞區號</label>
			<input type="text" readonly id="id-m-zipcode" name="m_zipcode" value="${user.m_zipcode}">
		</div>
		<div>
			<label for="id-m-country">國家</label>
			<select id="id-m-country" name="m_country" style="width:150px"></select>
		</div>
		<div>
			<label for="id-m-city">城市</label>
			<select id="id-m-city" name="m_city" style="width:150px"></select>
		</div>
		<div>
			<label for="id-m-region">區域</label>
			<select id="id-m-region" name="m_region" style="width:150px"></select>
		</div>
		<div>
			<label for="id-m-address">地址</label>
			<input type="text" id="id-m-address" name="m_address" value="${user.m_address}">
		</div>
		<div>
			<label for="id-m-email">信箱</label>
			<input type="text" id="id-m-email" name="m_email" value="${user.m_email}">
		</div>
		<div>
			<input type="submit" id="id-submit" value="修改">
		</div>
	</form>
	<script>
		$(document).ready(function(){
			
			// return country
			$.getJSON("${root}admin/country/select.ajax", function(data){
				var country_select = $("#id-m-country");
				country_select.append("<option value='0'>請選擇國家</option>");
				$.each(data, function(index, country){
					var country_option = $("<option value=" + country.co_id + "></option>").append(country.co_name);
					if(country.co_id == "${user.m_country}"){
						country_option.attr("selected", "selected");
					}
					country_select.append(country_option);
				});
			});
			
			// return city
			$.getJSON("${root}admin/city/select-by-country.ajax", {"ci_co_id": "${user.m_country}"}, function(data){
				var city_select = $("#id-m-city");
				city_select.empty();
				city_select.append("<option value='0'>請選擇城市</option>");
				$.each(data, function(index, city){
					var city_option = $("<option value=" + city.ci_id + "></option>").append(city.ci_name);
					if(city.ci_id == "${user.m_city}"){
						city_option.attr("selected", "selected");
					}
					city_select.append(city_option);
				});
			});
			
			// return region			
			$.getJSON("${root}admin/region/select-by-city.ajax", {"r_ci_id": "${user.m_city}"}, function(data){
				var region_select = $("#id-m-region");
				region_select.empty();
				region_select.append("<option value='0'>請選擇區域</option>");
				$.each(data, function(index, region){
					var region_option = $("<option value=" + region.r_id + "></option>").append(region.r_name);
					if(region.r_id == "${user.m_region}"){
						region_option.attr("selected", "selected");
					}
					region_select.append(region_option);
				});
			});
			
		});
		
		$("#id-m-country").change(function(){
			var co_id = $("#id-m-country").val();
			$.ajax({
				url: '${root}admin/city/select-by-country.ajax?ci_co_id=' + co_id,
				type: 'get',
				dataType: 'json',
				success: function(data){
					var city_select = $("#id-m-city");
					city_select.empty();
					city_select.append("<option value='0'>請選擇城市</option>");
					$.each(data, function(index, city){
						var city_option = $("<option value=" + city.ci_id + "></option>").append(city.ci_name);
						city_select.append(city_option);
					});
				}
			});
		});
		
		$("#id-m-city").change(function(){
			var ci_id = $("#id-m-city").val();
			$.getJSON("${root}admin/region/select-by-city.ajax", {"r_ci_id": ci_id}, function(data){
				var region_select = $("#id-m-region");
				region_select.empty();
				region_select.append("<option value='0'>請選擇區域</option>");
				$.each(data, function(index, region){
					var region_option = $("<option value=" + region.r_id + "></option>").append(region.r_name);
					region_select.append(region_option);
				});
			});
		});
		
		$("#id-m-region").change(function(){
			var r_id = $("#id-m-region").val();
			$.get("${root}admin/region/select-by-id.ajax", {"r_id": r_id}, function(data){
				$("#id-m-zipcode").val(data);
			});
		});
	</script>
</body>
</html>