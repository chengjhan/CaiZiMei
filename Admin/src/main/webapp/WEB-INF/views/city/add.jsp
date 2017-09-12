<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增城市 | 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/shortcut_icon_black.ico" type="image/x-icon" />
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
				<h2 class="sub-header">新增城市</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- form -->
					<form:form action="/Admin/city/add.do" method="post" modelAttribute="cityBean">
						<table class="table">
							<tr>
								<td><form:label path="ci_CountryBean">國家</form:label></td>
								<td>
									<form:select path="ci_CountryBean" cssClass="form-control">
										<form:option value="0" label="請選擇國家" />
										<form:options items="${countryList}" itemValue="co_id" itemLabel="co_name" />
									</form:select>
								</td>
							</tr>
							<tr>
								<td><form:label path="ci_StateBean">區域</form:label></td>
								<td>
									<form:select path="ci_StateBean" cssClass="form-control">
										<form:option value="0" label="請選擇區域" />
									</form:select>
								</td>
							</tr>
							<tr>
								<td><form:label path="ci_name">名稱</form:label></td>
								<td><form:input path="ci_name" cssClass="form-control" /></td>
								<td><form:errors path="ci_name" cssClass="error" /></td>
							</tr>
							<tr>
								<td><form:label path="ci_rank">排序</form:label></td>
								<td><form:input path="ci_rank" cssClass="form-control" /></td>
								<td><form:errors path="ci_rank" cssClass="error" /></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<input type="submit" class="btn btn-success" value="新增" />
									<a href="<%=request.getContextPath()%>/city/list"><button type="button" class="btn btn-danger">取消</button></a>
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
	<script src="<%=request.getContextPath()%>/js/city/form.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>