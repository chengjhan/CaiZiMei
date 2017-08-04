<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>診所一覽 | 采姿美管理系統</title>
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
				<h2 class="sub-header">診所一覽</h2>
				<div class="table-responsive">
					<a href="<%=request.getContextPath()%>/clinic/add">新增</a>

					<div>
						<label for="id-input-switch">顯示開啟的診所</label>
						<input type="checkbox" id="id-input-switch" />
					</div>
					
					<!-- table -->
					<table class="table table-bordered" style="width:100%;font-size:12px">
						<thead>
							<tr>
								<td>編號</td>
<!-- 								<td>流水號</td> -->
								<td>名稱</td>
								<td>英文名稱</td>
								<td>電話</td>
<!-- 								<td>國家</td> -->
<!-- 								<td>區域</td> -->
								<td>城市</td>
								<td>地址</td>
<!-- 								<td>緯度</td> -->
<!-- 								<td>經度</td> -->
								<td>網址</td>
<!-- 								<td>新增時間</td> -->
<!-- 								<td>更新時間</td> -->
<!-- 								<td>狀態更新時間</td> -->
								<td>編輯</td>
								<td>開啟</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="bean" items="${baseList}" varStatus="status">
								<fmt:formatDate value="${bean.ba_insert_time}" var="ba_insert_time_format" pattern="yyyy-MM-dd HH:mm:ss" />
								<fmt:formatDate value="${bean.ba_update_time}" var="ba_update_time_format" pattern="yyyy-MM-dd HH:mm:ss" />
								<fmt:formatDate value="${bean.ba_status_time}" var="ba_status_time_format" pattern="yyyy-MM-dd HH:mm:ss" />
								<tr>
									<td>${status.count}</td>
<%-- 									<td>${bean.ba_id}</td> --%>
									<td>${bean.ba_name}</td>
									<td>${bean.ba_eng_name}</td>
									<td>${bean.ba_localphone}</td>
<%-- 									<td>${bean.ba_CountryBean.co_name}</td> --%>
<%-- 									<td>${bean.ba_StateBean.st_name}</td> --%>
									<td>${bean.ba_CityBean.ci_name}</td>
									<td>${bean.ba_address}</td>
<%-- 									<td>${bean.ba_latitude}</td> --%>
<%-- 									<td>${bean.ba_longitude}</td> --%>
									<td><a href="${bean.ba_url}">${bean.ba_url}</a></td>
<%-- 									<td>${ba_insert_time_format}</td> --%>
<%-- 									<td>${ba_update_time_format}</td> --%>
<%-- 									<td>${ba_status_time_format}</td> --%>
									<td>
										<div style="width:30px">
											<a href="<%=request.getContextPath()%>/clinic/edit?ba_id=${bean.ba_id}"><img src="<%=request.getContextPath()%>/images/edit.svg" style="width:100%"></a>
										</div>
									</td>
									<td>
										<div class="ba-status-switch" data-ba-id="${bean.ba_id}" style="width:30px;cursor:pointer">
											<c:choose>
												<c:when test="${bean.ba_status eq 1}">
													<img src="<%=request.getContextPath()%>/images/true.svg" data-ba-status="1" style="width:100%">
												</c:when>
												<c:when test="${bean.ba_status eq 0}">
													<img src="<%=request.getContextPath()%>/images/false.svg" data-ba-status="0" style="width:100%">
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
			</div>
			<!-- content end -->
			
		</div>
	</div>
	
	<!-- load -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/base/list.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/js/clinic/list.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>