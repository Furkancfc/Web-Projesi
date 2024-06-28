<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> --%>

<%
if (session == null || session.getAttribute("userId") == null)
	response.sendRedirect(request.getContextPath() + "/login");
%>
<section class="cart">
	<h2>Your Cart</h2>
	<div id="cart-items">
		<c:choose>
			<c:when test="${not empty cart.items}">
				<c:forEach var="item" items="${cart.getItems()}">
					<div class="cart-item">
						<p>${item.getItem().getTitle()}</p>
						<p>Quantity: ${item.getItemCount()}</p>
						<p>Price: ${item.getPrice() }</p>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<p>Your cart is empty.</p>
			</c:otherwise>
		</c:choose>
	</div>
	<p id="total-price">Total: $0.00</p>
	<button id="checkout">Proceed to Checkout</button>
</section>
