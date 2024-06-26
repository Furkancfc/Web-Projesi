<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<div class='category-list'>
	<button class='btn' id='add-category-button'>Add Category</button>
	<table>
		<tr>
			<th>Category Id</th>
			<th>Category Name</th>
		</tr>
		<c:forEach items="${categoryService.getCategories()}" var='category'>
			<tr>
				<td>${category.getCategoryId() }</td>
				<td>${category.getName() }</td>
			</tr>
		</c:forEach>
	</table>
	<div id='add-category-form' class='form-popup'>
		<form action="<%=request.getContextPath()%>/AdminPage/Categories"
			method='post'>
			<div class="row">
				<label for='name'>Name : </label> <input type='text' name='name' />
			</div>
			<button class='btn' type='submit'>Add Category</button>
		</form>
		<button class='btn' id='add-category-cancel-button'>Cancel</button>
	</div>
</div>