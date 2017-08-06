<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>區域一覽 | 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_black_32x32.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/region/list.css" type="text/css" />
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
				<h2 class="sub-header">區域一覽</h2>
				
				<!-- content -->
				<div class="table-responsive">
					
					<!-- option -->
					<div class="btn-group option">
						<div style="float:left">
							<form:form modelAttribute="stateBean">
								<form:select id="id-input-st-co-id" path="st_CountryBean" cssClass="btn btn-default btn-sm">
									<form:option value="0" label="請選擇國家" />
									<form:options items="${countryList}" itemValue="co_id" itemLabel="co_name" />
								</form:select>
							</form:form>
						</div>
						<div class="add-button">
							<a href="<%=request.getContextPath()%>/state/add">
								<img src="<%=request.getContextPath()%>/images/add.svg">
							</a>
						</div>
					</div>
					<!-- option end -->
					
					<!-- table -->
					<table class="table table-bordered" style="width:500px">
						<thead>
							<tr>
								<td style="width:50px">編號</td>
<!-- 								<td>流水號</td> -->
								<td>名稱</td>
								<td style="width:75px">排序</td>
								<td style="width:50px">編輯</td>
								<td style="width:50px">刪除</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="bean" items="${stateList}" varStatus="status">
								<tr>
									<td>${status.count}</td>
<%-- 									<td>${bean.st_id}</td> --%>
									<td>${bean.st_name}</td>
									<td>${bean.st_rank}</td>
									<td>
										<div class="edit-button">
											<a href="<%=request.getContextPath()%>/state/edit?st_id=${bean.st_id}">
												<img src="<%=request.getContextPath()%>/images/edit.svg">
											</a>
										</div>
									</td>
									<td>
										<div class="delete-button">
											<a href="<%=request.getContextPath()%>/state/delete?st_id=${bean.st_id}">
												<img src="<%=request.getContextPath()%>/images/delete.svg">
											</a>
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/state/list.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>