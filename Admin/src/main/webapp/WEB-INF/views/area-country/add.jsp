<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增國家 - 采姿美管理系統</title>
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
				<h2 class="sub-header">新增國家</h2>
				
				<!-- content -->
				<div class="table-responsive">
					
					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath()%>/index">首頁</a></li>
						<li><a href="<%=request.getContextPath()%>/area-country/list">國家一覽</a></li>
						<li class="active">新增國家</li>
					</ol>
				
					<!-- form -->
					<form:form action="/Admin/area-country/add.do" method="post" modelAttribute="countryBean">
						<table class="table area-form-table">
							<tr>
								<td><form:label path="co_iso"><a href="https://zh.wikipedia.org/wiki/%E5%9C%8B%E5%AE%B6%E5%9C%B0%E5%8D%80%E4%BB%A3%E7%A2%BC" target="_blank">代碼</a></form:label></td>
								<td><form:input path="co_iso" cssClass="form-control" placeholder="請輸入 2 位英文字元的國際代碼" /></td>
								<td><form:errors path="co_iso" cssClass="error" /></td>
							</tr>
							<tr>
								<td><form:label path="co_name">名稱</form:label></td>
								<td><form:input path="co_name" cssClass="form-control" /></td>
								<td><form:errors path="co_name" cssClass="error" /></td>
							</tr>
							<tr>
								<td><form:label path="co_phonecode"><a href="https://zh.wikipedia.org/wiki/%E5%9B%BD%E9%99%85%E7%94%B5%E8%AF%9D%E5%8C%BA%E5%8F%B7%E5%88%97%E8%A1%A8" target="_blank">電話碼</a></form:label></td>
								<td><form:input path="co_phonecode" cssClass="form-control" placeholder="請輸入 5 位數字字元以內的國際電話碼" /></td>
								<td><form:errors path="co_phonecode" cssClass="error" /></td>
							</tr>
							<tr>
								<td><form:label path="co_rank">排序</form:label></td>
								<td><form:input path="co_rank" cssClass="form-control" /></td>
								<td><form:errors path="co_rank" cssClass="error" /></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<input type="submit" class="btn btn-success" value="新增" />
									<a href="<%=request.getContextPath()%>/area-country/list"><input type="button" class="btn btn-danger" value="取消" /></a>
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
	<script src="<%=request.getContextPath()%>/js/form.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>