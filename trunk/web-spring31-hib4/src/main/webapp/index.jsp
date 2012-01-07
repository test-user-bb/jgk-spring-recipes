<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
ServletContext sc = pageContext.getServletContext();
ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sc);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello still there World!</h1>
    </body>
    
    Application Context:<%= context %>
    <br/>
    <br/>
    <c:set var="anExpression" value="JED"/>
    <c:out value="${anExpression}"/>
</html>
