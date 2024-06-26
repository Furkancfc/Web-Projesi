<section class="confirmation">
	<h2>Order Confirmation</h2>
	<p>Thank you for your order! Your order number is: <strong><%= request.getAttribute("orderNumber") %></strong></p>
	<p>We have sent a confirmation email to: <strong><%= request.getAttribute("email") %></strong></p>
	<p>Estimated delivery date: <strong><%= request.getAttribute("deliveryDate") %></strong></p>
</section>