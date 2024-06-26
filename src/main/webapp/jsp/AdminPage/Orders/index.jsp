<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<section id="orders">
	<h2>Manage Orders</h2>
	<div id="order-list">
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Product Id</th>
					<th>Quantity</th>
					<th>Total</th>
				</tr>

			</thead>
			<tbody id="order-table-body">
				<c:forEach var='user' items="${userService.getUsers()}">
					<h1>${user.getUserName()}'sOrders</h1>
					<c:forEach var='orders'
						items='${ordersService.getOrders(user.getOrdersId())}'>
						<th>User Orders List Id : ${orders.getOrdersId()}</th>
						<c:forEach var="order" items="${orders.getOrders()}">
							<td>${order.getOrderId()}</td>
							<td>${order.getOrderId()}</td>
							<td>${order.getCartItem().getItemCount() }</td>
						</c:forEach>
						<tr>
						</tr>
					</c:forEach>
				</c:forEach>
			</tbody>
		</table>
	</div>
</section>