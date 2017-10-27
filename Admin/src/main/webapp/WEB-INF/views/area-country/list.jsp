<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>國家一覽 - 采姿美管理系統</title>
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
				<h2 class="sub-header">國家一覽</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath()%>/index">首頁</a></li>
						<li class="active">國家一覽</li>
					</ol>

					<!-- option -->
					<div class="btn-group option" style="width:500px;overflow:auto">
						<div class="add-button">
							<a href="<%=request.getContextPath()%>/area-country/add" title="新增">
								<img src="<%=request.getContextPath()%>/images/icon_add.svg">
							</a>
						</div>
					</div>
					<!-- option end -->
				
					<!-- table -->
					<table class="table table-striped table-hover" style="width:500px">
						<thead>
							<tr class="active">
								<td style="width:50px">編號</td>
								<td style="width:50px">代碼</td>
								<td>名稱</td>
								<td style="width:75px">電話碼</td>
								<td style="width:50px">排序</td>
								<td style="width:50px">編輯</td>
								<td style="width:50px">開啟</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="bean" items="${countryList}" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td>${bean.co_iso}</td>
									<td>${bean.co_name}</td>
									<td>${bean.co_phonecode}</td>
									<td>${bean.co_rank}</td>
									<td>
										<div class="edit-button">
											<a href="<%=request.getContextPath()%>/area-country/edit?co_id=${bean.co_id}" title="編輯">
												<img src="<%=request.getContextPath()%>/images/icon_edit.svg">
											</a>
										</div>
									</td>
									<td>
										<div class="co-status-switch" data-co-id="${bean.co_id}" title="切換">
											<c:choose>
												<c:when test="${bean.co_status eq 1}">
													<img src="<%=request.getContextPath()%>/images/icon_true.svg" data-co-status="1">
												</c:when>
												<c:when test="${bean.co_status eq 0}">
													<img src="<%=request.getContextPath()%>/images/icon_false.svg" data-co-status="0">
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
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/area-country/list.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>