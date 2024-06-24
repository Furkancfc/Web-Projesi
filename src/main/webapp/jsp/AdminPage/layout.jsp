<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:catch>
	<link rel='stylessheet' href="${pageCss}" />
</c:catch>
<c:catch>
	<script src="${pageJs}" /></script>
</c:catch>
<c:catch>
	<c:import url="${pageHead}" />
</c:catch>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/AdminPage/layout.css" />
<script src="<%=request.getContextPath()%>/js/AdminPage/layout.js"></script>
</head>
<body>
	<c:catch>
		<jsp:include page="${pageContent}" />
	</c:catch>
</body>
</html>