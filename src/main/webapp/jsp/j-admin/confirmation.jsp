<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation</title>
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
    <div class="container">
        <header>
            <div class="logo">
                <h1>Our fukking E-Commerce 😎</h1>
            </div>
            <nav>
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="products.jsp">Products</a></li>
                    <li><a href="about.jsp">About</a></li>
                    <li><a href="contact.jsp">Contact</a></li>
                    <li><a href="cart.jsp"><i class="fas fa-shopping-cart"></i></a></li>
                </ul>
            </nav>
        </header>
        <main>
            <section class="confirmation">
                <h2>Order Confirmation</h2>
                <p>Thank you for your order! Your order number is: <strong><%= request.getAttribute("orderNumber") %></strong></p>
                <p>We have sent a confirmation email to: <strong><%= request.getAttribute("email") %></strong></p>
                <p>Estimated delivery date: <strong><%= request.getAttribute("deliveryDate") %></strong></p>
            </section>
        </main>
    </div>
    <script src="cart.js"></script>
</body>
</html>
