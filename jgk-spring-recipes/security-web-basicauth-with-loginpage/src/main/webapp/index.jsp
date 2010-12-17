<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<title><%= request.getUserPrincipal().getName() %></title>
</head>
<body>
<h2>Basic Authentication with a Login Page!</h2>

<a href='<%= request.getContextPath() %>/logout'>logout</a>
<button onclick="location.href='<%= request.getContextPath() %>/admin'" 
<c:if test='<%= !request.isUserInRole("ROLE_ADMIN") %>'>disabled='true'</c:if>
>
Administrator</button>
<a href='<%= request.getContextPath() %>/admin'>Administration</a>
</body>
</html>
