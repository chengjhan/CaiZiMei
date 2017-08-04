<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>個人資訊 | 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_black_32x32.ico" type="image/x-icon" />
</head>
<body>
	<!-- header -->
	<jsp:include page="../header.jsp" />
	
	<div class="container-fluid">
		<div class="row">
		
			<!-- menu -->
			<jsp:include page="../menu.jsp" />
			
			<!-- content -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h2 class="sub-header">個人資訊</h2>
				<div class="table-responsive">
					<a href="<%=request.getContextPath()%>/admin/edit">編輯</a>
				
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
					</table>
					<!-- table end -->
	
				</div>
			</div>
			<!-- content end -->
			
		</div>
	</div>
</body>
</html>