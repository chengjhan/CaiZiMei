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
			<select id="id-co-name" name="co_name">
				<option>請選擇國家</option>
			</select>
		</div>
		<div>
			<label for="id-ci-name">城市</label>
			<select id="id-ci-name" name="ci_name">
				<option>請選擇城市</option>
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
	<script>
		$(document).ready(function(){
			var country_select = $("#id-co-name");
			$.getJSON("${root}admin/country/select.ajax", function(data){
				$.each(data, function(index, country){
					var country_option = $("<option></option>").append(country.co_name);
					country_select.append(country_option);
				});
			});
		});
	
		$("#id-co-name").change(function(){
			var co_name = $("#id-co-name").val();
			var city_select = $("#id-ci-name");
			$.ajax({
				url: '${root}admin/city/select.ajax?co_name=' + co_name,
				type: 'get',
				dataType: 'json',
				success: function(data){
					city_select.empty();
					city_select.append("<option>請選擇城市</option>");
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