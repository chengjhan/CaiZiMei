<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>診所一覽 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
</head>
<body>
	<div>
		<label for="id-input-switch">顯示開啟的診所</label>
		<input type="checkbox" id="id-input-switch" />
	</div>
	<table border="1" style="font-size:13px">
		<thead>
			<tr>
				<td>編號</td>
				<td>流水號</td>
				<td>名稱</td>
				<td>英文名稱</td>
				<td>電話</td>
				<td>國家</td>
				<td>區域</td>
				<td>城市</td>
				<td>地址</td>
				<td>緯度</td>
				<td>經度</td>
				<td>網址</td>
				<td>新增時間</td>
				<td>更新時間</td>
				<td>狀態</td>
				<td>狀態更新時間</td>
				<td>編輯</td>
				<td>狀態</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bean" items="${clinicList}" varStatus="status">
				<fmt:formatDate value="${bean.cl_insert_time}" var="cl_insert_time_format" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:formatDate value="${bean.cl_update_time}" var="cl_update_time_format" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:formatDate value="${bean.cl_status_time}" var="cl_status_time_format" pattern="yyyy-MM-dd HH:mm:ss" />
				<tr>
					<td>${status.count}</td>
					<td>${bean.cl_id}</td>
					<td>${bean.cl_name}</td>
					<td>${bean.cl_eng_name}</td>
					<td>${bean.cl_localphone}</td>
					<td>${bean.cl_CountryBean.co_name}</td>
					<td>${bean.cl_StateBean.st_name}</td>
					<td>${bean.cl_CityBean.ci_name}</td>
					<td>${bean.cl_address}</td>
					<td>${bean.cl_latitude}</td>
					<td>${bean.cl_longitude}</td>
					<td><a href="${bean.cl_url}">${bean.cl_url}</a></td>
					<td>${cl_insert_time_format}</td>
					<td>${cl_update_time_format}</td>
					<td>${bean.cl_status}</td>
					<td>${cl_status_time_format}</td>
					<td><a href="<%=request.getContextPath()%>/clinic/edit?cl_id=${bean.cl_id}">編輯</a></td>
					<td><a href="<%=request.getContextPath()%>/clinic/switch?cl_id=${bean.cl_id}">變更</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="<%=request.getContextPath()%>/clinic/add">新增</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/">首頁</a>
	</p>
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/clinic/list.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>