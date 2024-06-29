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
			<c:when test="${not empty cart.getItems()}">
				<c:forEach var="cartItem" items="${cart.getItems()}">
					<c:set var="item"
						value="${itemService.getItem(cartItem.getItemId())}" />
					<div class="cart-item">
						<p>${item.getTitle()}</p>
						<p>Quantity: ${cartItem.getItemCount()}</p>
						<p>Price: ${item.getPrice()}</p>
						<a class='btn btn-primary'
							href="<%=request.getContextPath() %>/CustomerPage/Checkout?cartId=${cart.getURL()}&cartItem=${cartItem.getURL()}">Buy</a>
					</div>
					<hr />
				</c:forEach>
				<p id="total-price"></p>
				<div id="chechkout-div">
					<p>Total Price :
						${cartService.calculateCartPrice(cart.getCartId())}</p>
					<a class='btn btn-primary' id='accept-checkout'
						href="<%= request.getContextPath() %>/CustomerPage/Checkout?cartId=${cart.getURL()}">Accept</a>
					<button class='btn btn-danger' id="cancel-checkout">Cancel</button>
				</div>
			</c:when>
			<c:otherwise>
				<p>Your cart is empty.</p>
			</c:otherwise>
		</c:choose>
	</div>
</section>
