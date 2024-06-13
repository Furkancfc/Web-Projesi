<%@ page import="webapp.Session"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String pageString = (String) request.getAttribute("page");
String pageCss = (String) request.getAttribute("pageCss");
String pageJs = (String) request.getAttribute("pageJs");
String pageTitle = (String) request.getAttribute("pageTitle");
String pageContent = (String) request.getAttribute("pageContent");
String pageHead = (String) request.getAttribute("pageHead");
%>
<%
if (pageTitle != null)
%>
<title>${pageTitle}</title>
<%
if (pageCss != null)
%>
<link rel="stylesheet" href="${pageCss}">
<%
if (pageJs != null)
%>
<script src="${pageJs}"></script>
