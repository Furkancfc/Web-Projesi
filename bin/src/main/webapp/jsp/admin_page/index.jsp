<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/adminPageStyles.css">
</head>
<body>
    <script>
        // Redirect to the dashboard page
        window.location.href = "dashboard.jsp";
    </script>
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
    </div>
</body>
</html>
