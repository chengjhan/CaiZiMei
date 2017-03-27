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
			<select id="id-co-name" name="co_name" style="width:150px"></select>
		</div>
		<div>
			<label for="id-ci-name">城市</label>
			<select id="id-ci-name" name="ci_name" style="width:150px"></select>
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
		var country_option_selected = "${param.co_name}";
		
		$(document).ready(function(){
			
			// return country
			$.getJSON("${root}admin/country/select.ajax", function(data){
				var country_select = $("#id-co-name");
				country_select.append("<option>請選擇國家</option>");
				$.each(data, function(index, country){
					var country_option = $("<option></option>").append(country.co_name);
					if(country.co_name == country_option_selected){
						country_option.attr("selected", "selected");
					}
					country_select.append(country_option);
				});
			});
			
			// return city
			$.getJSON("${root}admin/city/select-by-country.ajax", {"co_name": country_option_selected}, function(data){
				var city_select = $("#id-ci-name");
				city_select.empty();
				city_select.append("<option>請選擇城市</option>");
				$.each(data, function(index, city){
					var city_option = $("<option></option>").append(city.ci_name);
					var city_option_selected = "${param.ci_name}";
					if(city.ci_name == city_option_selected){
						city_option.attr("selected", "selected");
					}
					city_select.append(city_option);
				});
			});
			
		});
		
		$("#id-co-name").change(function(){
			var co_name = $("#id-co-name").val();
			$.ajax({
				url: '${root}admin/city/select-by-country.ajax?co_name=' + co_name,
				type: 'get',
				dataType: 'json',
				success: function(data){
					var city_select = $("#id-ci-name");
					city_select.empty();
					city_select.append("<option>請選擇城市</option>");
					$.each(data, function(index, city){
						var city_option = $("<option></option>").append(city.ci_name);
						var city_option_selected = "${param.ci_name}";
						if(city.ci_name == city_option_selected){
							city_option.attr("selected", "selected");
						}
						city_select.append(city_option);
					});
				}
			});
		});
	</script>
</body>
</html>