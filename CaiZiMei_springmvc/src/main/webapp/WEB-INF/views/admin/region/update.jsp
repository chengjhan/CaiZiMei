<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<form action="<c:url value='/admin/region/update.do' />" method="post">
		<div style="display:none">
			<input type="text" id="id-r-id" name="r_id" value="${param.r_id}">
		</div>
		<div>
			<label for="id-co-id">國家</label>
			<select id="id-co-id" name="co_id">
				<option>請選擇國家</option>
			</select>
		</div>
		<div>
			<label for="id-r-ci-id">城市</label>
			<select id="id-r-ci-id" name="r_ci_id">
				<option>請選擇城市</option>
			</select>
		</div>
		<div>
			<label for="id-r-name">區域</label>
			<input type="text" id="id-r-name" name="r_name" value="${param.r_name}">
		</div>
		<div>
			<label for="id-r-zipcode">郵遞區號</label>
			<input type="text" id="id-r-zipcode" name="r_zipcode" value="${param.r_zipcode}">
		</div>
		<div>
			<label for="id-r-rank">排序</label>
			<input type="text" id="id-r-rank" name="r_rank" value="${param.r_rank}">
		</div>
		<div>
			<input type="submit" id="id-submit" value="修改">
		</div>
	</form>
</body>
</html>