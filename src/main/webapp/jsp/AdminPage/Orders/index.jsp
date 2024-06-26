<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Orders</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        #orders {
            margin-top: 50px;
        }
        #order-list {
            max-height: 600px;
            overflow-y: auto;
        }
        table {
            width: 100%;
        }
        h1 {
            margin-top: 30px;
        }
        .order-section {
            margin-bottom: 50px;
        }
    </style>
</head>
<body>
<section id="orders" class="container">
    <h2 class="text-center">Manage Orders</h2>
    <div id="order-list" class="table-responsive">
        <table class="table table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>User</th>
                    <th>Order ID</th>
                    <th>Product ID</th>
                    <th>Quantity</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody id="order-table-body">
                <c:forEach var="user" items="${userService.getUsers()}">
                    <tr class="table-info">
                        <td colspan="5">
                            <h4>${user.getUserName()}'s Orders</h4>
                        </td>
                    </tr>
                    <c:forEach var="orders" items="${ordersService.getOrders(user.getOrdersId())}">
                        <tr class="table-secondary">
                            <td colspan="5">Order List ID: ${orders.getOrdersId()}</td>
                        </tr>
                        <c:forEach var="order" items="${orders.getOrders()}">
                            <tr>
                                <td>${user.getUserName()}</td>
                                <td>${order.getOrderId()}</td>
                                <td>${order.getCartItem().getItemCount()}</td>
                                <td>${order.getCartItem().getItemCount()}</td>
                                <td>${order.getTotal()}</td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
