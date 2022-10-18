<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 13.10.2022
  Time: 8:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
</head>
<body>
<div class="block1">
    <c:choose>
        <c:when test="${sessionScope.role eq 'auth' || sessionScope.role eq 'admin'}">
            <form action="/cart">
                <button>Корзина</button>
            </form>
            <form action="/profile">
                <button>Профиль</button>
            </form>
        </c:when>
        <c:when test="${sessionScope.role eq 'noAuth'}">
            <form action="/login">
                <button>Войти</button>
            </form>
        </c:when>
    </c:choose>

    <c:if test="${sessionScope.role eq 'admin'}">
        <form action="/admin">
            <button style="left: 0">Управление</button>
        </form>
    </c:if>
</div>
<c:set var="books" value="${applicationScope.mapService.convertAllAuthorsToAuthorDto()}"></c:set>

    <div class="container">
        <c:forEach var="book" items="${books}">
        <div>
            <img class="bookimg" src="/resources/strelok.jpg">
            <p class="price"><c:out value="${book.price}₽"></c:out></p>
            <p class="info"><c:out value="${book.tittle} | ${book.authorSurname} ${book.authorName} | ${book.yearOfPublication}"></c:out>
            </p>
            <form method="post">
            <button class="infoibutton" type="submit" name="toCart" value="${book.id}">В корзину</button>
            </form>
        </div>
        </c:forEach>

</body>
<style>
    <%@include file="/css/menu.css" %>
</style>
</html>
