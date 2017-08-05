<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>個人資訊 | 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_black_32x32.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/all.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/profile.css" type="text/css" />
</head>
<body>
	<!-- header -->
	<jsp:include page="../header.jsp" />
	
	<!-- container -->
	<div class="container-fluid">
		<div class="row">
		
			<!-- menu -->
			<jsp:include page="../menu.jsp" />
			
			<!-- main -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			
				<!-- title -->
				<h2 class="sub-header">個人資訊</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- option -->
					<div class="btn-group option">
						<div class="edit-button">
							<a href="<%=request.getContextPath()%>/admin/edit">
								<img src="<%=request.getContextPath()%>/images/edit.svg">
							</a>
						</div>
					</div>
				
					<!-- table -->
					<table class="table table-bordered" style="width:500px">
						<tr>
							<td>姓名</td>
							<td>${admin.ad_lastname} ${admin.ad_firstname}</td>
						</tr>
						<tr>
							<td>信箱</td>
							<td>${admin.ad_email}</td>
						</tr>
						<tr>
							<fmt:formatDate value="${admin.ad_signup_time}" var="ad_signup_time" pattern="yyyy-MM-dd HH:mm:ss" />
							<td>註冊時間</td>
							<td>${ad_signup_time}</td>
						</tr>
						<tr>
							<td>登入次數</td>
							<td>${admin.ad_signin_number}</td>
						</tr>
						<tr>
							<td>最後登入IP</td>
							<td>${admin.ad_signin_ip}</td>
						</tr>
						<tr>
							<fmt:formatDate value="${admin.ad_signin_time}" var="ad_signin_time" pattern="yyyy-MM-dd HH:mm:ss" />
							<td>最後登入時間</td>
							<td>${ad_signin_time}</td>
						</tr>
						<tr>
							<fmt:formatDate value="${admin.ad_update_info_time}" var="ad_update_info_time" pattern="yyyy-MM-dd HH:mm:ss" />
							<td>最後修改資料時間</td>
							<td>${ad_update_info_time}</td>
						</tr>
						<tr>
							<fmt:formatDate value="${admin.ad_update_pwd_time}" var="ad_update_pwd_time" pattern="yyyy-MM-dd HH:mm:ss" />
							<td>最後變更密碼時間</td>
							<td>${ad_update_pwd_time}</td>
						</tr>
					</table>
					<!-- table end -->
	
				</div>
				<!-- content end -->
				
			</div>
			<!-- main end -->
			
		</div>
	</div>
	<!-- container end -->
	
	<!-- load -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</body>
</html>