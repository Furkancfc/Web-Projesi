<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<section id="users">
		<h2>Manage Users</h2>
		<div id="user-list">
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
					</tr>
				</thead>
				<tbody id="user-table-body">
					<c:forEach items="${userService.getUsers()}" var="user">
						<tr>
							<td>${user.getUserId()}</td>
							<td>${user.getUserName()}</td>
							<td>${user.getEmail()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<button type='button' id="add-user">Add User</button>
		<div class="form-popup" id='add-user-form'>
			<form id="user-form" action="/AdminPage/Users">
				<div class="row">
					<label for="user-name">User Name:</label>
					<input type="text" id="user-name" name="user-name" required>
				</div>
				<div class="row">
					<label for="user-email">Email:</label>
					<input type="email" id="user-email" name="user-email" required>
				</div>
				<div class="row">
					<label for="user-password">Password :</label>
					<input type="password" id="user-password" name="user-password" accept="/*" required>
				</div>
				<button type="submit">Add User</button>
				<button type="button" id='cancel-add-user'>Cancel</button>
			</form>
		</div>
	</section>