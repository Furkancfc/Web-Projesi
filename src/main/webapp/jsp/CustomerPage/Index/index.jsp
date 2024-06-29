<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<h2>Welcome to Jabers and Furkans Store</h2>
<p>Find the best products at unbeatable prices. We have more ways to
	take the price from you 😉</p>
<section id="new-arrivals">
	<h2>New Arrivals</h2>
	<!--  Gonna make conditional listing. ie. for a product, if listing time is smaller than 15 days, it will be listed -->
	<div class="product-grid">
		<c:forEach var='item' items='${itemService.getItems()}'>
			<div class="product-item">
				<c:choose>
					<c:when test="${not empty item.getImageURLs() }">
						<img src="${item.getImageURLs().get(0)}" alt="${item.getTitle()}">
					</c:when>
				</c:choose>
				<h3>${item.getTitle() }</h3>
				<p>${item.getPrice()}</p>
				<a class='btn add-to-cart' type="button"
					href="${pageContext.request.contextPath}/CustomerPage?addToCart=&itemId=${item.getURL()}">Add
					to Cart</a>
			</div>
		</c:forEach>
	</div>
</section>
<section id="testimonials">
	<h2>Testimonials</h2>
	<div class="testimonial-grid">
		<div class="testimonial-item">
			<p>"Great products and amazing prices!"</p>
			<p>- Customer 1</p>
		</div>
		<div class="testimonial-item">
			<p>"The customer service is top-notch."</p>
			<p>- Customer 2</p>
		</div>
		<div class="testimonial-item">
			<p>"I love shopping here. Always find what I need."</p>
			<p>- Customer 3</p>
		</div>
	</div>
</section>