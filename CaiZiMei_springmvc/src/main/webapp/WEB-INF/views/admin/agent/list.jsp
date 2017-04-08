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
	<form action="<c:url value='/admin/agent/insert.do' />" method="post">
		<div>
			<label for="id-a-name">代理商</label>
			<input type="text" id="id-a-name" name="a_name">
		</div>
		<div>
			<label for="id-a-localphone">電話</label>
			<input type="text" id="id-a-localphone" name="a_localphone">
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
				<td>修改</td>
				<td>刪除</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bean" items="${agentList}" varStatus="status">
				<fmt:formatDate value="${bean.a_insert_time}" var="a_insert_time_format" pattern="yyyy-MM-dd hh-mm-ss" />
				<c:url value="/admin/agent/update" var="path">
					<c:param name="a_id" value="${bean.a_id}" />
					<c:param name="a_name" value="${bean.a_name}" />
					<c:param name="a_localphone" value="${bean.a_localphone}" />
				</c:url>
				<tr>
					<td>${status.count}</td>
					<td>${bean.a_id}</td>
					<td>${bean.a_name}</td>
					<td>${bean.a_localphone}</td>
					<td>${a_insert_time_format}</td>
					<td><a href="${path}">修改</a></td>
					<td><a href="${root}admin/agent/delete.do?a_id=${bean.a_id}">刪除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>