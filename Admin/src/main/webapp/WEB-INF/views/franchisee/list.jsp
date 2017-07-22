<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>加盟店一覽 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
</head>
<body>
	<div>
		<label for="id-input-switch">顯示開啟的加盟店</label>
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
			<c:forEach var="bean" items="${franchiseeList}" varStatus="status">
				<fmt:formatDate value="${bean.fr_insert_time}" var="fr_insert_time_format" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:formatDate value="${bean.fr_update_time}" var="fr_update_time_format" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:formatDate value="${bean.fr_status_time}" var="fr_status_time_format" pattern="yyyy-MM-dd HH:mm:ss" />
				<tr>
					<td>${status.count}</td>
					<td>${bean.fr_id}</td>
					<td>${bean.fr_name}</td>
					<td>${bean.fr_eng_name}</td>
					<td>${bean.fr_localphone}</td>
					<td>${bean.fr_CountryBean.co_name}</td>
					<td>${bean.fr_StateBean.st_name}</td>
					<td>${bean.fr_CityBean.ci_name}</td>
					<td>${bean.fr_address}</td>
					<td>${bean.fr_latitude}</td>
					<td>${bean.fr_longitude}</td>
					<td><a href="${bean.fr_url}">${bean.fr_url}</a></td>
					<td>${fr_insert_time_format}</td>
					<td>${fr_update_time_format}</td>
					<td>${bean.fr_status}</td>
					<td>${fr_status_time_format}</td>
					<td><a href="<%=request.getContextPath()%>/franchisee/edit?fr_id=${bean.fr_id}">編輯</a></td>
					<td><a href="<%=request.getContextPath()%>/franchisee/switch?fr_id=${bean.fr_id}">變更</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="<%=request.getContextPath()%>/franchisee/add">新增</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/">首頁</a>
	</p>
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/franchisee/list.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>