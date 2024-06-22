<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
    <div class="container">
        <header>
            <div class="logo">
                <h1>Our E-Commerce 😎</h1>
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
            <section class="hero">
                <h2>Welcome to Jabers and Furkans Store</h2>
                <p>Find the best products at unbeatable prices. We have more ways to take the price from you 😉</p>
            </section>
            <section id="best-sellers">
                <h2>Best Sellers</h2>
                <div class="product-grid">
                    <div class="product-item">
                        <img src="https://via.placeholder.com/150" alt="Product 1">
                        <h3>Product 1</h3>
                        <p>$10.00</p>
                        <button class="add-to-cart">Add to Cart</button>
                    </div>
                    <div class="product-item">
                        <img src="https://via.placeholder.com/150" alt="Product 2">
                        <h3>Product 2</h3>
                        <p>$20.00</p>
                        <button class="add-to-cart">Add to Cart</button>
                    </div>
                    <div class="product-item">
                        <img src="https://via.placeholder.com/150" alt="Product 3">
                        <h3>Product 3</h3>
                        <p>$30.00</p>
                        <button class="add-to-cart">Add to Cart</button>
                    </div>
                </div>
            </section>
            <section id="new-arrivals">
                <h2>New Arrivals</h2>
                <div class="product-grid">
                    <div class="product-item">
                        <img src="https://via.placeholder.com/150" alt="New Product 1">
                        <h3>New Product 1</h3>
                        <p>$15.00</p>
                        <button class="add-to-cart">Add to Cart</button>
                    </div>
                    <div class="product-item">
                        <img src="https://via.placeholder.com/150" alt="New Product 2">
                        <h3>New Product 2</h3>
                        <p>$25.00</p>
                        <button class="add-to-cart">Add to Cart</button>
                    </div>
                    <div class="product-item">
                        <img src="https://via.placeholder.com/150" alt="New Product 3">
                        <h3>New Product 3</h3>
                        <p>$35.00</p>
                        <button class="add-to-cart">Add to Cart</button>
                    </div>
                </div>
            </section>
            <section id="testimonials">
                <h2>Testimonials</h2>
                <div class="testimonial-grid">
                    <div class="testimonial-item">
                        <p>"Great products and amazing prices!"</p>
                        <p>- Customer 1</p>
                    </div>
                    <div class="testimonial-item">
                        <p>"The customer service is top-notch."</p>
                        <p>- Customer 2</p>
                    </div>
                    <div class="testimonial-item">
                        <p>"I love shopping here. Always find what I need."</p>
                        <p>- Customer 3</p>
                    </div>		
                </div>
            </section>
        </main>
    </div>
    <script src="cart.js"></script>
</body>
</html>
