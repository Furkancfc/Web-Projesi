<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/adminPageStyles.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
            <section id="dashboard">
                <h2>Dashboard</h2>
                <div class="stats">
                    <div class="stat">
                        <h3>Total Sales</h3>
                        <p id="total-sales">$0</p>
                    </div>
                    <div class="stat">
                        <h3>Total Orders</h3>
                        <p id="total-orders">0</p>
                    </div>
                    <div class="stat">
                        <h3>Total Users</h3>
                        <p id="total-users">0</p>
                    </div>
                </div>
                <h2>Sales Analysis</h2>
                <canvas id="sales-chart" width="400" height="200"></canvas>
            </section>
        </main>
    </div>
    <script src="<%=request.getContextPath()%>/js/adminscripts.js"></script>
</body>
</html>
