<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理員一覽 | 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_black_32x32.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/list.css" type="text/css" />
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
				<h2 class="sub-header">管理員一覽</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- table -->
					<table class="table table-bordered">
						<thead>
							<tr>
								<td>編號</td>
<!-- 								<td>流水號</td> -->
								<td>帳號</td>
								<td>姓名</td>
								<td>註冊時間</td>
								<td>登入次數</td>
								<td>最後登入IP</td>
								<td>最後登入時間</td>
								<td>狀態變更時間</td>
								<td>開啟</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="bean" items="${adminList}" varStatus="status">
								<fmt:formatDate value="${bean.ad_signup_time}" var="ad_signup_time" pattern="yyyy-MM-dd HH:mm:ss" />
								<fmt:formatDate value="${bean.ad_signin_time}" var="ad_signin_time" pattern="yyyy-MM-dd HH:mm:ss" />
								<fmt:formatDate value="${bean.ad_status_time}" var="ad_status_time" pattern="yyyy-MM-dd HH:mm:ss" />
								<tr>
									<td>${status.count}</td>
<%-- 									<td>${bean.ad_id}</td> --%>
									<td>${bean.ad_username}</td>
									<td>${bean.ad_lastname} ${bean.ad_firstname}</td>
									<td>${ad_signup_time}</td>
									<td>${bean.ad_signin_number}</td>
									<td>${bean.ad_signin_ip}</td>
									<td>${ad_signin_time}</td>
									<td>${ad_status_time}</td>
									<td>
										<div class="ad-status-switch" data-ad-id="${bean.ad_id}">
											<c:choose>
												<c:when test="${bean.ad_status eq 1}">
													<img src="<%=request.getContextPath()%>/images/true.svg" data-ad-status="1">
												</c:when>
												<c:when test="${bean.ad_status eq 0}">
													<img src="<%=request.getContextPath()%>/images/false.svg" data-ad-status="0">
												</c:when>
											</c:choose>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
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
	<script src="<%=request.getContextPath()%>/js/admin/list.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>