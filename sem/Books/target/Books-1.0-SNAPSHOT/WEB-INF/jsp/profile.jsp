<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
</head>
<body>
<h1 style="margin: 10px 10px;">Личный кабинет ${sessionScope.user.login}</h1>

<span class="balance">
            Баланс: ${applicationScope.usersService.findUserById(sessionScope.user.id).get().balance}₽
        </span>

<div class="container2">
    <div class="block1">
        <form action="/login" method="get">
            <button name="button" value="exit">Выйти</button>
        </form>
        <form action="/menu" method="get">
            <button name="button">В меню</button>
        </form>
        <form action="/cart" method="get">
            <button type="submit">Корзина</button>
        </form>
        <button name="button" type="button" class="history">История покупок</button>
        <button name="button" type="button" class="historyhide">Скрыть историю</button>

        <form action="/profile" method="post">
            <input type="text" name="setBalance" class="balance-input" placeholder="пополнить">
            <button type="submit" style="width: 0px; opacity: 0; position:absolute;" class="hh"></button>
            <button type="button" style=" left: 165px" class="subm">Скрыть</button>
            <button type="button" class="balance-button">Пополнить</button>
        </form>

        <c:if test="${sessionScope.role eq 'admin'}">
            <form action="/admin">
                <button style="background: #f65050; left: 200px">Управление</button>
            </form>
        </c:if>
    </div>


    <div class="block2">

        <c:set var="orders"
               value="${applicationScope.mapService.convertAllToOrderBookDto(sessionScope.user.id)}"></c:set>
        <c:forEach var="order" items="${orders}">
            <div class="each-order">
        <span id="order-id">
                <c:out value="ID заказа: ${order.orderId}"></c:out>
            </span>
                <p>
            <span id="price">
                <c:out value="Общая цена: ${order.priceAll}₽"></c:out>
                    </span>
                <p>
            <span id="buy-book">
                <em><c:out value="Купленные книги: "></c:out></em>
                <c:set var="books" value="${order.books}"></c:set>
                <c:forEach var="book" items="${books}"></span>
                <p><span id="book">
                · <c:out value=" ${book.title} | ${book.authorName} ${book.authorSurname} | ${book.price}₽"></c:out>
                    </c:forEach>
            </div>
        </c:forEach>
    </div>

</div>

<script src="${pageContext.request.contextPath}/js/jquery-3.6.1.js"></script>
<script src="${pageContext.request.contextPath}/js/profile.js"></script>

<style>
    <%@include file="/css/menu.css" %>
    <%@include file="/css/profile.css" %>
</style>
</body>
</html>

