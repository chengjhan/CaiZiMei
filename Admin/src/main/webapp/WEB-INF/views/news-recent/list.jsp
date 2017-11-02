<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>近期活動頁面一覽 - 采姿美管理系統</title>
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
				<h2 class="sub-header">近期活動頁面一覽</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath()%>/index">首頁</a></li>
						<li class="active">近期活動頁面一覽</li>
					</ol>
				
					<!-- option -->
					<jsp:include page="../option.jsp" />
					
					<!-- table -->
					<table class="table table-bordered">
						<thead>
							<tr>
								<td style="width:50px">編號</td>
								<td>名稱</td>
								<td style="width:50px">排序</td>
								<td style="width:50px">編輯</td>
								<td style="width:50px">開啟</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="bean" items="${htmlList}" varStatus="status">
								<tr>
									<td>${status.count + pageRowCount * (currentPage - 1)}</td>
									<td>${bean.ht_name}</td>
									<td>${bean.ht_rank}</td>
									<td>
										<div class="edit-button">
											<a href="<%=request.getContextPath()%>/news-recent/edit?ht_id=${bean.ht_id}&page=${currentPage}" title="編輯">
												<img src="<%=request.getContextPath()%>/images/icon_edit.svg">
											</a>
										</div>
									</td>
									<td>
										<div class="ht-status-switch" data-ht-id="${bean.ht_id}">
											<c:choose>
												<c:when test="${bean.ht_status eq 1}">
													<img src="<%=request.getContextPath()%>/images/icon_true.svg" data-ht-status="1">
												</c:when>
												<c:when test="${bean.ht_status eq 0}">
													<img src="<%=request.getContextPath()%>/images/icon_false.svg" data-ht-status="0" title="開啟">
												</c:when>
											</c:choose>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
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
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/html/list.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>