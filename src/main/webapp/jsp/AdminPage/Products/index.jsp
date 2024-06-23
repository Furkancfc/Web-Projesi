<section id="products">
                <h2>Manage Products</h2>
                <button onclick="showAddProductForm()">Add Product</button>
                <div id="product-list">
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Photo</th>
                            </tr>
                        </thead>
                        <tbody id="product-table-body">
                            <!-- Product rows will be added here dynamically -->
                        </tbody>
                    </table>
                </div>
                <div id="add-product-form" class="form-popup">
                    <h3>Add Product</h3>
                    <form id="product-form">
                        <label for="product-name">Name:</label>
                        <input type="text" id="product-name" name="product-name" required>
                        <label for="product-price">Price:</label>
                        <input type="number" id="product-price" name="product-price" required>
                        <label for="product-photo">Photo:</label>
                        <input type="file" id="product-photo" name="product-photo" accept="image/*" required>
                        <button type="submit">Add Product</button>
                        <button type="button" onclick="hideAddProductForm()">Cancel</button>
                    </form>
                </div>
            </section>