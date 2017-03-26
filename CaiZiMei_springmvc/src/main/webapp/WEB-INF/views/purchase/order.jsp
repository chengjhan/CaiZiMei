<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<form action="<c:url value='/member/sign-in.do' />" method="post">
		<div>
			<label for="id-co-name"></label>
			<select id="id-co-name" name="co_name">
			</select>
		</div>
		<div>
			<label for="id-ci-name"></label>
			<select id="id-ci-name" name="ci_name">
			</select>
		</div>
		<div>
			<label for="id-c-name"></label>
			<select id="id-c-name" name="c_name">
			</select>
		</div>
		<div>
			<input type="submit" id="id-submit" value="確定">
		</div>
	</form>
</body>
</html>