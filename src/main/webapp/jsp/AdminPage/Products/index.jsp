<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section id="products">
	<h2>Manage Products</h2>
	<button id="add-product-button">Add Product</button>
	<div id="product-list">
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Category</th>
					<th>Title</th>
					<th>Short Description</th>
					<th>Long Description</th>
					<th>Price</th>
					<th>Photos</th>
				</tr>
			</thead>
			<tbody id="product-table-body">
				<c:forEach items="${itemService.getItems()}" var="item">
					<tr>
						<td>${item.getItemId() }</td>
						<td>${item.getCategoryName()}</td>
						<td>${item.getTitle() }</td>
						<td>${item.getShortDesc() }</td>
						<td>${item.getLongDesc() }</td>
						<td>${item.getPrice() }</td>
						<td><c:forEach items="${item.getImageURLs()}" var="image">
								<img src="${image}" alt='image-not-found' />
							</c:forEach></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
	<div id="add-product-form" class="form-popup">
		<h3>Add Product</h3>
		<form id="product-form" enctype="multipart/form-data"
			action="<%=request.getContextPath()%>/AdminPage/Products"
			method="post">
			<div class="row">
				<label for="title">Title:</label> <input type="text" name="title"
					required />
			</div>
			<div class="row">
				<label for="short-desc">Short Description:</label> <input
					type="text" name="short-desc" required />
			</div>
			<div class="row">
				<label for="long-desc">Long Description:</label> <input type="text"
					name="long-desc" required />
			</div>
			<div class="row">
				<label for='category-name'>Category Name : </label> <select
					name="category">
					<c:forEach items="${categoryService.getCategories()}"
						var="category">
						<option value="${category.getName()}">${category.getName()}</option>
					</c:forEach>
				</select>
			</div>
			<div class='row'>
				<label for="price"> Price : </label> <input type="number" step="0.01" name="price" />
			</div>
			<div class="row">
				<label for="image">Image : </label> <input type="file" name="image"
					accept="image/*" multiple="multiple" />
			</div>
			<div class="row">
				<button type="submit">Add Product</button>
				<button type="button" id='add-product-cancel-button'>Cancel</button>
			</div>
		</form>
	</div>
</section>