<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>個人資訊 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/shortcut_icon_black.ico" type="image/x-icon" />
</head>
<body>
	<!-- header -->
	<jsp:include page="../header.jsp" />
	
	<!-- container -->
	<div class="container-fluid">
		<div class="row">
		
			<!-- menu-personal -->
			<jsp:include page="../menu-personal.jsp" />
			
			<!-- main -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			
				<!-- title -->
				<h2 class="sub-header">個人資訊</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- table -->
					<table class="table table-striped" style="width:500px">
						<tr>
							<td width="150px">姓名</td>
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
</body>
</html>