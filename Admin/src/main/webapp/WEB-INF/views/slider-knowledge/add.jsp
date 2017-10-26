<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增醫療新知輪播圖片 - 采姿美管理系統</title>
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
				<h2 class="sub-header">新增醫療新知輪播圖片</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath()%>/index">首頁</a></li>
						<li><a href="<%=request.getContextPath()%>/slider-knowledge/list?page=1">醫療新知輪播圖片一覽</a></li>
						<li class="active">新增醫療新知輪播圖片</li>
					</ol>
				
					<!-- form -->
					<form:form action="/Admin/slider-knowledge/add.do" method="post" modelAttribute="imageBean" enctype="multipart/form-data">
						
						<div class="image-upload">
							<label for="image-uploader">
								<div class="image-preview" style="max-width:533px">
									<img src="<%=request.getContextPath()%>/images/image/upload_new_image_533x300.png" class="img-thumbnail">
								</div>
							</label>
							<input type="file" id="image-uploader" name="file" style="display:none" />
						</div>
						<p style="color:red">※ 請上傳長寬比為 16:9 的圖片 (建議為 640px*360px)。</p>
						
						<table class="table image-table">
							<tr>
								<td><form:label path="im_name">名稱</form:label></td>
								<td><form:input path="im_name" cssClass="form-control" /></td>
								<td><form:errors path="im_name" cssClass="error" /></td>
							</tr>
							<tr>
								<td><form:label path="im_url">連結</form:label></td>
								<td><form:input path="im_url" cssClass="form-control" /></td>
								<td><form:errors path="im_url" cssClass="error" /></td>
							</tr>
							<tr>
								<td><form:label path="im_rank">排序</form:label></td>
								<td><form:input path="im_rank" cssClass="form-control" /></td>
								<td><form:errors path="im_rank" cssClass="error" /></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<input type="submit" class="btn btn-success" value="新增" />
									<a href="<%=request.getContextPath()%>/slider-knowledge/list?page=1"><button type="button" class="btn btn-danger">取消</button></a>
									<div id="image-reset" style="display:none"><input type="button" class="btn btn-warning" value="重設圖片" /></div>
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
	
	<script>
		// 清除上傳圖片
		$(document).on("click", "#image-reset", function(){
			$("#image-uploader").val("");
			$(".image-preview img").attr("src", "../images/image/upload_new_image_533x300.png");
			$("#image-reset").attr("style", "display:none");
		});
	</script>
	
	<!-- load -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/image/form.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>