<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編輯個人資訊 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/shortcut_icon_black.ico" type="image/x-icon" />
</head>
<body>
	<!-- header -->
	<jsp:include page="../header.jsp" />
	
	<!-- container -->
	<div class="container-fluid">
		<div class="row">
		
			<!-- menu-personal -->
			<jsp:include page="../menu-personal.jsp" />
			
			<!-- main -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			
				<!-- title -->
				<h2 class="sub-header">編輯個人資訊</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- form -->
					<form:form action="/Admin/admin/edit.do" method="post" modelAttribute="admin" cssClass="form-horizontal">
						<table class="table admin-form-table">
							<tr style="display:none">
								<td>流水號</td>
								<td><form:hidden path="ad_id" /></td>
							</tr>
							<tr>
								<td><form:label path="ad_lastname">姓氏</form:label></td>
								<td><form:input path="ad_lastname" cssClass="form-control" /></td>
								<td><form:errors path="ad_lastname" cssClass="error" /></td>
							</tr>
							<tr>
								<td><form:label path="ad_firstname">名字</form:label></td>
								<td><form:input path="ad_firstname" cssClass="form-control" /></td>
								<td><form:errors path="ad_firstname" cssClass="error" /></td>
							</tr>
							<tr>
								<td><form:label path="ad_email">信箱</form:label></td>
								<td><form:input path="ad_email" cssClass="form-control" /></td>
								<td><form:errors path="ad_email" cssClass="error" /></td>
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
		// validation
		$(document).ready(function(){
			$("form").validate({
				rules: {
					ad_lastname: {
						maxlength: 20
					},
					ad_firstname: {
						maxlength: 20
					},
					ad_email: {
						required: true,
						email: true,
						maxlength: 50,
						remote: { // 信箱重複驗證 (edit) (AJAX)
							url: "../admin/edit-email-repeat.ajax", // 後台處理程序
							type: "post", // 數據發送方式
							dataType: "text", // 接受數據格式
							data: { // 要傳遞的數據
								ad_id: function(){
									return $("#ad_id").val();
								},
								ad_email: function(){
									return $("#ad_email").val();
								}
							}
						}
					}
				},
				messages: {
					ad_lastname: {
						maxlength: "姓氏必須小於20個字"
					},
					ad_firstname: {
						maxlength: "名字必須小於20個字"
					},
					ad_email: {
						required: "這裡必須填入資料",
						email: "信箱必須填入正確的格式",
						maxlength: "信箱必須小於50個字"
					}
				},
				highlight: function(element){
					$(element).addClass("form-error");
				},
				unhighlight: function(element){
					$(element).removeClass("form-error");
				},
				submitHandler: function(form){
					form.submit();
			    }
			});
		});
	</script>
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/validation/jquery.validate.min.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>