<html>
<head>
<c:import url="head.jsp" />
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
	</div>
</body>

</html>
