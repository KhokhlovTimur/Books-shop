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
<div class="container">
    <form method="post">
        <table>
            <td>
                <input type="submit" value="Подтвердить">
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
</div>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <td></td>
            <th>Book id</th>
            <th>title</th>
            <th>author name</th>
            <th>author surname</th>
            <th>price</th>
            <th>year of publ</th>
        </tr>

        </thead>
        <tbody>
        <c:set var="books" value="${applicationScope.mapService.convertAllBooksToBooksDtoFromBooks()}"></c:set>
        <c:forEach var="book" items="${books}">
            <tr>
                <td><input type="checkbox" name="checkBookId" value="${book.id}"></td>
                <td><c:out value="${book.id}"></c:out></td>
                <td><c:out value="${book.title}"></c:out></td>
                <td><c:out value="${book.authorName}"></c:out></td>
                <td><c:out value="${book.authorSurname}"></c:out></td>
                <td><c:out value="${book.price}"></c:out></td>
                    <%--                <td><c:out value="${book.authorYearOfBirth}"></c:out></td>--%>
                <td><c:out value="${book.yearOfPublication}"></c:out></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<form action="/menu">
    <button>В меню</button>
</form>
<style>
    <%@include file="/css/admin.css" %>
</style>
</body>
</html>
