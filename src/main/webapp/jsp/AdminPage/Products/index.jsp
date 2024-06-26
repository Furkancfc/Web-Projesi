<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Products</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .form-popup {
            display: none;
            position: fixed;
            bottom: 0;
            right: 15px;
            border: 3px solid #f1f1f1;
            z-index: 9;
            max-width: 400px;
            width: 100%;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        #product-list {
            max-height: 500px;
            overflow-y: auto;
        }
    </style>
</head>
<body>

<section id="products" class="container mt-5">
    <h2>Manage Products</h2>
    <div id="product-list" class="mt-3">
        <table class="table table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Short Description</th>
                    <th>Long Description</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody id="product-table-body">
                <c:forEach items="${itemService.getItems()}" var="item">
                    <tr data-id="${item.getItemId()}">
                        <td>${item.getItemId()}</td>
                        <td>${item.getTitle()}</td>
                        <td>${item.getShortDesc()}</td>
                        <td>${item.getLongDesc()}</td>
                        <td>${item.getPrice()}</td>
                        <td>
                            <button class="edit-product btn btn-primary btn-sm" data-id="${item.getItemId()}">Edit</button>
                            <button class="btn btn-info btn-sm view-details" data-id="${item.getItemId()}">Details</button>
                            <button class="btn btn-danger btn-sm delete-product" data-id="${item.getItemId()}">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <button type="button" class="btn btn-success mt-3" id="add-product">Add Product</button>
    <div class="form-popup" id="add-product-form">
        <form id="product-form" enctype="multipart/form-data" action="<%=request.getContextPath()%>/AdminPage/Products" method="post">
            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" class="form-control" id="title" name="title" required>
            </div>
            <div class="form-group">
                <label for="short-desc">Short Description:</label>
                <input type="text" class="form-control" id="short-desc" name="short-desc" required>
            </div>
            <div class="form-group">
                <label for="long-desc">Long Description:</label>
                <input type="text" class="form-control" id="long-desc" name="long-desc" required>
            </div>
            <div class="form-group">
                <label for="category">Category:</label>
                <select name="category" class="form-control" id="category">
                    <c:forEach items="${categoryService.getCategories()}" var="category">
                        <option value="${category.getName()}">${category.getName()}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="text" class="form-control" id="price" name="price" required>
            </div>
            <div class="form-group">
                <label for="image">Image:</label>
                <input type="file" class="form-control" id="image" name="image" accept="image/*" multiple="multiple">
            </div>
            <button type="submit" class="btn btn-primary">Add Product</button>
            <button type="button" class="btn btn-secondary" id="cancel-add-product">Cancel</button>
        </form>
    </div>
    <div class="form-popup" id="edit-product-form">
        <form id="edit-form" action="<%=request.getContextPath()%>/AdminPage/updateProduct" method="post">
            <input type="hidden" id="edit-product-id" name="product-id">
            <div class="form-group">
                <label for="edit-title">Title:</label>
                <input type="text" class="form-control" id="edit-title" name="title" required>
            </div>
            <div class="form-group">
                <label for="edit-short-desc">Short Description:</label>
                <input type="text" class="form-control" id="edit-short-desc" name="short-desc" required>
            </div>
            <div class="form-group">
                <label for="edit-long-desc">Long Description:</label>
                <input type="text" class="form-control" id="edit-long-desc" name="long-desc" required>
            </div>
            <div class="form-group">
                <label for="edit-category">Category:</label>
                <select name="edit-category" class="form-control" id="edit-category">
                    <c:forEach items="${categoryService.getCategories()}" var="category">
                        <option value="${category.getName()}">${category.getName()}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="edit-price">Price:</label>
                <input type="text" class="form-control" id="edit-price" name="price" required>
            </div>
            <button type="submit" class="btn btn-primary">Save Changes</button>
            <button type="button" class="btn btn-secondary" id="cancel-edit-product">Cancel</button>
        </form>
    </div>
    <div class="modal" id="product-details-modal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Product Details</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <p><strong>ID:</strong> <span id="details-product-id"></span></p>
                    <p><strong>Title:</strong> <span id="details-product-title"></span></p>
                    <p><strong>Short Description:</strong> <span id="details-product-short-desc"></span></p>
                    <p><strong>Long Description:</strong> <span id="details-product-long-desc"></span></p>
                    <p><strong>Price:</strong> <span id="details-product-price"></span></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</section>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    $(document).ready(function() {
        // Edit button click event
        $('.edit-product').click(function() {
            var productId = $(this).data('id');
            var title = $(this).closest('tr').find('td:nth-child(2)').text();
            var shortDesc = $(this).closest('tr').find('td:nth-child(3)').text();
            var longDesc = $(this).closest('tr').find('td:nth-child(4)').text();
            var price = $(this).closest('tr').find('td:nth-child(5)').text();

            // Populate edit form fields
            $('#edit-product-id').val(productId);
            $('#edit-title').val(title);
            $('#edit-short-desc').val(shortDesc);
            $('#edit-long-desc').val(longDesc);
            $('#edit-price').val(price);

            // Show the edit form popup
            $('#edit-product-form').fadeIn();
        });

        // Cancel edit button click event
        $('#cancel-edit-product').click(function() {
            $('#edit-product-form').fadeOut();
        });

        // Details button click event
        $('.view-details').click(function() {
            var productId = $(this).data('id');
            var title = $(this).closest('tr').find('td:nth-child(2)').text();
            var shortDesc = $(this).closest('tr').find('td:nth-child(3)').text();
            var longDesc = $(this).closest('tr').find('td:nth-child(4)').text();
            var price
            // Populate details modal
            $('#details-product-id').text(productId);
            $('#details-product-title').text(title);
            $('#details-product-short-desc').text(shortDesc);
            $('#details-product-long-desc').text(longDesc);
            $('#details-product-price').text(price);

            // Show the details modal
            $('#product-details-modal').modal('show');
        });

        // Delete button click event
        $('.delete-product').click(function() {
            var productId = $(this).data('id');

            // Assuming you have a confirmation logic here, for simplicity, we'll just send a confirmation alert
            if (confirm("Are you sure you want to delete this product?")) {
                // You can make an AJAX call here to delete the product from the server
                $.ajax({
                    type: 'POST',
                    url: '<%=request.getContextPath()%>/AdminPage/deleteProduct',
                    data: { 'product-id': productId },
                    success: function(response) {
                        // Handle success response, if any
                        // For example, you might want to remove the row from the table
                        $('tr[data-id="' + productId + '"]').remove();
                    },
                    error: function(xhr, status, error) {
                        // Handle error, if any
                        console.error(error);
                        alert('Error deleting product. Please try again.');
                    }
                });
            }
        });

        // Show add product form
        $('#add-product').click(function() {
            $('#add-product-form').fadeIn();
        });

        // Cancel add product form
        $('#cancel-add-product').click(function() {
            $('#add-product-form').fadeOut();
        });

        // No need for the submit event script here, as the form's action is set to the servlet for handling.
    });
</script>

</body>
</html>
            