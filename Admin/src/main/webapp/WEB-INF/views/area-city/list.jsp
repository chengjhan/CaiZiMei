<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>城市一覽 - 采姿美管理系統</title>
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
				<h2 class="sub-header">城市一覽</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath()%>/index">首頁</a></li>
						<li class="active">城市一覽</li>
					</ol>
				
					<!-- option -->
					<div class="btn-group option" style="width:500px">
						<form:form modelAttribute="cityBean">
							<form:select path="ci_CountryBean" cssClass="form-control">
								<form:option value="0" label="請選擇國家" />
								<form:options items="${countryList}" itemValue="co_id" itemLabel="co_name" />
							</form:select>
							<form:select path="ci_StateBean" cssClass="form-control">
								<form:option value="0" label="請選擇區域" />
								<form:options items="${stateList}" itemValue="st_id" itemLabel="st_name" />
							</form:select>
						</form:form>
						<div class="add-button">
							<a href="<%=request.getContextPath()%>/area-city/add" title="新增">
								<img src="<%=request.getContextPath()%>/images/icon_add.svg" />
							</a>
						</div>
					</div>
					<!-- option end -->
					
					<!-- table -->
					<table class="table table-striped table-hover area-list-table">
						<thead>
							<tr class="active">
								<td>編號</td>
								<td>名稱</td>
								<td>排序</td>
								<td>編輯</td>
								<td>開啟</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="bean" items="${cityList}" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td>${bean.ci_name}</td>
									<td>${bean.ci_rank}</td>
									<td>
										<div class="edit-button">
											<a href="<%=request.getContextPath()%>/area-city/edit?ci_id=${bean.ci_id}" title="編輯">
												<img src="<%=request.getContextPath()%>/images/icon_edit.svg" />
											</a>
										</div>
									</td>
									<td>
										<div class="ci-status-switch" data-ci-id="${bean.ci_id}" title="切換">
											<c:choose>
												<c:when test="${bean.ci_status eq 1}">
													<img src="<%=request.getContextPath()%>/images/icon_true.svg" data-ci-status="1" />
												</c:when>
												<c:when test="${bean.ci_status eq 0}">
													<img src="<%=request.getContextPath()%>/images/icon_false.svg" data-ci-status="0" />
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
	<script src="<%=request.getContextPath()%>/js/dashboard.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>