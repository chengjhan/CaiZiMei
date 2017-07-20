<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理員一覽 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<td>編號</td>
				<td>流水號</td>
				<td>帳號</td>
				<td>姓名</td>
				<td>信箱</td>
				<td>註冊時間</td>
				<td>登入次數</td>
				<td>最後登入IP</td>
				<td>最後登入時間</td>
				<td>狀態</td>
				<td>狀態變更時間</td>
				<td>變更</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bean" items="${adminList}" varStatus="status">
				<fmt:formatDate value="${bean.ad_signup_time}" var="ad_signup_time" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:formatDate value="${bean.ad_signin_time}" var="ad_signin_time" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:formatDate value="${bean.ad_status_time}" var="ad_status_time" pattern="yyyy-MM-dd HH:mm:ss" />
				<tr>
					<td>${status.count}</td>
					<td>${bean.ad_id}</td>
					<td>${bean.ad_username}</td>
					<td>${bean.ad_lastname} ${bean.ad_firstname}</td>
					<td>${bean.ad_email}</td>
					<td>${ad_signup_time}</td>
					<td>${bean.ad_signin_number}</td>
					<td>${bean.ad_signin_ip}</td>
					<td>${ad_signin_time}</td>
					<td>${bean.ad_status}</td>
					<td>${ad_status_time}</td>
					<td><a href="<%=request.getContextPath()%>/admin/switch?ad_id=${bean.ad_id}">變更</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="<%=request.getContextPath()%>/">首頁</a>
	</p>
</body>
</html>