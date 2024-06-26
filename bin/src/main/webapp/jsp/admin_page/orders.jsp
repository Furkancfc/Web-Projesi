<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Orders</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/adminPageStyles.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Admin Dashboard</h1>
            <nav>
                <ul>
                    <li><a href="dashboard.jsp">Dashboard</a></li>
                    <li><a href="products.jsp">Products</a></li>
                    <li><a href="orders.jsp">Orders</a></li>
                    <li><a href="users.jsp">Users</a></li>
                </ul>
            </nav>
        </header>
        <main>
            <section id="orders">
                <h2>Manage Orders</h2>
                <div id="order-list">
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Product</th>
                                <th>Quantity</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody id="order-table-body">
                            <!-- Order rows will be added here dynamically -->
                        </tbody>
                    </table>
                </div>
            </section>
        </main>
    </div>
    <script src="<%=request.getContextPath()%>/js/adminscripts.js"></script>
</body>
</html>
