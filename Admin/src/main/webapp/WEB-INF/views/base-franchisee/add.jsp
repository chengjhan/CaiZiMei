<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增加盟店 - 采姿美管理系統</title>
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
				<h2 class="sub-header">新增加盟店</h2>
				
				<!-- content -->
				<div class="table-responsive">
				
					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath()%>/index">首頁</a></li>
						<li><a href="<%=request.getContextPath()%>/base-franchisee/list?page=1">加盟店一覽</a></li>
						<li class="active">新增加盟店</li>
					</ol>
				
					<!-- form -->
					<form:form action="/Admin/base-franchisee/add.do" method="post" modelAttribute="baseBean">
						<table class="table base-table">
							<tr>
								<td><form:label path="ba_name">名稱</form:label></td>
								<td>
									<form:input path="ba_name" cssClass="form-control" />
									<div class="error"></div>
								</td>
								<td><form:errors path="ba_name" cssClass="error" /></td>
							</tr>
							<tr>
								<td><form:label path="ba_eng_name">英文名稱</form:label></td>
								<td>
									<form:input path="ba_eng_name" cssClass="form-control" />
									<div class="error"></div>
								</td>
								<td><form:errors path="ba_eng_name" cssClass="error" /></td>
							</tr>
							<tr>
								<td><form:label path="ba_tel_code">電話</form:label></td>
								<td>
									<form:input path="ba_tel_code" cssClass="form-control" />
									<form:input path="ba_tel" cssClass="form-control" />
									<div id="ba_tel_code_error" class="error"></div>
									<div id="ba_tel_error" class="error"></div>
								</td>
								<td><form:errors path="ba_tel" cssClass="error" /></td>
							</tr>
							<tr>
								<td><form:label path="ba_CountryBean">國家</form:label></td>
								<td>
									<form:select path="ba_CountryBean" cssClass="form-control">
										<form:option value="0" label="請選擇國家" />
										<form:options items="${countryList}" itemValue="co_id" itemLabel="co_name" />
									</form:select>
									<div class="error"></div>
								</td>
							</tr>
							<tr>
								<td><form:label path="ba_StateBean">區域</form:label></td>
								<td>
									<form:select path="ba_StateBean" cssClass="form-control">
										<form:option value="0" label="請選擇區域" />
									</form:select>
									<div class="error"></div>
								</td>
							</tr>
							<tr>
								<td><form:label path="ba_CityBean">城市</form:label></td>
								<td>
									<form:select path="ba_CityBean" cssClass="form-control">
										<form:option value="0" label="請選擇城市" />
									</form:select>
									<div class="error"></div>
								</td>
							</tr>
							<tr>
								<td><form:label path="ba_address">地址</form:label></td>
								<td>
									<form:input path="ba_address" cssClass="form-control" />
									<div class="error"></div>
								</td>
								<td><form:errors path="ba_address" cssClass="error" /></td>
							</tr>
							<tr>
								<td><form:label path="ba_url">網址</form:label></td>
								<td>
									<form:input path="ba_url" cssClass="form-control" />
									<div class="error"></div>
								</td>
								<td><form:errors path="ba_url" cssClass="error" /></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<input type="submit" class="btn btn-success" value="新增" />
									<a href="<%=request.getContextPath()%>/base-franchisee/list?page=1"><button type="button" class="btn btn-danger">取消</button></a>
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
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/validation/jquery.validate.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/base/form.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>