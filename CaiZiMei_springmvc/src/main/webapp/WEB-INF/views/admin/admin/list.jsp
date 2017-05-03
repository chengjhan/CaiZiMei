<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:url value="/" var="root" />
	<form action="<c:url value='/admin/admin/insert.do' />" method="post">
		<div>
			<label for="id-ad-name">總公司</label>
			<input type="text" id="id-ad-name" name="ad_name">
		</div>
		<div>
			<label for="id-ad-localphone">電話</label>
			<input type="text" id="id-ad-localphone" name="ad_localphone">
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
				<td>總公司</td>
				<td>電話</td>
				<td>新增時間</td>
				<td>更新時間</td>
				<td>狀態</td>
				<td>狀態更新時間</td>
				<td>修改</td>
				<td>狀態</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bean" items="${adminList}" varStatus="status">
				<fmt:formatDate value="${bean.ad_insert_time}" var="ad_insert_time_format" pattern="yyyy-MM-dd hh:mm:ss" />
				<fmt:formatDate value="${bean.ad_update_time}" var="ad_update_time_format" pattern="yyyy-MM-dd hh:mm:ss" />
				<fmt:formatDate value="${bean.ad_status_time}" var="ad_status_time_format" pattern="yyyy-MM-dd hh:mm:ss" />
				<c:url value="/admin/admin/update" var="path">
					<c:param name="ad_id" value="${bean.ad_id}" />
					<c:param name="ad_name" value="${bean.ad_name}" />
					<c:param name="ad_localphone" value="${bean.ad_localphone}" />
				</c:url>
				<tr>
					<td>${status.count}</td>
					<td>${bean.ad_id}</td>
					<td>${bean.ad_name}</td>
					<td>${bean.ad_localphone}</td>
					<td>${ad_insert_time_format}</td>
					<td>${ad_update_time_format}</td>
					<td>${bean.ad_status}</td>
					<td>${ad_status_time_format}</td>
					<td><a href="${path}">修改</a></td>
					<td><a href="${root}admin/admin/update-status.do?ad_id=${bean.ad_id}">狀態</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>