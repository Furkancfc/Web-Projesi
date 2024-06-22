<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Users</title>
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
            <section id="users">
                <h2>Manage Users</h2>
                <div id="user-list">
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Email</th>
                            </tr>
                        </thead>
                        <tbody id="user-table-body">
                            <!-- User rows will be added here dynamically -->
                        </tbody>
                    </table>
                </div>
            </section>
        </main>
    </div>
    <script src="<%=request.getContextPath()%>/js/adminscripts.js"></script>
</body>
</html>
