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
						<td rowspan="7">${status.count}</td>
						<td rowspan="7" style="width:300px"><img style="width:100%" src="<%=request.getContextPath()%>/images/slider-main/${bean.sm_filename}"></td>
						<td>流水號</td>
						<td style="width:300px">${bean.sm_id}</td>
						<td rowspan="7">
							<a href="<%=request.getContextPath()%>/slider-main/edit?sm_id=${bean.sm_id}">編輯</a><br />
							<a href="<%=request.getContextPath()%>/slider-main/switch?sm_id=${bean.sm_id}">變更</a>
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
						<td>${bean.sm_url}</td>
					</tr>
					<tr>
						<td>排序</td>
						<td>${bean.sm_rank}</td>
					</tr>
					<tr>
						<td>狀態</td>
						<td>${bean.sm_status}</td>
					</tr>
					<tr>
						<td>最後更新時間</td>
						<td>${bean.sm_update_time}</td>
					</tr>
			</tbody>
		</c:forEach>
	</table>
	<p>
		<a href="<%=request.getContextPath()%>/slider-main/add">新增</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/">首頁</a>
	</p>
</body>
</html>