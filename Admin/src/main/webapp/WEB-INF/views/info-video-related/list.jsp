<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>相關影音頁面一覽 - 采姿美管理系統</title>
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
				<h2 class="sub-header">相關影音頁面一覽</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath()%>/index">首頁</a></li>
						<li class="active">相關影音頁面一覽</li>
					</ol>
				
					<!-- option -->
					<div class="btn-group option">
						<div class="add-button">
							<a href="<%=request.getContextPath()%>/info-video-related/add" title="新增">
								<img src="<%=request.getContextPath()%>/images/icon_add.svg">
							</a>
						</div>
					</div>
					<!-- option end -->
					
					<!-- table -->
					<table class="table table-bordered">
						<thead>
							<tr>
								<td style="width:50px">編號</td>
								<td>名稱</td>
								<td style="width:560px">影片</td>
								<td style="width:50px">排序</td>
								<td style="width:50px">編輯</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="bean" items="${videoList}" varStatus="status">
								<tr>
									<td>${status.count + pageRowCount * (currentPage - 1)}</td>
									<td>${bean.vi_name}</td>
									<td>${bean.vi_tag}</td>
									<td>${bean.vi_rank}</td>
									<td>
										<div class="edit-button">
											<a href="<%=request.getContextPath()%>/info-video-related/edit?vi_id=${bean.vi_id}&page=${currentPage}" title="編輯">
												<img src="<%=request.getContextPath()%>/images/icon_edit.svg">
											</a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- table end -->
					
					<!-- pagination -->
					<c:if test="${pageCount > 1}">
						<nav style="text-align:center">
							<ul class="pagination" style="margin:0">
								<c:if test="${currentPage > 1}">
									<li><a href="<%=request.getContextPath()%>/info-video-related/list?page=${currentPage - 1}"><span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>
								</c:if>
								<c:forEach begin="1" end="${pageCount}" varStatus="status">
									<li id="id-li-page-${status.count}"><a href="<%=request.getContextPath()%>/info-video-related/list?page=${status.count}">${status.count}</a></li>
								</c:forEach>
								<c:if test="${currentPage < pageCount}">
									<li><a href="<%=request.getContextPath()%>/info-video-related/list?page=${currentPage + 1}"><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/video/list.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>