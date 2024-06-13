<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
<c:import url="head.jsp" />
<c:catch>
	<c:import url="${pageHead}" />
</c:catch>
<link rel='stylessheet' href="<c:url value="${pageCss}"/>" />
<script src="<c:url value="${pageJs}"/>"></script>
<link rel="stylesheet" href="css/admin/layout.css" />
<script src="js/admin/layout.js"></script>
</head>
<body>
	<div class='container mx-0 p-0'>
		<nav class='navbar'>
			<div class='navbar-nav'>
				<div class='nav-item top'>
					<img src="/favicon.ico" />
				</div>
				<div class='nav-item'>
					<a href="#" class="nav-link">Category Management</a>
				</div>
				<div class='nav-item'>
					<a href="#" class="nav-link">Account Management</a>
				</div>
				<div class='nav-item'>
					<a href="#" class='nav-link'>Item Management</a>
				</div>
			</div>
		</nav>
		<div class='content'>
			<c:catch>
				<c:import url="${pageContent}" />
			</c:catch>
		</div>
	</div>
</body>

</html>
