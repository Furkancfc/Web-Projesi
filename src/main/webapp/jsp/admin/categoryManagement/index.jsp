<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<div class='categories'>
	<ul>
		<c:forEach items="categories" var="category">
			<li>${category.getName()}</li>
		</c:forEach>
	</ul>

</div>
<form class='form' method="post" action="?addCategory">
	<label for="name">Name : </label> <input type="text" name="name" />
	<button type="submit">Add Category</button>
</form>