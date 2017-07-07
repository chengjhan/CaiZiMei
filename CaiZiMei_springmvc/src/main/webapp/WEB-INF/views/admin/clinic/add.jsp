<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增診所 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
</head>
<body>
	<c:url value="/" var="root" />
	<form action="<c:url value='/admin/clinic/insert.do' />" method="post">
		<div>
			<label for="id-c-name">診所</label>
			<input type="text" id="id-c-name" name="c_name">
		</div>
		<div>
			<label for="id-c-eng-name">診所英文</label>
			<input type="text" id="id-c-eng-name" name="c_eng_name">
		</div>
		<div>
			<label for="id-c-localphone">電話</label>
			<input type="text" id="id-c-localphone" name="c_localphone_front">
			<input type="text" id="id-c-localphone" name="c_localphone_back">
		</div>
		<div>
			<label for="id-co-id">國家</label>
			<select id="id-co-id" style="width:150px">
				<option value="0">請選擇國家</option>
			</select>
		</div>
		<div>
			<label for="id-ci-id">城市</label>
			<select id="id-ci-id" style="width:150px">
				<option value="0">請選擇城市</option>
			</select>
		</div>
		<div>
			<label for="id-c-r-id">區域</label>
			<select id="id-c-r-id" name="c_r_id" style="width:150px">
				<option value="0">請選擇區域</option>
			</select>
		</div>
		<div>
			<label for="id-c-address">地址</label>
			<input type="text" id="id-c-address" name="c_address">
		</div>
		<div>
			<label for="id-c-url">網址</label>
			<input type="text" id="id-c-url" name="c_url">
		</div>
		<div>
			<input type="submit" id="id-submit" value="新增">
		</div>
	</form>
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/admin/clinic/add.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>