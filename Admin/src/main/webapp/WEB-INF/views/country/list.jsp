<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>國家一覽 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<td>編號</td>
				<td>流水號</td>
				<td>代碼</td>
				<td>國家名</td>
				<td>電話碼</td>
				<td>排序</td>
				<td>修改</td>
				<td>刪除</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bean" items="${countryList}" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.co_id}</td>
					<td>${bean.co_iso}</td>
					<td>${bean.co_name}</td>
					<td>${bean.co_phonecode}</td>
					<td>${bean.co_rank}</td>
					<td><a href="<%=request.getContextPath()%>/country/edit?co_id=${bean.co_id}">修改</a></td>
					<td><a href="<%=request.getContextPath()%>/country/delete.do?co_id=${bean.co_id}">刪除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="<%=request.getContextPath()%>/country/add">新增</a>
	</p>
</body>
</html>