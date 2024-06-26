document.addEventListener('DOMContentLoaded', () => {
    const totalSales = 5000;
    const totalOrders = 150;
    const totalUsers = 300;

    const products = [
        { id: 1, name: 'Product 1', price: 100, photo: 'https://via.placeholder.com/100' },
        { id: 2, name: 'Product 2', price: 200, photo: 'https://via.placeholder.com/100' }
    ];

    const orders = [
        { id: 1, product: 'Product 1', quantity: 2, total: 200 },
        { id: 2, product: 'Product 2', quantity: 1, total: 200 }
    ];

    const users = [
        { id: 1, name: 'User 1', email: 'user1@example.com' },
        { id: 2, name: 'User 2', email: 'user2@example.com' }
    ];

    if (document.getElementById('dashboard')) {
        document.getElementById('total-sales').innerText = `$${totalSales}`;
        document.getElementById('total-orders').innerText = totalOrders;
        document.getElementById('total-users').innerText = totalUsers;

        const ctx = document.getElementById('sales-chart').getContext('2d');
        const salesChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
                datasets: [{
                    label: 'Sales Over Time',
                    data: [500, 1000, 750, 2000, 1500, 3000, 2500],
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    }

    if (document.getElementById('products')) {
        const productList = document.getElementById('product-table-body');
        products.forEach(product => {
            const productRow = document.createElement('tr');
            productRow.innerHTML = `
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>$${product.price}</td>
                <td><img src="${product.photo}" alt="${product.name}"></td>
            `;
            productList.appendChild(productRow);
        });

        const productForm = document.getElementById('product-form');
        productForm.addEventListener('submit', (e) => {
			$.ajax({
			  type: "POST",
			  url: url,
			  data: data,
			  success: success,
			  dataType: dataType
			});
            e.preventDefault();
            if (productName && productPrice) {
            } else {
                alert("Please fill in all fields.");
            }
        });
    }

    if (document.getElementById('orders')) {
        const orderList = document.getElementById('order-table-body');
        orders.forEach(order => {
            const orderRow = document.createElement('tr');
            orderRow.innerHTML = `
                <td>${order.id}</td>
                <td>${order.product}</td>
                <td>${order.quantity}</td>
                <td>$${order.total}</td>
            `;
            orderList.appendChild(orderRow);
        });
    }

    if (document.getElementById('users')) {
        const userList = document.getElementById('user-table-body');
        users.forEach(user => {
            const userRow = document.createElement('tr');
            userRow.innerHTML = `
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
            `;
            userList.appendChild(userRow);
        });
    }
});

function showAddProductForm() {
    document.getElementById('add-product-form').style.display = 'block';
}

function hideAddProductForm() {
    document.getElementById('add-product-form').style.display = 'none';
}
