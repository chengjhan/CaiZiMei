<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編輯國家資訊 | 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_black_32x32.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/region/form.css" type="text/css" />
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
				<h2 class="sub-header">編輯國家資訊</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- form -->
					<form:form action="/Admin/country/edit.do" method="post" modelAttribute="countryBean">
						<table class="table">
							<tr style="display:none">
								<td>流水號</td>
								<td><form:hidden path="co_id" /></td>
							</tr>
							<tr>
								<td>代碼</td>
								<td><form:input path="co_iso" cssClass="form-control" /></td>
								<td><form:errors path="co_iso" cssClass="error" /></td>
							</tr>
							<tr>
								<td>名稱</td>
								<td><form:input path="co_name" cssClass="form-control" /></td>
								<td><form:errors path="co_name" cssClass="error" /></td>
							</tr>
							<tr>
								<td>電話碼</td>
								<td><form:input path="co_phonecode" cssClass="form-control" /></td>
								<td><form:errors path="co_phonecode" cssClass="error" /></td>
							</tr>
							<tr>
								<td>排序</td>
								<td><form:input path="co_rank" cssClass="form-control" /></td>
								<td><form:errors path="co_rank" cssClass="error" /></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<input type="submit" class="btn btn-success" value="變更" />
									<a href="<%=request.getContextPath()%>/country/list"><button type="button" class="btn btn-danger">取消</button></a>
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
	<script src="<%=request.getContextPath()%>/js/bootstrap/bootstrap.min.js"></script>
</body>
</html>