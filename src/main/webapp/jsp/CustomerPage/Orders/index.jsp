<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<div class='orders'>
	<c:forEach items='${orders.getOrders().values()}' var="order">
		<div class='order'>
			<p>Order Item Title :
				${itemService.getItem(order.getCartItem().getItemId()).getTitle()}</p>
		</div>
	</c:forEach>
</div>