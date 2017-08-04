<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>區域一覽 | 采姿美管理系統</title>
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
				<h2 class="sub-header">區域一覽</h2>
				<div class="table-responsive">
					<a href="<%=request.getContextPath()%>/state/add">新增</a>

					<form:form modelAttribute="stateBean">
						<form:label path="st_CountryBean">國家</form:label>
						<form:select id="id-input-st-co-id" path="st_CountryBean">
							<form:option value="0" label="請選擇國家" />
							<form:options items="${countryList}" itemValue="co_id" itemLabel="co_name" />
						</form:select>
					</form:form>
					
					<!-- table -->
					<table class="table table-bordered" style="width:500px">
						<thead>
							<tr>
								<td>編號</td>
<!-- 								<td>流水號</td> -->
								<td>名稱</td>
								<td>排序</td>
								<td>編輯</td>
								<td>刪除</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="bean" items="${stateList}" varStatus="status">
								<tr>
									<td>${status.count}</td>
<%-- 									<td>${bean.st_id}</td> --%>
									<td>${bean.st_name}</td>
									<td>${bean.st_rank}</td>
									<td><a href="<%=request.getContextPath()%>/state/edit?st_id=${bean.st_id}">編輯</a></td>
									<td><a href="<%=request.getContextPath()%>/state/delete?st_id=${bean.st_id}">刪除</a></td>
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
	<script src="<%=request.getContextPath()%>/js/state/list.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>