<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編輯采姿美介紹頁面 - 采姿美管理系統</title>
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
				<h2 class="sub-header">編輯采姿美介紹頁面</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath()%>/index">首頁</a></li>
						<li><a href="<%=request.getContextPath()%>/about-introduction/list?page=${currentPage}">采姿美介紹頁面一覽</a></li>
						<li class="active">編輯采姿美介紹頁面</li>
					</ol>
				
					<!-- form -->
					<form:form action="/Admin/about-introduction/edit.do" method="post" modelAttribute="htmlBean">
						<table class="table html-form-table">
							<tr style="display:none">
								<td>流水號</td>
								<td><form:hidden path="ht_id" /></td>
							</tr>
							<tr>
								<td><form:label path="ht_name">名稱</form:label></td>
								<td><form:input path="ht_name" cssClass="form-control" /></td>
								<td><form:errors path="ht_name" cssClass="error" /></td>
							</tr>
							<tr>
								<td><form:label path="ht_rank">排序</form:label></td>
								<td><form:input path="ht_rank" cssClass="form-control" /></td>
								<td><form:errors path="ht_rank" cssClass="error" /></td>
							</tr>
							<tr>
								<td><form:label path="ht_tag">標籤</form:label></td>
								<td><form:textarea path="ht_tag" cssClass="form-control" rows="10" /></td>
								<td><form:errors path="ht_tag" cssClass="error" /></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<input type="submit" class="btn btn-success" value="變更" />
									<a href="<%=request.getContextPath()%>/about-introduction/list?page=${currentPage}"><input type="button" class="btn btn-danger" value="取消"></a>
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
	<script src="<%=request.getContextPath()%>/js/html/form.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>