<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編輯個人資訊 | 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_black_32x32.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/all.css" type="text/css" />
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
						<table class="table">
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
								<td>
									<input type="submit" class="btn btn-success" value="變更" />
<%-- 									<a href="<%=request.getContextPath()%>/admin/profile"><button type="button" class="btn btn-danger">取消</button></a> --%>
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
	<script src="<%=request.getContextPath()%>/js/admin/form.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>