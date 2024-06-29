<%@page import="service.implement.ItemServiceImpl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="service.implement.CartServiceImpl"%>
<%
String cartId = (String) session.getAttribute("userId");
String cartItemId = request.getParameter("cartItem");
pageContext.setAttribute("cartItemId", cartItemId);
pageContext.setAttribute("cartId", cartId);
pageContext.setAttribute("cart", ((CartServiceImpl) application.getAttribute("cartService")).getCart(cartId));
if (cartItemId != null) {
	pageContext.setAttribute("item", ((ItemServiceImpl) application.getAttribute("itemService")).getItem(cartItemId));
}
%>
<section id="checkout">
	<c:choose>
		<c:when test="${not empty cartItemId }">
			<div class='checkout-form'>
				<p>Cart Id : ${cartId }</p>
				<p>Cart Item Id : ${cartItemId }</p>
				<p>Item Title : ${item.getTitle()}</p>
				<p>Price : ${item.getPrice()}</p>
				<div class="item-description">
					<h1>${item.getShortDesc()}</h1>
					<p>${item.getLongDesc()}</p>
				</div>
				<form class='checkout-form' method="post"
					action="<%=request.getContextPath()%>/CustomerPage/Checkout"
					enctype="application/*">
					<input type="hidden" name="cartId" value="${cartId}" /> <input
						type='hidden' name='cartItemId' value='${cartItemId}' /> <label
						for="">Payment Method : </label> <select name="payment-method">
						<optgroup label="Credit-Cart">
							<option value="Cart Corporation 1">Cart Corporation 1</option>
							<option value="Cart Corporation 2">Cart Corporation 2</option>
							<option value="Cart Corporation 3">Cart Corporation 3</option>
							<option value="Cart Corporation 4">Cart Corporation 4</option>
						</optgroup>
					</select> <br />
					<button type="submit">Give Order with ${item.getPrice()}</button>
				</form>
			</div>
		</c:when>
		<c:otherwise>
			<div class='checkout-form'>
				<form class='checkout-form' method="post"
					action="<%=request.getContextPath()%>/CustomerPage/Checkout"
					enctype="application/*">
					<input type='hidden' name="cartId" value="${cartId}" />
					<p>Cart : ${cart}</p>
					Items :
					<ul>
						<c:forEach var="cartItem" items="${cart.getItems()}">
							<c:set var="item"
								value="${itemService.getItem(cartItem.getItemId())}" />
							<li>
								<div class='item'>
									<p>Item Title : ${item.getTitle()}</p>
									<p>Item Price : ${item.getPrice()}</p>
									<p>Item Count : ${cartItem.getItemCount()}</p>
								</div>
							</li>
						</c:forEach>
					</ul>
					<select name="payment-method">
						<optgroup label="Credit-Cart">
							<option value="Cart Corporation 1">Cart Corporation 1</option>
							<option value="Cart Corporation 2">Cart Corporation 2</option>
							<option value="Cart Corporation 3">Cart Corporation 3</option>
							<option value="Cart Corporation 4">Cart Corporation 4</option>
						</optgroup>
					</select> <br />
					<button type="submit">Give Order with
						${cartService.calculateCartPrice(cartId)}</button>
				</form>
			</div>
		</c:otherwise>
	</c:choose>
</section>