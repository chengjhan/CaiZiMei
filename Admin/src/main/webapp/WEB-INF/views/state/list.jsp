<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>區域一覽 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<td>編號</td>
				<td>流水號</td>
				<td>國家</td>
				<td>區域</td>
				<td>排序</td>
				<td>修改</td>
				<td>刪除</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bean" items="${stateList}" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.s_id}</td>
					<td>${bean.s_CountryBean.co_name}</td>
					<td>${bean.s_name}</td>
					<td>${bean.s_rank}</td>
					<td><a href="<%=request.getContextPath()%>/state/edit?co_id=${bean.s_id}">修改</a></td>
					<td><a href="<%=request.getContextPath()%>/state/delete.do?co_id=${bean.s_id}">刪除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="<%=request.getContextPath()%>/state/add">新增</a>
	</p>
</body>
</html>