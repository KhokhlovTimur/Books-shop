<%@ page import="models.User" %>
<%@ page import="services.users.UsersService" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<h1 style="background: #dfd3ff; text-align: center">Hello, admin!</h1>
<form method="post">
    <table>
        <td>
            <input type="submit" value="Отправить">
        </td>
        <c:set var="users" value="${applicationScope.usersService.findAllUsers()}"></c:set>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value="${user.id}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><c:out value="${user.role}"></c:out></td>
                <td><c:out value="${user.sessionId}"></c:out></td>
                <td>
                    <c:if test="${user.role eq 'auth' || user.role eq 'admin'}">
                    <select name="chooseRole">
                        <option value="none">Выберите роль</option>
                        <option value="${user.id}admin">Admin</option>
                    </select></td>
                </c:if>
            </tr>

        </c:forEach>
    </table>
</form>
<form action="/menu"><button>В меню</button></form>
</body>
</html>
