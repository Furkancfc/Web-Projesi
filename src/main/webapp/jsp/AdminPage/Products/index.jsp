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
	<div id="add-product-form" class="form-popup" action="/AdminPage/Products">
		<h3>Add Product</h3>
		<form id="product-form">
			<label for="title">Title:</label>
			<input type="text" id="title" name="title" required />
			<label for="short-desc">Short Description:</label>
			<input type="text" id="short-desc" name="short-desc" required />
			<label for="long-desc">Long Description:</label>
			<input type="text" name="long-desc" required />
			<input type="text" id="category-name" name="category-name" />
			<input type="text" id="price" name="price" />
			<label for="image">Image : </label>
			<input type="image" id="image" name="image" />
			<button type="submit">Add Product</button>
			<button type="button" onclick="hideAddProductForm()">Cancel</button>
		</form>
	</div>
</section>