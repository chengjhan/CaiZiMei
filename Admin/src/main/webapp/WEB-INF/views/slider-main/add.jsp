<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增主輪播圖片 | 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_black_32x32.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/image/add.css" type="text/css" />
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
				<h2 class="sub-header">新增主輪播圖片</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<div class="image-preview">
						<img class="img-thumbnail">
					</div>
				
					<!-- form -->
					<form:form action="/Admin/slider-main/add.do" method="post" modelAttribute="imageBean" enctype="multipart/form-data">
						<table class="table">
							<tr>
								<td>名稱</td>
								<td><form:input path="im_name" cssClass="form-control" /></td>
								<td></td>
							</tr>
							<tr>
								<td>選擇</td>
								<td><input type="file" id="image-upload" class="form-control-file" name="file" /></td>
								<td></td>
							</tr>
							<tr>
								<td>連結</td>
								<td><form:input path="im_url" cssClass="form-control" /></td>
								<td></td>
							</tr>
							<tr>
								<td>排序</td>
								<td><form:input path="im_rank" cssClass="form-control" /></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" class="btn btn-success" value="新增" /></td>
<%-- 								<td><a href="<%=request.getContextPath()%>/slider-main/list"><button type="button" class="btn btn-danger">取消</button></a></td> --%>
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
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/cropit/jquery.cropit.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/js/image/add.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>