<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編輯相關影音 - 采姿美管理系統</title>
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
				<h2 class="sub-header">編輯相關影音</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath()%>/index">首頁</a></li>
						<li><a href="<%=request.getContextPath()%>/info-video-related/list?page=${currentPage}">相關影音頁面一覽</a></li>
						<li class="active">編輯相關影音</li>
					</ol>
				
					<!-- form -->
					<form:form action="/Admin/info-video-related/edit.do" method="post" modelAttribute="videoBean">
						<table class="table html-table">
							<tr style="display:none">
								<td>流水號</td>
								<td><form:hidden path="vi_id" /></td>
							</tr>
							<tr>
								<td><form:label path="vi_name">名稱</form:label></td>
								<td><form:input path="vi_name" cssClass="form-control" /></td>
								<td><form:errors path="vi_name" cssClass="error" /></td>
							</tr>
							<tr>
								<td><form:label path="vi_tag">標籤</form:label></td>
								<td>
									<form:textarea path="vi_tag" cssClass="form-control" rows="6" />
									<p style="color:red">※ 請至 Youtube 網站複製嵌入標籤。</p>
								</td>
								<td><form:errors path="vi_tag" cssClass="error" /></td>
							</tr>
							<tr>
								<td><form:label path="vi_rank">排序</form:label></td>
								<td><form:input path="vi_rank" cssClass="form-control" /></td>
								<td><form:errors path="vi_rank" cssClass="error" /></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<input type="submit" class="btn btn-success" value="變更" />
									<a href="<%=request.getContextPath()%>/info-video-related/list?page=${currentPage}"><button type="button" class="btn btn-danger">取消</button></a>
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/video/form.js"></script>
</body>
</html>