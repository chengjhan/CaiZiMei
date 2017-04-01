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
	<form action="<c:url value='/admin/country/update.do' />" method="post">
		<div style="display:none">
			<input type="text" id="id-co-id" name="co_id" value="${param.co_id}">
		</div>
		<div>
			<label for="id-co-name">國家</label>
			<input type="text" id="id-co-name" name="co_name" value="${param.co_name}">
		</div>
		<div>
			<label for="id-co-eng-name">國家英文</label>
			<input type="text" id="id-co-eng-name" name="co_eng_name" value="${param.co_eng_name}">
		</div>
		<div>
			<label for="id-co-rank">排序</label>
			<input type="text" id="id-co-rank" name="co_rank" value="${param.co_rank}">
		</div>
		<div>
			<input type="submit" id="id-submit" value="修改">
		</div>
	</form>
</body>
</html>