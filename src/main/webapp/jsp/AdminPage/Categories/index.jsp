<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Categories</title>
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
        .category-list {
            text-align: center;
            margin-top: 50px; /* Adjust as needed */
        }
        .category-list table {
            margin-top: 20px;
        }
    </style>
</head>
<body>

<div class='container'>
    <div class='category-list'>
        <h2>Manage Categories</h2>
        <table class='table table-bordered mt-3'>
            <thead class='thead-dark'>
                <tr>
                    <th>Category Id</th>
                    <th>Category Name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody id='category-table-body'>
                <c:forEach items="${categoryService.getCategories()}" var='category'>
                    <tr>
                        <td>${category.getCategoryId()}</td>
                        <td>${category.getName()}</td>
                        <td>
                            <button class='edit-category btn btn-primary btn-sm' data-id='${category.getCategoryId()}'>Edit</button>
                            <button class='view-details btn btn-info btn-sm' data-id='${category.getCategoryId()}'>Details</button>
                            <button class='delete-category btn btn-danger btn-sm' data-id='${category.getCategoryId()}'>Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <button class='btn btn-primary' id='add-category-button'>Add Category</button>
    </div>

    <div id='add-category-form' class='form-popup'>
        <form action='<%=request.getContextPath()%>/AdminPage/Categories' method='post'>
            <div class="form-group">
                <label for='name'>Name:</label>
                <input type='text' class='form-control' name='name' required>
            </div>
            <button type='submit' class='btn btn-primary'>Add Category</button>
            <button type='button' class='btn btn-secondary' id='add-category-cancel-button'>Cancel</button>
        </form>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    $(document).ready(function() {
        // Show add category form popup
        $('#add-category-button').click(function() {
            $('#add-category-form').fadeIn();
        });

        // Hide add category form popup
        $('#add-category-cancel-button').click(function() {
            $('#add-category-form').fadeOut();
        });

        // Edit button click event
        $('.edit-category').click(function() {
            var categoryId = $(this).data('id');
            // Implement edit functionality as needed
        });

        // Details button click event
        $('.view-details').click(function() {
            var categoryId = $(this).data('id');
            // Implement details functionality as needed
        });

        // Delete button click event
        $('.delete-category').click(function() {
            var categoryId = $(this).data('id');
            // Implement delete functionality as needed
        });
    });
</script>

</body>
</html>
 	