<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>城市一覽 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
</head>
<body>
	<div>
		<label for="id-input-ci-co-id">國家</label>
		<select id="id-input-ci-co-id" style="width:150px">
			<option value="0">請選擇國家</option>
		</select>
	</div>
	<div>
		<label for="id-input-ci-s-id">區域</label>
		<select id="id-input-ci-s-id" style="width:150px">
			<option value="0">請選擇區域</option>
		</select>
	</div>
	<table border="1">
		<thead>
			<tr>
				<td>編號</td>
				<td>流水號</td>
				<td>名稱</td>
				<td>排序</td>
				<td>修改</td>
				<td>刪除</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bean" items="${cityList}" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.ci_id}</td>
					<td>${bean.ci_name}</td>
					<td>${bean.ci_rank}</td>
					<td><a href="<%=request.getContextPath()%>/city/edit?s_id=${bean.ci_id}">修改</a></td>
					<td><a href="<%=request.getContextPath()%>/city/delete?s_id=${bean.ci_id}">刪除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="<%=request.getContextPath()%>/city/add">新增</a>
	</p>
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/city/list.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>