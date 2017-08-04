<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主輪播圖片一覽 | 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_black_32x32.ico" type="image/x-icon" />
</head>
<body>
	<!-- header -->
	<jsp:include page="../header.jsp" />
	
	<div class="container-fluid">
		<div class="row">
		
			<!-- menu -->
			<jsp:include page="../menu.jsp" />
			
			<!-- content -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h2 class="sub-header">主輪播圖片一覽</h2>
				<div class="table-responsive">
					<a href="<%=request.getContextPath()%>/slider-main/add">新增</a>
				
					<!-- table -->
					<table class="table table-bordered" style="width:100%">
						<c:forEach var="bean" items="${sliderMainList}" varStatus="status">
							<tbody>
								<tr>
									<td rowspan="4">${status.count}</td>
									<td rowspan="4" style="width:400px"><img style="width:100%" src="<%=request.getContextPath()%>/images/slider-main/${bean.sm_filename}"></td>
									<td>名稱</td>
									<td style="width:400px">${bean.sm_name}</td>
									<td rowspan="4">
										<div style="width:30px">
											<a href="<%=request.getContextPath()%>/slider-main/edit?sm_id=${bean.sm_id}"><img src="<%=request.getContextPath()%>/images/edit.svg" style="width:100%"></a>
										</div>
									</td>	
									<td rowspan="4">
										<div class="sm-status-switch" data-sm-id="${bean.sm_id}" style="width:30px;cursor:pointer">
											<c:choose>
												<c:when test="${bean.sm_status eq 1}">
													<img src="<%=request.getContextPath()%>/images/true.svg" data-sm-status="1" style="width:100%">
												</c:when>
												<c:when test="${bean.sm_status eq 0}">
													<img src="<%=request.getContextPath()%>/images/false.svg" data-sm-status="0" style="width:100%">
												</c:when>
											</c:choose>
										</div>
									</td>
								</tr>
								<tr>
									<td>檔名</td>
									<td>${bean.sm_filename}</td>
								</tr>
								<tr>
									<td>連結</td>
									<td><a href="${bean.sm_url}">${bean.sm_url}</a></td>
								</tr>
								<tr>
									<td>排序</td>
									<td>${bean.sm_rank}</td>
								</tr>
<!-- 								<tr> -->
<!-- 									<td>最後更新時間</td> -->
<%-- 									<td>${bean.sm_update_time}</td> --%>
<!-- 								</tr> -->
							</tbody>
						</c:forEach>
					</table>
					<!-- table end -->
	
				</div>
			</div>
			<!-- content end -->
			
		</div>
	</div>
	
	<!-- load -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/slider-main/list.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>