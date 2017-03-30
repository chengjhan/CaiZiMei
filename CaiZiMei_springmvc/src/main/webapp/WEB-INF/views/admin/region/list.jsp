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
	<form action="<c:url value='/admin/region/insert.do' />" method="post">
		<div>
			<label for="id-co-id">國家</label>
			<select id="id-co-id" name="co_id">
				<option>請選擇國家</option>
			</select>
		</div>
		<div>
			<label for="id-r-ci-id">城市</label>
			<select id="id-r-ci-id" name="r_ci_id">
				<option>請選擇城市</option>
			</select>
		</div>
		<div>
			<label for="id-r-name">區域</label>
			<input type="text" id="id-r-name" name="r_name">
		</div>
		<div>
			<label for="id-r-zipcode">郵遞區號</label>
			<input type="text" id="id-r-zipcode" name="r_zipcode">
		</div>
		<div>
			<label for="id-r-rank">排序</label>
			<input type="text" id="id-r-rank" name="r_rank">
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
				<td>城市</td>
				<td>區域</td>
				<td>郵遞區號</td>
				<td>排序</td>
				<td>修改</td>
				<td>刪除</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bean" items="${countryList}">
				<c:url value="/admin/country/update" var="path">
					<c:param name="co_id" value="${bean.co_id}" />
					<c:param name="co_name" value="${bean.co_name}" />
					<c:param name="co_eng_name" value="${bean.co_eng_name}" />
					<c:param name="co_rank" value="${bean.co_rank}" />
				</c:url>
				<tr>
					<td>${bean.co_id}</td>
					<td>${bean.co_name}</td>
					<td>${bean.co_eng_name}</td>
					<td>${bean.co_rank}</td>
					<td><a href="${path}">修改</a></td>
					<td><a href="${root}admin/country/delete.do?co_id=${bean.co_id}">刪除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>