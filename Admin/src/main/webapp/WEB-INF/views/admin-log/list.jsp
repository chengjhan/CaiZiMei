<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理員日誌一覽 - 采姿美管理系統</title>
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
				<h2 class="sub-header">管理員日誌一覽</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath()%>/index">首頁</a></li>
						<li class="active">管理員日誌一覽</li>
					</ol>
					
					<!-- option -->
					<div class="btn-group option">
						<form:form action="/Admin/admin-log/list.do" method="get" modelAttribute="adminLogBean">
							<label for="start">開始日期</label>
							<input type="text" id="start" class="form-control" name="start" value="${start}" />
							<label for="end">結束日期</label>
							<input type="text" id="end" class="form-control" name="end" value="${end}" />
							<form:select path="al_AdminBean" cssClass="form-control">
								<form:option value="0" label="請選擇帳號" />
								<form:options items="${adminList}" itemValue="ad_id" itemLabel="ad_username" />
							</form:select>
							<form:select path="al_AdminPathBean" cssClass="form-control">
								<form:option value="0" label="請選擇動作" />
								<form:options items="${adminPathList}" itemValue="ap_id" itemLabel="ap_name" />
							</form:select>
							<div style="display:none">
								<input type="hidden" name="page" value="1" />
							</div>
							<input type="submit" class="btn btn-success" value="搜尋" />
						</form:form>
					</div>
					<!-- option end -->
					
					<!-- table -->
					<table class="table table-hover">
						<thead>
							<tr class="active">
								<td width="50px">編號</td>
								<td width="150px">時間</td>
								<td>帳號</td>
								<td>動作</td>
								<td>IP</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="bean" items="${adminLogList}" varStatus="status">
								<fmt:formatDate value="${bean.al_insert_time}" var="al_insert_time" pattern="yyyy-MM-dd HH:mm:ss" />
								<tr>
									<td>${status.count}</td>
									<td>${al_insert_time}</td>
									<td>${bean.al_AdminBean.ad_username}</td>
									<td>${bean.al_AdminPathBean.ap_name}</td>
									<td>${bean.al_ip}</td>
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