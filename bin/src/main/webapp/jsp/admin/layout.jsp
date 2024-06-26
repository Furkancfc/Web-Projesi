<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
<c:import url="head.jsp" />
<c:catch>
	<link rel='stylessheet' href="${pageCss}" />
</c:catch>
<c:catch>
	<script src="${pageJs}" /></script>
</c:catch>
<c:catch>
	<c:import url="${pageHead}" />
</c:catch>
<link rel="stylesheet" href="/css/admin/style.css" />
<script src='/js/admin/layout.js'></script>
</head>
<body>

	<div class='container mx-0 p-0'>
		<header>
			<h1>Admin Dashboard</h1>
			<nav>
				<ul>
					<li><a href="/admin/dashboard">Dashboard</a></li>
					<li><a href="/admin/products">Products</a></li>
					<li><a href="/admin/orders">Orders</a></li>
					<li><a href="/admin/users">Users</a></li>
				</ul>
			</nav>
		</header>
		${pageContent }
		<c:catch>
			<jsp:include page="${pageContent}" />
		</c:catch>
	</div>
</body>

</html>
