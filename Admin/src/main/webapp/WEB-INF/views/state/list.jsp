<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>區域一覽 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
</head>
<body>
	<form:form modelAttribute="stateBean">
		<form:label path="st_CountryBean">國家</form:label>
		<form:select id="id-input-st-co-id" path="st_CountryBean">
			<form:option value="0" label="請選擇國家" />
			<form:options items="${countryList}" itemValue="co_id" itemLabel="co_name" />
		</form:select>
	</form:form>
	<table border="1">
		<thead>
			<tr>
				<td>編號</td>
				<td>流水號</td>
				<td>名稱</td>
				<td>排序</td>
				<td>編輯</td>
				<td>刪除</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bean" items="${stateList}" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.st_id}</td>
					<td>${bean.st_name}</td>
					<td>${bean.st_rank}</td>
					<td><a href="<%=request.getContextPath()%>/state/edit?st_id=${bean.st_id}">編輯</a></td>
					<td><a href="<%=request.getContextPath()%>/state/delete?st_id=${bean.st_id}">刪除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="<%=request.getContextPath()%>/state/add">新增</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/">首頁</a>
	</p>
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/state/list.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>