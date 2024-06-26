<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Users</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="path/to/your/style.css"> <!-- Ensure this path is correct -->
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
        #user-list {
            max-height: 500px;
            overflow-y: auto;
        }
    </style>
</head>
<body>

<section id="users" class="container mt-5">
    <h2>Manage Users</h2>
    <div id="user-list" class="mt-3">
        <table class="table table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody id="user-table-body">
                <c:forEach items="${userService.getUsers()}" var="user">
                    <tr data-id="${user.getUserId()}">
                        <td>${user.getUserId()}</td>
                        <td>${user.getUserName()}</td>
                        <td>${user.getEmail()}</td>
                        <td>
                            <button class="edit-user btn btn-primary btn-sm" data-id="${user.getUserId()}">Edit</button>
                            <button class="btn btn-info btn-sm view-details" data-id="${user.getUserId()}">Details</button>
                            <button class="btn btn-danger btn-sm delete-user" data-id="${user.getUserId()}">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <button type="button" class="btn btn-success mt-3" id="add-user">Add User</button>
    <div class="form-popup" id="add-user-form">
        <form id="user-form" action="<%=request.getContextPath()%>/AdminPage/Users" method="post">
            <div class="form-group">
                <label for="user-name">User Name:</label>
                <input type="text" class="form-control" id="user-name" name="user-name" required>
            </div>
            <div class="form-group">
                <label for="user-email">Email:</label>
                <input type="email" class="form-control" id="user-email" name="user-email" required>
            </div>
            <div class="form-group">
                <label for="user-password">Password:</label>
                <input type="password" class="form-control" id="user-password" name="user-password" required>
            </div>
            <div class="form-group">
                <label for="auth">Authorization:</label>
                <select name="auth" class="form-control" id="auth-types">
                    <option value="admin">Admin</option>
                    <option value="customer">Customer</option>
                    <option value="seller">Seller</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Add User</button>
            <button type="button" class="btn btn-secondary" id="cancel-add-user">Cancel</button>
        </form>
    </div>
    <div class="form-popup" id="edit-user-form">
        <form id="edit-form" action="<%=request.getContextPath()%>/AdminPage/updateUser" method="post">
            <input type="hidden" id="edit-user-id" name="user-id">
            <div class="form-group">
                <label for="edit-user-name">User Name:</label>
                <input type="text" class="form-control" id="edit-user-name" name="user-name" required>
            </div>      
            <div class="form-group">
                <label for="edit-user-email">Email:</label>
                <input type="email" class="form-control" id="edit-user-email" name="user-email" required>
            </div>
            <div class="form-group">
                <label for="edit-user-password">Password:</label>
                <input type="password" class="form-control" id="edit-user-password" name="user-password">
            </div>
            <input type="hidden" name="user-id" id="edit-user-id"/>
            <button type="submit" class="btn btn-primary">Save Changes</button>
            <button type="button" class="btn btn-secondary" id="cancel-edit-user">Cancel</button>
        </form>
    </div>
    <div class="modal" id="user-details-modal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">User Details</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <p><strong>ID:</strong> <span id="details-user-id"></span></p>
                    <p><strong>Name:</strong> <span id="details-user-name"></span></p>
                    <p><strong>Email:</strong> <span id="details-user-email"></span></p>
                    <p><strong>Authorization:</strong> <span id="details-user-auth"></span></p>
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
        $('.edit-user').click(function() {
            var userId = $(this).data('id');
            var userName = $(this).closest('tr').find('td:nth-child(2)').text();
            var userEmail = $(this).closest('tr').find('td:nth-child(3)').text();

            // Populate edit form fields
            $('#edit-user-id').val(userId);
            $('#edit-user-name').val(userName);
            $('#edit-user-email').val(userEmail);

            // Show the edit form popup
            $('#edit-user-form').fadeIn();
        });

        // Cancel edit button click event
        $('#cancel-edit-user').click(function() {
            $('#edit-user-form').fadeOut();
        });

        // Details button click event
        $('.view-details').click(function() {
            var userId = $(this).data('id');
            var userName = $(this).closest('tr').find('td:nth-child(2)').text();
            var userEmail = $(this).closest('tr').find('td:nth-child(3)').text();
            var userAuth = $(this).closest('tr').find('td:nth-child(4)').text(); // Adjust if needed

            // Populate details modal
            $('#details-user-id').text(userId);
            $('#details-user-name').text(userName);
            $('#details-user-email').text(userEmail);
            $('#details-user-auth').text(userAuth);

            // Show the details modal
            $('#user-details-modal').modal('show');
        });

        // Delete button click event
        $('.delete-user').click(function() {
            var userId = $(this).data('id');

            // Assuming you have a confirmation logic here, for simplicity, we'll just send a confirmation alert
            if (confirm("Are you sure you want to delete this user?")) {
                // You can make an AJAX call here to delete the user from the server
                $.ajax({
                    type: 'POST',
                    url: '<%=request.getContextPath()%>/AdminPage/deleteUser',
                    data: { 'user-id': userId },
                    success: function(response) {
                        // Handle success response, if any
                        // For example, you might want to remove the row from the table
                        $('tr[data-id="' + userId + '"]').remove();
                    },
                    error: function(xhr, status, error) {
                        // Handle error, if any
                        console.error(error);
                        alert('Error deleting user. Please try again.');
                    }
                });
            }
        });

        // No need for the submit event script here, as the form's action is set to the servlet for handling.
    });
</script>

</body>
</html>
