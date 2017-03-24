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
	<form action="<c:url value='/admin/country/insert.do' />" method="post">
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
			<c:forEach var="bean" items="${countryList}">
				<c:url value="/admin/country/update" var="path">
					<c:param name="co_id" value="${bean.co_id}" />
					<c:param name="co_name" value="${bean.co_name}" />
					<c:param name="co_countrycode" value="${bean.co_countrycode}" />
				</c:url>
				<tr>
					<td>${bean.co_id}</td>
					<td>${bean.co_name}</td>
					<td>${bean.co_countrycode}</td>
					<td><a href="${path}">修改</a></td>
					<td><a href="${root}admin/country/delete.do?co_id=${bean.co_id}">刪除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>