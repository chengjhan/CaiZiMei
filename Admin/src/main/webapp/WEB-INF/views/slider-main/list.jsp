<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>圖片一覽 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
</head>
<body>
	<table border="1">
		<c:forEach var="bean" items="${sliderMainList}" varStatus="status">
			<tbody>
					<tr>
						<td rowspan="5">${status.count}</td>
						<td rowspan="5" style="width:300px"><img style="width:100%" src="<%=request.getContextPath()%>/images/slider-main/${bean.sm_filename}"></td>
						<td>流水號</td>
						<td style="width:300px">${bean.sm_id}</td>
						<td rowspan="5">
							<a href="<%=request.getContextPath()%>/slider-main/edit?sm_id=${bean.sm_id}">編輯</a><br />
							<div class="sm-status-switch" data-sm-id="${bean.sm_id}" style="width:20px;cursor:pointer">
								<c:choose>
									<c:when test="${bean.sm_status eq 1}">
										<img src="<%=request.getContextPath()%>/images/true.svg" data-sm-status="1" style="width:100%">
									</c:when>
									<c:when test="${bean.sm_status eq 0}">
										<img src="<%=request.getContextPath()%>/images/false.svg" data-sm-status="0" style="width:100%">
									</c:when>
								</c:choose>
							</div>
						</td>
					</tr>
					<tr>
						<td>名稱</td>
						<td>${bean.sm_name}</td>
					</tr>
					<tr>
						<td>檔名</td>
						<td>${bean.sm_filename}</td>
					</tr>
					<tr>
						<td>連結</td>
						<td><a href="${bean.sm_url}">${bean.sm_url}</a></td>
					</tr>
					<tr>
						<td>排序</td>
						<td>${bean.sm_rank}</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td>最後更新時間</td> -->
<%-- 						<td>${bean.sm_update_time}</td> --%>
<!-- 					</tr> -->
			</tbody>
		</c:forEach>
	</table>
	<p>
		<a href="<%=request.getContextPath()%>/slider-main/add">新增</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/">首頁</a>
	</p>
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/slider-main/list.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>