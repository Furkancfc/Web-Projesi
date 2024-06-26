<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        #dashboard {
            padding: 20px;
        }
        .stats {
            display: flex;
            justify-content: space-around;
            margin-bottom: 30px;
        }
        .stat {
            flex: 1;
            margin: 0 15px;
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
            text-align: center;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .stat h3 {
            margin-bottom: 10px;
            font-size: 1.5rem;
        }
        .stat p {
            font-size: 1.2rem;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="container">
    <section id="dashboard" class="mt-5">
        <h2 class="text-center mb-4">Dashboard</h2>
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
        <h2 class="text-center mb-4">Sales Analysis</h2>
        <canvas id="sales-chart" width="400" height="200"></canvas>
    </section>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>
    $(document).ready(function() {
        // Example data
        const totalSales = 5000;
        const totalOrders = 120;
        const totalUsers = 300;
        
        $('#total-sales').text(`$${totalSales}`);
        $('#total-orders').text(totalOrders);
        $('#total-users').text(totalUsers);
        
        // Sales chart
        const ctx = document.getElementById('sales-chart').getContext('2d');
        const salesChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
                datasets: [{
                    label: 'Sales',
                    data: [500, 1000, 750, 1250, 1000, 1500, 2000],
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    });
</script>

</body>
</html>
