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
	<%
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);

		CountryService countryService = (CountryService) context.getBean("countryService");
		List<CountryBean> list = countryService.select();
		request.setAttribute("select", list);
	%>
	<c:url value="/" var="root" />
	<form action="<c:url value='/country/insert.controller' />" method="post">
		<div>
			<label for="id-co-name">國家</label>
			<input type="text" id="id-co-name" name="co_name">
		</div>
		<div>
			<label for="id-co-countrycode">代碼</label>
			<input type="text" id="id-co-countrycode" name="co_countrycode">
		</div>
		<div>
			<input type="submit" id="id-submit" value="新增">
		</div>
	</form>
	<table border="1">
		<thead>
			<tr>
				<td>編號</td>
				<td>國家</td>
				<td>代碼</td>
				<td>修改</td>
				<td>刪除</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bean" items="${select}">
				<c:url value="/country/update.jsp" var="path">
					<c:param name="co_id" value="${bean.co_id}" />
					<c:param name="co_name" value="${bean.co_name}" />
					<c:param name="co_countrycode" value="${bean.co_countrycode}" />
				</c:url>
				<tr>
					<td>${bean.co_id}</td>
					<td>${bean.co_name}</td>
					<td>${bean.co_countrycode}</td>
					<td><a href="${path}">修改</a></td>
					<td><a href="${root}country/delete.controller?co_id=${bean.co_id}">刪除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<table id="id-table" border="1">
		<thead>
			<tr>
				<td>編號</td>
				<td>國家</td>
				<td>代碼</td>
				<td>修改</td>
				<td>刪除</td>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<script>
		$(document).ready(select);
		$(document).on("click", "#id-submit", select);
		
		function select(){
			$.ajax({
				url: '${root}country/select.ajax',
				type: 'get',
				dataType: 'json',
				success: function(data){
					var tb = $("#id-table>tbody");
					tb.empty();
					$.each(data, function(index, country){
						var cell1 = $("<td></td>").text(country.co_id);
						var cell2 = $("<td></td>").text(country.co_name);
						var cell3 = $("<td></td>").text(country.co_countrycode);
						var cell4_a = $("<a></a>").attr("href", "${root}country/update.jsp?co_id=" + country.co_id + "co_country=" + country.co_name + "co_countrycode=" + country.co_countrycode).text("修改");
						var cell4 = $("<td></td>").append(cell4_a);
						var cell5_a = $("<a></a>").attr("href", "${root}country/delete.controller?co_id=" + country.co_id).text("刪除"); 
						var cell5 = $("<td></td>").append(cell5_a);
						var row = $("<tr></tr>").append([ cell1, cell2, cell3, cell4, cell5 ]);
						tb.append(row);
					});
				}
			})
		}
	</script>
</body>
</html>