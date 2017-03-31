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
	<form action="<c:url value='/admin/region/update.do' />" method="post">
		<div style="display:none">
			<input type="text" id="id-r-id" name="r_id" value="${param.r_id}">
		</div>
		<div>
			<label for="id-co-id">國家</label>
			<select id="id-co-id"></select>
		</div>
		<div>
			<label for="id-r-ci-id">城市</label>
			<select id="id-r-ci-id" name="r_ci_id"></select>
		</div>
		<div>
			<label for="id-r-name">區域</label>
			<input type="text" id="id-r-name" name="r_name" value="${param.r_name}">
		</div>
		<div>
			<label for="id-r-zipcode">郵遞區號</label>
			<input type="text" id="id-r-zipcode" name="r_zipcode" value="${param.r_zipcode}">
		</div>
		<div>
			<label for="id-r-rank">排序</label>
			<input type="text" id="id-r-rank" name="r_rank" value="${param.r_rank}">
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
				var city_select = $("#id-r-ci-id");
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
			
		});
		
		$("#id-co-id").change(function(){
			var co_id = $("#id-co-id").val();
			$.ajax({
				url: '${root}admin/city/select-by-country.ajax?ci_co_id=' + co_id,
				type: 'get',
				dataType: 'json',
				success: function(data){
					var city_select = $("#id-r-ci-id");
					city_select.empty();
					city_select.append("<option value='0'>請選擇城市</option>");
					$.each(data, function(index, city){
						var city_option = $("<option value=" + city.ci_id + "></option>").append(city.ci_name);
						city_select.append(city_option);
					});
				}
			});
		});
	</script>
</body>
</html>