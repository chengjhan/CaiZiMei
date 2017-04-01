<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value='/admin/member/select.do' />" method="get">
		<div>
			<label for="id-m-lastname">姓氏</label>
			<input type="text" id="id-m-lastname" name="m_lastname">
		</div>
		<div>
			<label for="id-m-firstname">名字</label>
			<input type="text" id="id-m-firstname" name="m_firstname">
		</div>
		<div>
			<label for="id-m-mobilephone">手機</label>
			<input type="text" id="id-m-mobilephone" name="m_mobilephone">
		</div>
		<div>
			<label for="id-m-email">信箱</label>
			<input type="text" id="id-m-email" name="m_email">
		</div>
		<div>
			<input type="submit" id="id-submit" value="搜尋">
		</div>
	</form>
	<table border="1">
		<thead>
			<tr>
				<td>編號</td>
				<td>流水號</td>
				<td>帳號</td>
				<td>姓名</td>
				<td>生日</td>
				<td>性別</td>
				<td>身高</td>
				<td>體重</td>
				<td>電話</td>
				<td>手機</td>
				<td>地址</td>
				<td>信箱</td>
				<td>註冊時間</td>
				<td>最後登入時間</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bean" items="${selectByConditions}" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.m_id}</td>
					<td>${bean.m_username}</td>
					<td>${bean.m_lastname} ${bean.m_firstname}</td>
					<td>${bean.m_birth}</td>
					<td>${bean.m_sex}</td>
					<td>${bean.m_height}</td>
					<td>${bean.m_weight}</td>
					<td>${bean.m_localphone}</td>
					<td>${bean.m_mobilephone}</td>
					<td>${bean.m_zipcode} ${bean.m_country} ${bean.m_city} ${bean.m_region} ${bean.m_address}</td>
					<td>${bean.m_email}</td>
					<td>${bean.m_signup_time}</td>
					<td>${bean.m_signin_time}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>