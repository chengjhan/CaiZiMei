<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value='/admin/clinic/search.do' />" method="get">
		<div>
			<label for="id-c-name">名稱</label>
			<input type="text" id="id-c-name" name="c_name">
		</div>
		<div>
			<label for="id-c-localphone">電話</label>
			<input type="text" id="id-c-localphone" name="c_localphone">
		</div>
		<div>
			<input type="submit" id="id-submit" value="搜尋">
		</div>
	</form>
	<table border="1">
		<thead>
			<tr>
				<td>編號</td>
				<td>流水號</td>
				<td>名稱</td>
				<td>英文名稱</td>
				<td>電話</td>
				<td>國家</td>
				<td>城市</td>
				<td>區域</td>
				<td>地址</td>
				<td>緯度</td>
				<td>經度</td>
				<td>網址</td>
				<td>新增時間</td>
				<td>更新時間</td>
				<td>修改</td>
				<td>刪除</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bean" items="${selectByConditions}" varStatus="status">
				<c:url value="/admin/clinic/update" var="path">
					<c:param name="c_id" value="${bean.c_id}" />
					<c:param name="c_name" value="${bean.c_name}" />
					<c:param name="c_eng_name" value="${bean.c_eng_name}" />
					<c:param name="c_localphone" value="${bean.c_localphone}" />
					<c:param name="co_id" value="${bean.c_RegionBean.r_CityBean.ci_CountryBean.co_id}" />
					<c:param name="ci_id" value="${bean.c_RegionBean.r_CityBean.ci_id}" />
					<c:param name="r_id" value="${bean.c_RegionBean.r_id}" />
					<c:param name="c_address" value="${bean.c_address}" />
					<c:param name="c_url" value="${bean.c_url}" />
				</c:url>
				<tr>
					<td>${status.count}</td>
					<td>${bean.c_id}</td>
					<td>${bean.c_name}</td>
					<td>${bean.c_eng_name}</td>
					<td>${bean.c_localphone}</td>
					<td>${bean.c_RegionBean.r_CityBean.ci_CountryBean.co_name}</td>
					<td>${bean.c_RegionBean.r_CityBean.ci_name}</td>
					<td>${bean.c_RegionBean.r_name}</td>
					<td>${bean.c_address}</td>
					<td>${bean.c_latitude}</td>
					<td>${bean.c_longitude}</td>
					<td><a href="${bean.c_url}">${bean.c_url}</a></td>
					<td>${bean.c_insert_time}</td>
					<td>${bean.c_update_time}</td>
					<td><a href="${path}">修改</a></td>
					<td><a href="${root}admin/clinic/delete.do?c_id=${bean.c_id}">刪除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>