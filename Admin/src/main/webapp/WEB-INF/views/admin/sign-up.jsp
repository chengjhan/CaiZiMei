<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>註冊 - 采姿美管理系統</title>
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
				<h2 class="sub-header">管理員註冊</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath()%>/index">首頁</a></li>
						<li class="active">管理員註冊</li>
					</ol>
				
					<!-- form -->
					<form:form action="/Admin/admin/sign-up.do" method="post" modelAttribute="adminBean" cssClass="form-horizontal">
						<table class="table admin-table">
							<tr>
								<td><form:label path="ad_username">帳號</form:label></td>
								<td><form:input path="ad_username" cssClass="form-control" /></td>
								<td>
									<span id="id-span-ad-username" class="error"></span>
									<form:errors path="ad_username" cssClass="error" />
								</td>
							</tr>
							<tr>
								<td><form:label path="ad_password">密碼</form:label></td>
								<td><form:password path="ad_password" cssClass="form-control" /></td>
								<td><form:errors path="ad_password" cssClass="error" /></td>
							</tr>
							<tr>
								<td><label for="ad_password_again">確認密碼</label></td>
								<td><input type="password" id="ad_password_again" class="form-control" name="ad_password_again"></td>
								<td></td>
								<td></td>
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
								<td>
									<span id="id-span-ad-email" class="error"></span>
									<form:errors path="ad_email" cssClass="error" />
								</td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" class="btn btn-success" value="註冊"></td>
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
	<script src="<%=request.getContextPath()%>/js/admin/form.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>