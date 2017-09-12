<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>診所一覽 | 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/shortcut_icon_black.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/base/list.css" type="text/css" />
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
				<h2 class="sub-header">診所一覽</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- option -->
					<div class="btn-group option">
						<div class="add-button">
							<a href="<%=request.getContextPath()%>/clinic/add" title="新增">
								<img src="<%=request.getContextPath()%>/images/icon_add.svg">
							</a>
						</div>
					</div>
					<!-- option end -->
					
					<!-- table -->
					<table class="table table-hover">
						<thead>
							<tr class="active">
								<td style="width:50px">編號</td>
<!-- 								<td>流水號</td> -->
								<td>名稱</td>
								<td>英文名稱</td>
								<td style="width:100px">電話</td>
<!-- 								<td>國家</td> -->
<!-- 								<td>區域</td> -->
								<td>地址</td>
<!-- 								<td>緯度</td> -->
<!-- 								<td>經度</td> -->
								<td>網址</td>
<!-- 								<td>新增時間</td> -->
<!-- 								<td>更新時間</td> -->
<!-- 								<td>狀態更新時間</td> -->
								<td style="width:50px">編輯</td>
								<td style="width:50px">開啟</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="bean" items="${baseList}" varStatus="status">
<%-- 								<fmt:formatDate value="${bean.ba_insert_time}" var="ba_insert_time_format" pattern="yyyy-MM-dd HH:mm:ss" /> --%>
<%-- 								<fmt:formatDate value="${bean.ba_update_time}" var="ba_update_time_format" pattern="yyyy-MM-dd HH:mm:ss" /> --%>
<%-- 								<fmt:formatDate value="${bean.ba_status_time}" var="ba_status_time_format" pattern="yyyy-MM-dd HH:mm:ss" /> --%>
								<tr>
									<td>${status.count + pageRowCount * (currentPage - 1)}</td>
<%-- 									<td>${bean.ba_id}</td> --%>
									<td>${bean.ba_name}</td>
									<td>${bean.ba_eng_name}</td>
									<td>${bean.ba_tel_code}-${bean.ba_tel}</td>
<%-- 									<td>${bean.ba_CountryBean.co_name}</td> --%>
<%-- 									<td>${bean.ba_StateBean.st_name}</td> --%>
									<td>${bean.ba_CityBean.ci_name}${bean.ba_address}</td>
<%-- 									<td>${bean.ba_latitude}</td> --%>
<%-- 									<td>${bean.ba_longitude}</td> --%>
									<td><a href="${bean.ba_url}">${bean.ba_url}</a></td>
<%-- 									<td>${ba_insert_time_format}</td> --%>
<%-- 									<td>${ba_update_time_format}</td> --%>
<%-- 									<td>${ba_status_time_format}</td> --%>
									<td>
										<div class="edit-button">
											<a href="<%=request.getContextPath()%>/clinic/edit?ba_id=${bean.ba_id}&page=${currentPage}" title="編輯">
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
					<c:if test="${pageCount > 1}">
						<nav style="text-align:center">
							<ul class="pagination" style="margin:0">
								<c:if test="${currentPage > 1}">
									<li><a href="<%=request.getContextPath()%>/clinic/list?page=${currentPage - 1}"><span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>
								</c:if>
								<c:forEach begin="1" end="${pageCount}" varStatus="status">
									<li id="id-li-page-${status.count}"><a href="<%=request.getContextPath()%>/clinic/list?page=${status.count}">${status.count}</a></li>
								</c:forEach>
								<c:if test="${currentPage < pageCount}">
									<li><a href="<%=request.getContextPath()%>/clinic/list?page=${currentPage + 1}"><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>
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
	<script src="<%=request.getContextPath()%>/js/base/list.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>