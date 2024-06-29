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
					<th>Order Amount</th>
				</tr>
			</thead>
			<tbody id="order-table-body">
				<c:forEach var='user' items="${userService.getUsers()}">
					<th>${user.getUserName()}'sOrders</th>
					<c:set var='userorders'
						value='${ordersService.getOrders(user.getUserId())}' />
					<tr>
						<th>User</th>
						<th>${userorders.getOrdersId()}</th>
					</tr>
					<c:forEach var="order" items="${userorders.getOrders().values()}"> <!--  treeMap tan value lar alinir -->
						<td>${order.getOrderId()}</td>
						<td>${order.getCartItem().getItemId()}</td>
						<td>${order.getCartItem().getItemCount()}</td>
						<td>${order.getPrice()}</td>
					</c:forEach>
				</c:forEach>
			</tbody>
		</table>
	</div>
</section>