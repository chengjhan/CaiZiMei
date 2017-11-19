<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>醫療團隊輪播圖片一覽 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/shortcut_icon_black.ico" type="image/x-icon" />
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
				<h2 class="sub-header">醫療團隊輪播圖片一覽</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath()%>/index">首頁</a></li>
						<li class="active">醫療團隊輪播圖片一覽</li>
					</ol>
				
					<!-- option -->
					<jsp:include page="../option.jsp" />
					
					<!-- table -->
					<table class="table table-bordered image-list-table">
						<c:forEach var="bean" items="${imageList}" varStatus="status">
							<tbody>
								<tr>
									<td rowspan="4">${status.count + pageRowCount * (currentPage - 1)}</td>
									<td rowspan="4"><img src="<%=request.getContextPath()%>/images/slider-doctor/${bean.im_filename}" /></td>
									<td>名稱</td>
									<td>${bean.im_name}</td>
									<td rowspan="4">
										<div class="edit-button">
											<a href="<%=request.getContextPath()%>/slider-doctor/edit?im_id=${bean.im_id}&page=${currentPage}" title="編輯">
												<img src="<%=request.getContextPath()%>/images/icon_edit.svg" />
											</a>
										</div>
									</td>	
									<td rowspan="4">
										<div class="im-status-switch" data-im-id="${bean.im_id}" title="切換">
											<c:choose>
												<c:when test="${bean.im_status eq 1}">
													<img src="<%=request.getContextPath()%>/images/icon_true.svg" data-im-status="1" />
												</c:when>
												<c:when test="${bean.im_status eq 0}">
													<img src="<%=request.getContextPath()%>/images/icon_false.svg" data-im-status="0" />
												</c:when>
											</c:choose>
										</div>
									</td>
								</tr>
								<tr>
									<td>檔名</td>
									<td>${bean.im_filename}</td>
								</tr>
								<tr>
									<td>連結</td>
									<td><a href="${bean.im_url}" target="_blank">${bean.im_url}</a></td>
								</tr>
								<tr>
									<td>排序</td>
									<td>${bean.im_rank}</td>
								</tr>
							</tbody>
						</c:forEach>
					</table>
					<!-- table end -->
					
					<!-- pagination -->
					<jsp:include page="../pagination.jsp" />
	
				</div>
				<!-- content end -->
				
			</div>
			<!-- main end -->
			
		</div>
	</div>
	<!-- container end -->
	
	<!-- image zoom in -->
	<div class="image-zoom" style="display:none">
		<div><img /></div>
	</div>
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/dashboard.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>