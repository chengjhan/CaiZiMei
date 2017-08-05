<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>變更密碼 | 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_gray_32x32.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/all.css" type="text/css" />
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
				<h2 class="sub-header">變更密碼</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- form -->
					<form action="<c:url value='/admin/change-password.do' />" method="post" class="form-horizontal change-password-form">
						<div class="form-group">
							<label for="id-input-ad-password" class="col-sm-2 control-label">舊密碼</label>
							<div class="col-sm-6">
								<input type="password" id="id-input-ad-password-old" class="form-control" name="ad_password_old">
							</div>
						</div>
						<div class="form-group">
							<label for="id-input-ad-password-new" class="col-sm-2 control-label">新密碼</label>
							<div class="col-sm-6">
								<input type="password" id="id-input-ad-password-new" class="form-control" name="ad_password_new">
							</div>
						</div>
						<div class="form-group">
							<label for="id-input-ad-password-new-again" class="col-sm-2 control-label">新密碼</label>
							<div class="col-sm-6">
								<input type="password" id="id-input-ad-password-new-again" class="form-control" name="ad_password_new_again">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<input type="submit" id="id-input-submit" class="btn btn-success" value="變更">
								<a href="<%=request.getContextPath()%>/"><button type="button" class="btn btn-danger">取消</button></a>
							</div>
						</div>
						<div class="error">${error}</div>
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</body>
</html>