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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
</head>
<body>
	<form:form modelAttribute="cityBean">
		<form:label path="ci_CountryBean">國家</form:label>
		<form:select id="id-input-ci-co-id" path="ci_CountryBean">
			<form:option value="0" label="請選擇國家" />
			<form:options items="${countryList}" itemValue="co_id" itemLabel="co_name" />
		</form:select>
		<form:label path="ci_StateBean">區域</form:label>
		<form:select id="id-input-ci-st-id" path="ci_StateBean">
			<form:option value="0" label="請選擇區域" />
			<form:options items="${stateList}" itemValue="st_id" itemLabel="st_name" />
		</form:select>
	</form:form>
	<table class="table table-bordered" style="width:500px">
		<thead>
			<tr>
				<td>編號</td>
<!-- 				<td>流水號</td> -->
				<td>名稱</td>
				<td>排序</td>
				<td>編輯</td>
				<td>刪除</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bean" items="${cityList}" varStatus="status">
				<tr>
					<td>${status.count}</td>
<%-- 					<td>${bean.ci_id}</td> --%>
					<td>${bean.ci_name}</td>
					<td>${bean.ci_rank}</td>
					<td><a href="<%=request.getContextPath()%>/city/edit?ci_id=${bean.ci_id}">編輯</a></td>
					<td><a href="<%=request.getContextPath()%>/city/delete?ci_id=${bean.ci_id}">刪除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="<%=request.getContextPath()%>/city/add">新增</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/">首頁</a>
	</p>
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/city/list.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>