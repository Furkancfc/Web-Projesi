<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page
	import="org.springframework.jdbc.datasource.DriverManagerDataSource"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page
	import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.apache.catalina.core.ApplicationContext"%>
<%@page
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.apache.naming.factory.BeanFactory"%>
<%@page
	import="org.springframework.web.context.support.XmlWebApplicationContext"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.context.annotation.Bean"%>
<%@page import="dao.implement.CategoryDaoImpl"%>
<%@page import="webapp.MainDispatcher"%>
<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<div class='container mx-auto'>
	<nav class='navbar border bg-[#fef9c3]'>
		<a class='navbar-brand' href='index'>E-Trade</a>
		<ul class='navbar-nav'>
			<li class='nav-item'><a href="/index/about" class='nav-link'>About
			</a></li>
			<%
			if (session != null && session.getAttribute("userId") == null) {
			%>
			<li class='nav-item'><a class='nav-link' href='login'>Login</a></li>
			<%
			} else {
			%>
			<li class='nav-item'><a class='nav-link' href='logout'>Logout</a></li>
			<%
			}
			%>
			<%
			if (session != null && session.getAttribute("auth") != null && session.getAttribute("auth").equals("admin")) {
			%>
			<li class='nav-item'><a class='nav-link' href="admin">Admin
					Page</a></li>
			<%
			}
			%>
		</ul>

	</nav>
	<nav class='category-navs navbar border'>
		<ul class='navbar-nav'>
			<c:forEach items="${categories}" var="category">
				<li class='nav-item'><a href="${category.getName()}"
					class='nav-link'>${category.getName()}</a></li>
			</c:forEach>
		</ul>
	</nav>
	<div class='content-body'></div>
	<div class='footer'></div>
</div>
