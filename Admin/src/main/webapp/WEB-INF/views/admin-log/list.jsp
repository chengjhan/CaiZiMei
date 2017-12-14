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
							<div>
								<fmt:formatDate value="${beginDate}" var="begin" pattern="yyyy-MM-dd" />
								<label for="begin">開始日期</label>
								<input type="text" id="begin" class="form-control" name="begin" value="${begin}" placeholder="yyyy-MM-dd" />
							</div>
							<div>
								<fmt:formatDate value="${endDate}" var="end" pattern="yyyy-MM-dd" />
								<label for="end">結束日期</label>
								<input type="text" id="end" class="form-control" name="end" value="${end}" placeholder="yyyy-MM-dd" />
							</div>
							<div>
								<form:select path="al_AdminBean" cssClass="form-control">
									<form:option value="0" label="請選擇帳號" />
									<form:options items="${adminList}" itemValue="ad_id" itemLabel="ad_username" />
								</form:select>
							</div>
							<div>
								<form:select path="al_AdminPathBean" cssClass="form-control">
									<form:option value="0" label="請選擇動作" />
									<form:options items="${adminPathList}" itemValue="ap_id" itemLabel="ap_name" />
								</form:select>
							</div>
							<div style="display:none">
								<input type="hidden" name="page" value="1" />
							</div>
							<div>
								<input type="submit" class="btn btn-success" value="搜尋" />
							</div>
						</form:form>
					</div>
					<!-- option end -->
					
					<!-- table -->
					<table class="table table-hover">
						<thead>
							<tr class="active">
								<td width="75px">編號</td>
								<td width="200px">時間</td>
								<td>帳號</td>
								<td>動作</td>
								<td width="150px">IP</td>
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
					<c:if test="${pageCount > 1}">
						<nav class="page">
							<ul class="pagination">
								<c:choose>
									<c:when test="${currentGroup eq 1}">
										<li class="disabled"><a><span>&laquo;&laquo;</span></a></li>
									</c:when>
									<c:when test="${currentGroup > 1}">
										<li><a href="<%=request.getContextPath()%>/${ca_directory}/list.do?begin=${begin}&end=${end}&al_AdminBean=${al_AdminBean}&al_AdminPathBean=${al_AdminPathBean}&page=${(currentGroup - 2)*groupRowCount + 1}"><span>&laquo;&laquo;</span></a></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${currentPage eq 1}">
										<li class="disabled"><a><span>&laquo;</span></a></li>
									</c:when>
									<c:when test="${currentPage > 1}">
										<li><a href="<%=request.getContextPath()%>/${ca_directory}/list.do?begin=${begin}&end=${end}&al_AdminBean=${al_AdminBean}&al_AdminPathBean=${al_AdminPathBean}&page=${currentPage - 1}"><span>&laquo;</span></a></li>
									</c:when>
								</c:choose>
								<c:forEach begin="${currentGroupBegin}" end="${currentGroupEnd}" varStatus="status">
									<li id="id-li-page-${status.count + (currentGroup - 1)*groupRowCount}"><a href="<%=request.getContextPath()%>/${ca_directory}/list.do?begin=${begin}&end=${end}&al_AdminBean=${al_AdminBean}&al_AdminPathBean=${al_AdminPathBean}&page=${status.count + (currentGroup - 1)*groupRowCount}">${status.count + (currentGroup - 1)*groupRowCount}</a></li>
								</c:forEach>
								<c:choose>
									<c:when test="${currentPage < pageCount}">
										<li><a href="<%=request.getContextPath()%>/${ca_directory}/list.do?begin=${begin}&end=${end}&al_AdminBean=${al_AdminBean}&al_AdminPathBean=${al_AdminPathBean}&page=${currentPage + 1}"><span>&raquo;</span></a></li>
									</c:when>
									<c:when test="${currentPage eq pageCount}">
										<li class="disabled"><a><span>&raquo;</span></a></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${currentGroup < groupCount}">
										<li><a href="<%=request.getContextPath()%>/${ca_directory}/list.do?begin=${begin}&end=${end}&al_AdminBean=${al_AdminBean}&al_AdminPathBean=${al_AdminPathBean}&page=${currentGroup*groupRowCount + 1}"><span>&raquo;</span></a></li>
									</c:when>
									<c:when test="${currentGroup eq groupCount}">
										<li class="disabled"><a><span>&raquo;&raquo;</span></a></li>
									</c:when>
								</c:choose>
							</ul>
						</nav>
					</c:if>
					
				</div>
				<!-- content end -->
				
			</div>
			<!-- main end -->
			
		</div>
	</div>
	<!-- container end -->
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/dashboard.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/js/pagination.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>