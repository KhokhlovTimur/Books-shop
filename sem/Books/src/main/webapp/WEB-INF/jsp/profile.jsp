<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
</head>
<body>
<h1 style="margin: 10px 10px;">Личный кабинет ${sessionScope.user.login}</h1>
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
        <c:if test="${sessionScope.role eq 'admin'}">
            <form action="/admin">
                <button style="background: #f65050;">Управление</button>
            </form>
        </c:if>

    </div>
    <div class="block2">
        <c:set var="orders"
               value="${applicationScope.mapService.convertAllToOrderBookDto(sessionScope.user.id)}"></c:set>
        <c:forEach var="order" items="${orders}">
        <p>
                <c:out value="ID заказа: ${order.orderId}"></c:out>
            <br>
                <c:out value="Общая стоимость: ${order.priceAll}"></c:out>
            <br>
                <c:out value="Купленные книги: "></c:out>
                <c:set var="books" value="${order.books}"></c:set>
            <c:forEach var="book" items="${books}">
            <p>
                <c:out value="Название: ${book.title}, "></c:out>
                <c:out value="автор: ${book.authorName} ${book.authorSurname}, "></c:out>
                <c:out value="цена: ${book.price}."></c:out>
            </c:forEach>
            </c:forEach>
    </div>
</div>
<%--<script src="${pageContext.request.contextPath}/js/insert.js"></script>--%>
<script src="${pageContext.request.contextPath}/js/jquery-3.6.1.js"></script>
<style><%@include file="/css/menu.css" %>
<%@include file="/css/profile.css" %>
</style>
</body>
</html>
<script>
    $('.historyhide').hide();
    $('.block2').hide();
    $('.history').click(function () {
        $('.history').hide();
        $('.block2').show(1500);
        $('.historyhide').show().click(function () {
            $('.block2').hide(1500);
            $('.historyhide').hide();
            $('.history').show();
        })
    })
</script>
