<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增國家 - 采姿美管理系統</title>
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
				<h2 class="sub-header">新增國家</h2>
				
				<!-- content -->
				<div class="table-responsive">
					
					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath()%>/index">首頁</a></li>
						<li><a href="<%=request.getContextPath()%>/area-country/list">國家一覽</a></li>
						<li class="active">新增國家</li>
					</ol>
				
					<!-- form -->
					<form:form action="/Admin/area-country/add.do" method="post" modelAttribute="countryBean">
						<table class="table area-table">
							<tr>
								<td><form:label path="co_iso">代碼</form:label></td>
								<td><form:input path="co_iso" cssClass="form-control" /></td>
								<td><form:errors path="co_iso" cssClass="error" /></td>
							</tr>
							<tr>
								<td><form:label path="co_name">名稱</form:label></td>
								<td><form:input path="co_name" cssClass="form-control" /></td>
								<td><form:errors path="co_name" cssClass="error" /></td>
							</tr>
							<tr>
								<td><form:label path="co_phonecode">電話碼</form:label></td>
								<td><form:input path="co_phonecode" cssClass="form-control" /></td>
								<td><form:errors path="co_phonecode" cssClass="error" /></td>
							</tr>
							<tr>
								<td><form:label path="co_rank">排序</form:label></td>
								<td><form:input path="co_rank" cssClass="form-control" /></td>
								<td><form:errors path="co_rank" cssClass="error" /></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<input type="submit" class="btn btn-success" value="新增" />
									<a href="<%=request.getContextPath()%>/area-country/list"><button type="button" class="btn btn-danger">取消</button></a>
								</td>
							</tr>
						</table>
					</form:form>
					<!-- form end -->
	
				</div>
				<!-- content end -->
				
			</div>
			<!-- main end -->
						
		</div>
	</div>
	<!-- container end -->
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/validation/jquery.validate.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/area-country/form.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>