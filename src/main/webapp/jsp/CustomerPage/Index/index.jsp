
<section class="hero">
	<h2>Welcome to Jabers and Furkans Store</h2>
	<p>Find the best products at unbeatable prices. We have more ways
		to take the price from you 😉</p>
</section>
<section id="best-sellers">
	<h2>Best Sellers</h2>
	<div class="product-grid">
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<c:forEach items="${itemService.getItems()}" var="item">
			<div class='product-item'>
				${item}
			</div>
		</c:forEach>
	</div>
</section>
<section id="new-arrivals">
	<h2>New Arrivals</h2>
	<!--  Gonna make conditional listing. ie. for a product, if listing time is smaller than 15 days, it will be listed -->
	<div class="product-grid">
		<c:forEach var='item' items='${itemService.getItems()}'>
			<div class="product-item">
				<img src="${item.getImageURLs().get(0)}" alt="${item.getTitle()}">
				<h3>${item.getTitle() }</h3>
				<p>${item.getPrice()}</p>
				<button class="add-to-cart">Add to Cart</button>
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