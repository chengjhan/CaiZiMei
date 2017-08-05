<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編輯城市資訊 - 采姿美管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/caizimei_shortcut_icon_black_32x32.ico" type="image/x-icon" />
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
				<h2 class="sub-header">編輯城市資訊</h2>
				
				<!-- content -->
				<div class="table-responsive">

					<!-- form -->
					<form:form action="/Admin/city/edit.do" method="post" modelAttribute="cityBean">
						<table>
							<tr style="display:none">
								<td>流水號</td>
								<td><form:hidden path="ci_id" /></td>
							</tr>
							<tr>
								<td>國家</td>
								<td>
									<form:select id="id-input-ci-co-id" path="ci_CountryBean">
										<form:option value="0" label="請選擇國家" />
										<form:options items="${countryList}" itemValue="co_id" itemLabel="co_name" />
									</form:select>
								</td>
							</tr>
							<tr>
								<td>區域</td>
								<td>
									<form:select id="id-input-ci-st-id" path="ci_StateBean">
										<form:option value="0" label="請選擇區域" />
										<form:options items="${stateList}" itemValue="st_id" itemLabel="st_name" />
									</form:select>
								</td>
							</tr>
							<tr>
								<td>名稱</td>
								<td><form:input path="ci_name" /></td>
								<td><form:errors path="ci_name" cssClass="error" /></td>
							</tr>
							<tr>
								<td>排序</td>
								<td><form:input path="ci_rank" /></td>
								<td><form:errors path="ci_rank" cssClass="error" /></td>
							</tr>
							<tr>
								<td><input type="submit" value="變更" /></td>
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
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/city/edit.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>