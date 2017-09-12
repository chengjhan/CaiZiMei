<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編輯優惠活動輪播圖片資訊 | 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/shortcut_icon_black.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/image/form.css" type="text/css" />
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
				<h2 class="sub-header">編輯優惠活動輪播圖片資訊</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- form -->
					<form:form action="/Admin/slider-sale/edit.do" method="post" modelAttribute="imageBean" enctype="multipart/form-data" cssClass="form-horizontal">
						
						<div class="image-upload">
							<label for="image-uploader">
								<div class="image-preview" style="max-width:400px">
									<img src="<%=request.getContextPath()%>/images/slider-sale/${imageBean.im_filename}" class="img-thumbnail">
								</div>
							</label>
							<div id="image-reset">
								<img src="<%=request.getContextPath()%>/images/icon_false.svg" style="display:none">
							</div>
						</div>
						<p style="color:red">※ 請上傳長寬比為 4:3 的圖片 (建議為 640px*480px)。</p>
						<input type="file" id="image-uploader" name="file" style="display:none" />
						
						<table class="table">
							<tr style="display:none">
								<td>流水號</td>
								<td><form:hidden path="im_id" /></td>
							</tr>
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
								<td><input type="submit" class="btn btn-success" value="變更" /></td>
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
			$(".image-preview img").attr("src", "../images/slider-sale/${imageBean.im_filename}");
			$("#image-reset img").attr("style", "display:none");
		});
	</script>
	
	<!-- load -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/image/form.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>