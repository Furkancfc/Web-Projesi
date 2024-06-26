<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Products</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
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
            <section class="product-list-section">
                <h2>Our Products</h2>
                <div id="product-list" class="product-grid">
                    <div class="product-item">
                        <h3>Product 1</h3>
                        <p><strong>Category:</strong> Electronics</p>
                        <p><strong>Price:</strong> $10.00</p>
                        <p><strong>Short Description:</strong> High-quality electronic gadget</p>
                        <p><strong>Long Description:</strong> This electronic gadget is made of high-quality materials, designed to last long and provide excellent performance.</p>
                        <button class="add-to-cart">Add to Cart</button>
                    </div>
                    <div class="product-item">
                        <h3>Product 2</h3>
                        <p><strong>Category:</strong> Home Appliances</p>
                        <p><strong>Price:</strong> $20.00</p>
                        <p><strong>Short Description:</strong> Durable home appliance</p>
                        <p><strong>Long Description:</strong> This home appliance is built to withstand daily use, offering great functionality and efficiency.</p>
                        <button class="add-to-cart">Add to Cart</button>
                    </div>
                    <div class="product-item">
                        <h3>Product 3</h3>
                        <p><strong>Category:</strong> Fashion</p>
                        <p><strong>Price:</strong> $30.00</p>
                        <p><strong>Short Description:</strong> Trendy fashion item</p>
                        <p><strong>Long Description:</strong> This fashion item is designed with the latest trends in mind, providing both style and comfort.</p>
                        <button class="add-to-cart">Add to Cart</button>
                    </div>
                    <!-- Add more products as needed -->
                </div>
            </section>
        </main>
    </div>
    <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
 
</body>
</html>
