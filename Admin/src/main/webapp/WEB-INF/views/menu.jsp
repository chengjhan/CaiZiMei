<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>menu</title>
</head>
<body>
	<div class="col-sm-3 col-md-2 sidebar">
		<c:choose>
			<c:when test="${admin.ad_authority eq 1}">
				<ul class="nav nav-sidebar">
					<li id="id-li-admin-sign-up"><a href="<%=request.getContextPath()%>/admin/sign-up">管理員註冊</a></li>
					<li id="id-li-admin-list"><a href="<%=request.getContextPath()%>/admin/list?page=1">管理員一覽</a></li>
				</ul>
			</c:when>
		</c:choose>
		<ul class="nav nav-sidebar">
			<li id="id-li-country-list"><a href="<%=request.getContextPath()%>/country/list">國家一覽</a></li>
			<li id="id-li-state-list"><a href="<%=request.getContextPath()%>/state/list">區域一覽</a></li>
			<li id="id-li-city-list"><a href="<%=request.getContextPath()%>/city/list">城市一覽</a></li>
		</ul>
		<ul class="nav nav-sidebar">
			<li id="id-li-office-list"><a href="<%=request.getContextPath()%>/office/list?page=1">辦事處一覽</a></li>
			<li id="id-li-franchisee-list"><a href="<%=request.getContextPath()%>/franchisee/list?page=1">加盟店一覽</a></li>
			<li id="id-li-clinic-list"><a href="<%=request.getContextPath()%>/clinic/list?page=1">診所一覽</a></li>
		</ul>
		<ul class="nav nav-sidebar">
			<li id="id-li-slider-main-list"><a href="<%=request.getContextPath()%>/slider-main/list?page=1">主輪播圖片一覽</a></li>
			<li id="id-li-slider-franchisee-list"><a href="<%=request.getContextPath()%>/slider-franchisee/list?page=1">加盟店資訊輪播圖片一覽</a></li>
			<li id="id-li-slider-recent-list"><a href="<%=request.getContextPath()%>/slider-recent/list?page=1">近期活動輪播圖片一覽</a></li>
			<li id="id-li-slider-sale-list"><a href="<%=request.getContextPath()%>/slider-sale/list?page=1">優惠活動輪播圖片一覽</a></li>
			<li id="id-li-slider-knowledge-list"><a href="<%=request.getContextPath()%>/slider-knowledge/list?page=1">醫療新知輪播圖片一覽</a></li>
			<li id="id-li-slider-doctor-list"><a href="<%=request.getContextPath()%>/slider-doctor/list?page=1">醫療團隊輪播圖片一覽</a></li>
		</ul>
		<ul class="nav nav-sidebar">
			<li id="id-li-video-main-list"><a href="<%=request.getContextPath()%>/video-main/list?page=1">相關影音一覽 (首頁)</a></li>
			<li id="id-li-video-related-list"><a href="<%=request.getContextPath()%>/video-related/list?page=1">相關影音一覽 (相關資訊)</a></li>
		</ul>
	</div>
	
	<!-- load -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/menu.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>