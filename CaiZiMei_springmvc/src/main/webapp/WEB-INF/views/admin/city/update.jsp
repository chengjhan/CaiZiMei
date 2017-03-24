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
	<form action="<c:url value='/admin/city/update.do' />" method="post">
		<div style="display: none">
			<input type="text" id="id-ci-id" name="ci_id" value="${param.ci_id}">
		</div>
		<div>
			<label for="id-co-name">國家</label>
			<select id="id-co-name" name="co_name">
				<option>請選擇國家</option>
			</select>
		</div>
		<div>
			<label for="id-ci-name">城市</label>
			<input type="text" id="id-ci-name" name="ci_name" value="${param.ci_name}">
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
	</script>
</body>
</html>