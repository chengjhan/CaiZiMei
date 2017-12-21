<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>pagination</title>
</head>
<body>
	<c:if test="${pageCount > 1}">
		<nav class="page">
			<ul class="pagination">
				<c:choose>
					<c:when test="${currentGroup eq 1}">
						<li class="disabled"><a><span>&laquo;&laquo;</span></a></li>
					</c:when>
					<c:when test="${currentGroup > 1}">
						<li><a href="<%=request.getContextPath()%>/${ca_directory}/list?page=${(currentGroup - 2)*groupRowCount + 1}" title="前 10 頁"><span>&laquo;&laquo;</span></a></li>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${currentPage eq 1}">
						<li class="disabled"><a><span>&laquo;</span></a></li>
					</c:when>
					<c:when test="${currentPage > 1}">
						<li><a href="<%=request.getContextPath()%>/${ca_directory}/list?page=${currentPage - 1}" title="上 1 頁"><span>&laquo;</span></a></li>
					</c:when>
				</c:choose>
				<c:forEach begin="${currentGroupBegin}" end="${currentGroupEnd}" varStatus="status">
					<li id="id-li-page-${status.count + (currentGroup - 1)*groupRowCount}"><a href="<%=request.getContextPath()%>/${ca_directory}/list?page=${status.count + (currentGroup - 1)*groupRowCount}">${status.count + (currentGroup - 1)*groupRowCount}</a></li>
				</c:forEach>
				<c:choose>
					<c:when test="${currentPage < pageCount}">
						<li><a href="<%=request.getContextPath()%>/${ca_directory}/list?page=${currentPage + 1}" title="下 1 頁"><span>&raquo;</span></a></li>
					</c:when>
					<c:when test="${currentPage eq pageCount}">
						<li class="disabled"><a><span>&raquo;</span></a></li>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${currentGroup < groupCount}">
						<li><a href="<%=request.getContextPath()%>/${ca_directory}/list?page=${currentGroup*groupRowCount + 1}" title="後 10 頁"><span>&raquo;&raquo;</span></a></li>
					</c:when>
					<c:when test="${currentGroup eq groupCount}">
						<li class="disabled"><a><span>&raquo;&raquo;</span></a></li>
					</c:when>
				</c:choose>
			</ul>
		</nav>
	</c:if>
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/pagination.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>