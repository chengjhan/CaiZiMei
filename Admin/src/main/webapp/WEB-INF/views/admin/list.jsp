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
				<td>變更狀態時間</td>
				<td>變更狀態</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bean" items="${adminList}" varStatus="status">
				<fmt:formatDate value="${bean.a_signup_time}" var="a_signup_time" pattern="yyyy-MM-dd hh:mm:ss" />
				<fmt:formatDate value="${bean.a_signin_time}" var="a_signin_time" pattern="yyyy-MM-dd hh:mm:ss" />
				<fmt:formatDate value="${bean.a_status_time}" var="a_status_time" pattern="yyyy-MM-dd hh:mm:ss" />
				<tr>
					<td>${status.count}</td>
					<td>${bean.a_id}</td>
					<td>${bean.a_username}</td>
					<td>${bean.a_lastname} ${bean.a_firstname}</td>
					<td>${bean.a_email}</td>
					<td>${a_signup_time}</td>
					<td>${bean.a_signin_number}</td>
					<td>${bean.a_signin_ip}</td>
					<td>${a_signin_time}</td>
					<td>${bean.a_status}</td>
					<td>${a_status_time}</td>
					<td><a href="<%=request.getContextPath()%>/admin/switch.do?a_id=${bean.a_id}">變更</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>