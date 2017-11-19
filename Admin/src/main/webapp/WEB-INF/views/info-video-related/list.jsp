<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>相關影音頁面一覽 - 采姿美管理系統</title>
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
						<span style="color:red">※ 排序前 6 個將顯示於「相關資訊/相關影音」頁面中。</span>
						<div class="add-button">
							<a href="<%=request.getContextPath()%>/${ca_directory}/add" title="新增">
								<img src="<%=request.getContextPath()%>/images/icon_add.svg" />
							</a>
						</div>
					</div>
					
					<!-- table -->
					<table class="table table-bordered video-list-table">
						<thead>
							<tr>
								<td>編號</td>
								<td>名稱</td>
								<td>影片</td>
								<td>排序</td>
								<td>編輯</td>
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
												<img src="<%=request.getContextPath()%>/images/icon_edit.svg" />
											</a>
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
	<script src="<%=request.getContextPath()%>/js/dashboard.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>