<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>優惠活動輪播圖片一覽 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/shortcut_icon_black.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" type="text/css" />
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
				<h2 class="sub-header">優惠活動輪播圖片一覽</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath()%>/index">首頁</a></li>
						<li class="active">優惠活動輪播圖片一覽</li>
					</ol>
				
					<!-- option -->
					<div class="btn-group option">
						<div class="add-button">
							<a href="<%=request.getContextPath()%>/slider-sale/add" title="新增">
								<img src="<%=request.getContextPath()%>/images/icon_add.svg">
							</a>
						</div>
					</div>
					<!-- option end -->
					
					<!-- table -->
					<table class="table table-bordered">
						<c:forEach var="bean" items="${imageList}" varStatus="status">
							<tbody>
								<tr>
									<td rowspan="4" style="width:50px">${status.count + pageRowCount * (currentPage - 1)}</td>
									<td rowspan="4" style="width:25%"><img src="<%=request.getContextPath()%>/images/slider-sale/${bean.im_filename}"></td>
									<td style="width:50px">名稱</td>
									<td>${bean.im_name}</td>
									<td rowspan="4" style="width:50px">
										<div class="edit-button">
											<a href="<%=request.getContextPath()%>/slider-sale/edit?im_id=${bean.im_id}&page=${currentPage}" title="編輯">
												<img src="<%=request.getContextPath()%>/images/icon_edit.svg">
											</a>
										</div>
									</td>	
									<td rowspan="4" style="width:50px">
										<div class="im-status-switch" data-im-id="${bean.im_id}" title="切換">
											<c:choose>
												<c:when test="${bean.im_status eq 1}">
													<img src="<%=request.getContextPath()%>/images/icon_true.svg" data-im-status="1">
												</c:when>
												<c:when test="${bean.im_status eq 0}">
													<img src="<%=request.getContextPath()%>/images/icon_false.svg" data-im-status="0">
												</c:when>
											</c:choose>
										</div>
									</td>
								</tr>
								<tr>
									<td style="width:50px">檔名</td>
									<td>${bean.im_filename}</td>
								</tr>
								<tr>
									<td style="width:50px">連結</td>
									<td><a href="${bean.im_url}" target="_blank">${bean.im_url}</a></td>
								</tr>
								<tr>
									<td style="width:50px">排序</td>
									<td>${bean.im_rank}</td>
								</tr>
							</tbody>
						</c:forEach>
					</table>
					<!-- table end -->
					
					<!-- pagination -->
					<c:if test="${pageCount > 1}">
						<nav style="text-align:center">
							<ul class="pagination" style="margin:0">
								<c:if test="${currentPage > 1}">
									<li><a href="<%=request.getContextPath()%>/slider-sale/list?page=${currentPage - 1}"><span aria-hidden="true">&laquo;</span></a></li>
								</c:if>
								<c:forEach begin="1" end="${pageCount}" varStatus="status">
									<li id="id-li-page-${status.count}"><a href="<%=request.getContextPath()%>/slider-sale/list?page=${status.count}">${status.count}</a></li>
								</c:forEach>
								<c:if test="${currentPage < pageCount}">
									<li><a href="<%=request.getContextPath()%>/slider-sale/list?page=${currentPage + 1}"><span aria-hidden="true">&raquo;</span></a></li>
								</c:if>
							</ul>
						</nav>
					</c:if>
					<!-- pagination end -->
	
				</div>
				<!-- content end -->
				
			</div>
			<!-- main end -->
			
		</div>
	</div>
	<!-- container end -->
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/image/list.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>