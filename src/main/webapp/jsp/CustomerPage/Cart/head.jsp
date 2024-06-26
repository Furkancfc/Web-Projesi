
<title>Shopping Cart</title>
<%
if (session == null || session.getAttribute("userId") == null) {
	response.sendRedirect(request.getContextPath() + "/login");
}
%>
