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
	<form action="<c:url value='/member/update.controller' />" method="post">
		<div>
			<label for="id-m-lastname">姓氏</label>
			<input type="text" id="id-m-lastname" name="m_lastname" value="${user.m_lastname}">
		</div>
		<div>
			<label for="id-m-firstname">名字</label>
			<input type="text" id="id-m-firstname" name="m_firstname" value="${user.m_firstname}">
		</div>
		<div>
			<label for="id-m-birth">生日</label>
			<input type="text" id="id-m-birth" name="m_birth" value="${user.m_birth}">
		</div>
		<div>
			<label for="id-m-sex">性別</label>
			<select id="id-m-sex" name="m_sex">
				<option>---</option>
				<c:if test="${user.m_sex eq 1}">
					<option value="1" selected>男</option>
					<option value="0">女</option>
				</c:if>
				<c:if test="${user.m_sex eq 0}">
					<option value="1">男</option>
					<option value="0" selected>女</option>
				</c:if>
			</select>
		</div>
		<div>
			<label for="id-m-height">身高</label>
			<input type="text" id="id-m-height" name="m_height" value="${user.m_height}">
		</div>
		<div>
			<label for="id-m-weight">體重</label>
			<input type="text" id="id-m-weight" name="m_weight" value="${user.m_weight}">
		</div>
		<div>
			<label for="id-m-telephone">電話</label>
			<input type="text" id="id-m-telephone" name="m_telephone" value="${user.m_telephone}">
		</div>
		<div>
			<label for="id-m-address">地址</label>
			<input type="text" id="id-m-address" name="m_address" value="${user.m_address}">
		</div>
		<div>
			<label for="id-m-email">信箱</label>
			<input type="text" id="id-m-email" name="m_email" value="${user.m_email}">
		</div>
		<div>
			<input type="submit" id="id-submit" value="修改">
		</div>
	</form>
</body>
</html>