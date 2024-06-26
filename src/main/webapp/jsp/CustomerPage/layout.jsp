<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
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
	<div class="container">
		<header>
			<div class="logo">
				<h1>Our fukking E-Commerce 😎</h1>
			</div>
			<nav>
				<ul>
					<li><a
						href="<%=request.getContextPath()%>/CustomerPage">Home</a></li>
					<li><a
						href="<%=request.getContextPath()%>/CustomerPage/Products">Products</a></li>
					<li><a href="<%=request.getContextPath()%>/CustomerPage/About">About</a></li>
					<li><a
						href="<%=request.getContextPath()%>/CustomerPage/Contact">Contact</a></li>
					<li><a href="<%=request.getContextPath()%>/CustomerPage/Cart"><i
							class="fas fa-shopping-cart"></i></a></li>
				</ul>
			</nav>
			<nav>
				<ul>
					<c:forEach var='category'
						items='${categoryService.getCategories()}'>
						<li><a
							href="<%=request.getContextPath()%>/CustomerPage?Category=${category.getName()}">${category.getName()}</a>
						</li>
					</c:forEach>
				</ul>
			</nav>
		</header>
		<main>
			<jsp:include page="${pageContent}" />
		</main>
	</div>
</body>
</html>
