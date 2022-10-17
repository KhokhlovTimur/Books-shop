<%@ page import="models.User" %>
<%@ page import="services.users.UsersService" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 16.10.2022
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<h1 style="background: #dfd3ff; text-align: center">Hello, admin!</h1>
<table>
    <c:set var="users" value="${requestScope.usersAll}"></c:set>
    <c:forEach var="user" items="${users}">
        <tr>
            <td><c:out value="${user.id}"></c:out></td>
            <td><c:out value="${user.sessionId}"></c:out></td>
            <td><c:out value="${user.role}"></c:out></td>
            <td><c:out value="${user.login}"></c:out></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
