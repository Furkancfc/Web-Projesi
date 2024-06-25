<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    // Liste oluşturulup dolduruluyor
    List<String> last = new ArrayList<String>();
    last.add("A");
    last.add("B");
    last.add("C");
    
    // Liste oturumda saklanıyor
    session.setAttribute("last", last);
%>

<p>
    ${last[0]}
</p>
