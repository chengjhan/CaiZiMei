<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>註冊 | 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_black_32x32.ico" type="image/x-icon" />
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
				<h2 class="sub-header">管理員註冊</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- form -->
					<form:form action="/Admin/admin/sign-up.do" method="post" modelAttribute="adminBean" cssClass="form-horizontal sign-up-form">
						<div class="form-group">
							<form:label path="ad_username" cssClass="col-sm-2 control-label">帳號</form:label>
							<div class="col-sm-6">
								<form:input path="ad_username" id="id-input-ad-username" cssClass="form-control" />
							</div>
							<form:errors path="ad_username" cssClass="error" />
							<span id="id-span-ad-username" class="error"></span>
						</div>
						<div class="form-group">
							<form:label path="ad_password" cssClass="col-sm-2 control-label">密碼</form:label>
							<div class="col-sm-6">
								<form:password path="ad_password" id="id-input-ad-password" cssClass="form-control" />
							</div>
							<form:errors path="ad_password" cssClass="error" />
						</div>
						<div class="form-group">
							<label for="id-input-ad-password-again" class="col-sm-2 control-label">確認密碼</label>
							<div class="col-sm-6">	
								<input type="password" id="id-input-ad-password-again" class="form-control" name="ad_password_again">
							</div>
						</div>
						<div class="form-group">
							<form:label path="ad_lastname" cssClass="col-sm-2 control-label">姓氏</form:label>
							<div class="col-sm-6">
								<form:input path="ad_lastname" id="id-input-ad-lastname" cssClass="form-control" />
							</div>
							<form:errors path="ad_lastname" cssClass="error" />
						</div>
						<div class="form-group">
							<form:label path="ad_firstname" cssClass="col-sm-2 control-label">名字</form:label>
							<div class="col-sm-6">
								<form:input path="ad_firstname" id="id-input-ad-firstname" cssClass="form-control" />
							</div>	
							<form:errors path="ad_firstname" cssClass="error" />
						</div>
						<div class="form-group">
							<form:label path="ad_email" cssClass="col-sm-2 control-label">信箱</form:label>
							<div class="col-sm-6">
								<form:input path="ad_email" id="id-input-ad-email" cssClass="form-control" />
							</div>
							<form:errors path="ad_email" cssClass="error" />
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<input type="submit" id="id-input-submit" class="btn btn-primary" value="註冊">
							</div>
						</div>
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
	<script src="<%=request.getContextPath()%>/js/admin/sign-up.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>