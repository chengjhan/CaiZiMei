<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>加盟店一覽 - 采姿美管理系統</title>
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
				<h2 class="sub-header">加盟店一覽</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath()%>/index">首頁</a></li>
						<li class="active">加盟店一覽</li>
					</ol>
					
					<!-- option -->
					<jsp:include page="../option.jsp" />
					
					<!-- table -->
					<table class="table table-hover base-list-table">
						<thead>
							<tr class="active">
								<td>編號</td>
								<td>名稱</td>
								<td>英文名稱</td>
								<td>電話</td>
								<td>地址</td>
								<td>網址</td>
								<td>編輯</td>
								<td>開啟</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="bean" items="${baseList}" varStatus="status">
								<tr>
									<td>${status.count + pageRowCount * (currentPage - 1)}</td>
									<td>${bean.ba_name}</td>
									<td>${bean.ba_eng_name}</td>
									<td>${bean.ba_tel_code}-${bean.ba_tel}</td>
									<td>${bean.ba_CityBean.ci_name}${bean.ba_address}</td>
									<td><a href="${bean.ba_url}">${bean.ba_url}</a></td>
									<td>
										<div class="edit-button">
											<a href="<%=request.getContextPath()%>/base-franchisee/edit?ba_id=${bean.ba_id}&page=${currentPage}" title="編輯">
												<img src="<%=request.getContextPath()%>/images/icon_edit.svg">
											</a>
										</div>
									</td>
									<td>
										<div class="ba-status-switch" data-ba-id="${bean.ba_id}" title="切換">
											<c:choose>
												<c:when test="${bean.ba_status eq 1}">
													<img src="<%=request.getContextPath()%>/images/icon_true.svg" data-ba-status="1">
												</c:when>
												<c:when test="${bean.ba_status eq 0}">
													<img src="<%=request.getContextPath()%>/images/icon_false.svg" data-ba-status="0">
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
	<script src="<%=request.getContextPath()%>/js/list.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>