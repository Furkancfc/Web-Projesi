<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
	integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/styles.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/AdminPage/layout.css" />
<script src="<%=request.getContextPath()%>/js/AdminPage/layout.js"></script>
<c:catch>
	<link rel='stylessheet' href="${pageCss}" />
</c:catch>
<c:catch>
	<script src="${pageJs}" /></script>
</c:catch>
<c:catch>
	<c:import url="${pageHead}" />
</c:catch>
</head>
<body>
	<c:catch>
		<jsp:include page="${pageContent}" />
	</c:catch>
</body>
</html>