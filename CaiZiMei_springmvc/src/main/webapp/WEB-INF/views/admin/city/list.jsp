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
	<form action="<c:url value='/admin/city/insert.do' />" method="post">
		<div>
			<label for="id-ci-co-id">國家</label>
			<select id="id-ci-co-id" name="ci_co_id">
				<option value="0">請選擇國家</option>
			</select>
		</div>
		<div>
			<label for="id-ci-name">城市</label>
			<input type="text" id="id-ci-name" name="ci_name">
		</div>
		<div>
			<label for="id-ci-rank">排序</label>
			<input type="text" id="id-ci-rank" name="ci_rank">
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
				<td>國家</td>
				<td>城市</td>
				<td>排序</td>
				<td>修改</td>
				<td>刪除</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bean" items="${cityList}" varStatus="status">
				<c:url value="/admin/city/update" var="path">
					<c:param name="ci_id" value="${bean.ci_id}" />
					<c:param name="ci_co_id" value="${bean.ci_CountryBean.co_id}" />
					<c:param name="ci_name" value="${bean.ci_name}" />
					<c:param name="ci_rank" value="${bean.ci_rank}" />
				</c:url>
				<tr>
					<td>${status.count}</td>
					<td>${bean.ci_id}</td>
					<td>${bean.ci_CountryBean.co_name}</td>
					<td>${bean.ci_name}</td>
					<td>${bean.ci_rank}</td>
					<td><a href="${path}">修改</a></td>
					<td><a href="${root}admin/city/delete.do?ci_id=${bean.ci_id}">刪除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script>
		$(document).ready(function(){
			$.getJSON("${root}admin/country/select.ajax", function(data){
				$.each(data, function(index, country){
					var country_option = $("<option value=" + country.co_id + "></option>").append(country.co_name);
					$("#id-ci-co-id").append(country_option);
				});
			});
		});
	</script>
</body>
</html>