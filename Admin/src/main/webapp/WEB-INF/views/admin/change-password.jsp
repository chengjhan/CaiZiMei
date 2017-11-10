<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>變更密碼 - 采姿美管理系統</title>
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
				<h2 class="sub-header">變更密碼</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- form -->
					<form action="<c:url value='/admin/change-password.do' />" method="post" class="form-horizontal">
						<table class="table admin-form-table">
							<tr>
								<td><label for="ad_password_old">舊密碼</label></td>
								<td>
									<input type="password" id="ad_password_old" class="form-control" name="ad_password_old" value="${ad_password_old}">
									<c:if test="${not empty ad_password_old_error}">
										<label for="ad_password_old" id="ad_password_old-error" class="error"><span>${ad_password_old_error}</span></label>
									</c:if>
								</td>
								<td></td>
							</tr>
							<tr>
								<td><label for="ad_password_new">新密碼</label></td>
								<td>
									<input type="password" id="ad_password_new" class="form-control" name="ad_password_new" value="${ad_password_new}" placeholder="必須為 8 位以上並包含英文及數字">
									<c:if test="${not empty ad_password_new_error}">
										<label for="ad_password_new" id="ad_password_new-error" class="error"><span>${ad_password_new_error}</span></label>
									</c:if>
								</td>
								<td></td>
							</tr>
							<tr>
								<td><label for="ad_password_new_again">確認密碼</label></td>
								<td><input type="password" id="ad_password_new_again" class="form-control" name="ad_password_new_again" value="${ad_password_new_again}"></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" class="btn btn-success" value="變更" /></td>
								<td></td>
							</tr>
						</table>
					</form>
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
	<script src="<%=request.getContextPath()%>/js/admin/form.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>