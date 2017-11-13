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
					<c:when test="${currentPage eq 1}">
						<li class="disabled"><a><span aria-hidden="true">&laquo;&laquo;</span><span class="sr-only">Previous</span></a></li>
					</c:when>
					<c:when test="${currentPage > 1}">
						<li><a href="<%=request.getContextPath()%>/${ca_directory}/list?page=1"><span aria-hidden="true">&laquo;&laquo;</span><span class="sr-only">Previous</span></a></li>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${currentPage eq 1}">
						<li class="disabled"><a><span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>
					</c:when>
					<c:when test="${currentPage > 1}">
						<li><a href="<%=request.getContextPath()%>/${ca_directory}/list?page=${currentPage - 1}"><span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>
					</c:when>
				</c:choose>
				<c:forEach begin="${currentGroupStart}" end="${currentGroupEnd}" varStatus="status">
					<li id="id-li-page-${status.count + groupRowCount*(currentGroup - 1)}"><a href="<%=request.getContextPath()%>/${ca_directory}/list?page=${status.count + groupRowCount*(currentGroup - 1)}">${status.count + groupRowCount*(currentGroup - 1)}</a></li>
				</c:forEach>
				<c:choose>
					<c:when test="${currentPage < pageCount}">
						<li><a href="<%=request.getContextPath()%>/${ca_directory}/list?page=${currentPage + 1}"><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>
					</c:when>
					<c:when test="${currentPage eq pageCount}">
						<li class="disabled"><a><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${currentPage < pageCount}">
						<li><a href="<%=request.getContextPath()%>/${ca_directory}/list?page=${pageCount}"><span aria-hidden="true">&raquo;&raquo;</span><span class="sr-only">Next</span></a></li>
					</c:when>
					<c:when test="${currentPage eq pageCount}">
						<li class="disabled"><a><span aria-hidden="true">&raquo;&raquo;</span><span class="sr-only">Next</span></a></li>
					</c:when>
				</c:choose>
			</ul>
		</nav>
	</c:if>
	
	<!-- load -->
	<script src="<%=request.getContextPath()%>/js/pagination.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>