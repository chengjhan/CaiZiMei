<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:url value="/" var="root" />
	<form action="<c:url value='/admin/company/insert.do' />" method="post">
		<div>
			<label for="id-com-name">公司</label>
			<input type="text" id="id-com-name" name="com_name">
		</div>
		<div>
			<label for="id-com-localphone">電話</label>
			<input type="text" id="id-com-localphone" name="com_localphone">
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
				<td>代理商</td>
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
			<c:forEach var="bean" items="${companyList}" varStatus="status">
				<fmt:formatDate value="${bean.com_insert_time}" var="com_insert_time_format" pattern="yyyy-MM-dd hh:mm:ss" />
				<fmt:formatDate value="${bean.com_update_time}" var="com_update_time_format" pattern="yyyy-MM-dd hh:mm:ss" />
				<fmt:formatDate value="${bean.com_status_time}" var="com_status_time_format" pattern="yyyy-MM-dd hh:mm:ss" />
				<c:url value="/admin/company/update" var="path">
					<c:param name="com_id" value="${bean.com_id}" />
					<c:param name="com_name" value="${bean.com_name}" />
					<c:param name="com_localphone" value="${bean.com_localphone}" />
				</c:url>
				<tr>
					<td>${status.count}</td>
					<td>${bean.com_id}</td>
					<td>${bean.com_name}</td>
					<td>${bean.com_localphone}</td>
					<td>${com_insert_time_format}</td>
					<td>${com_update_time_format}</td>
					<td>${bean.com_status}</td>
					<td>${com_status_time_format}</td>
					<td><a href="${path}">修改</a></td>
					<td><a href="${root}admin/company/update-status.do?com_id=${bean.com_id}">狀態</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>