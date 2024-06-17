<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
<c:import url="head.jsp" />
<c:catch>
	<c:import url="${pageHead}" />
</c:catch>
<c:catch>
	<link rel='stylessheet' href="<c:url value="${pageCss}"/>" />
</c:catch>
<c:catch>
	<script src="<c:url value="${pageJs}"/>"></script>
</c:catch>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/admin/layout.css" />
<script src="<%=request.getContextPath()%>/js/admin/layout.js"></script>
</head>
<body>
	<div class='container mx-0 p-0'>
		<nav class='navbar'>
			<div class='navbar-nav'>
				<div class='nav-item top'>
					<img src="/favicon.ico" />
				</div>
				<div class='nav-item'>
					<a href="/admin/categoryManagement" class="nav-link">Category
						Management</a>
				</div>
				<div class='nav-item'>
					<a href="/admin/accountManagement" class="nav-link">Account
						Management</a>
				</div>
				<div class='nav-item'>
					<a href="/admin/itemManagement" class='nav-link'>Item
						Management</a>
				</div>
			</div>
		</nav>
		<div class='content'>
			<c:catch>
				<jsp:include page="${pageContent}" />
			</c:catch>
		</div>
	</div>
</body>

</html>
