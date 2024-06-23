<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/styles.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<jsp:include page="${pageHead}"></jsp:include>
</head>
<body>
	<div class="container">
		<header>
			<div class="logo">
				<h1>Our fukking E-Commerce 😎</h1>
			</div>
			<nav>
				<ul>
					<li><a href="/jadmin/index">Home</a></li>
					<li><a href="/jadmin/product">Products</a></li>
					<li><a href="/jadmin/about">About</a></li>
					<li><a href="/jadmin/contact">Contact</a></li>
					<li><a href="/jadmin/cart"><i class="fas fa-shopping-cart"></i></a></li>
				</ul>
			</nav>
		</header>
		<main>
			<jsp:include page="${pageContent}" />
		</main>
	</div>
</body>
</html>
