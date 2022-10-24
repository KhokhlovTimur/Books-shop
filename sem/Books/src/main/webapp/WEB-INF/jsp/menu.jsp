
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
            <button style="background: #f65050;">Управление</button>
        </form>
    </c:if>
</div>
<c:set var="books" value="${applicationScope.mapService.convertAllBooksToBooksDtoFromBooks()}"></c:set>
    <div class="container">
        <c:forEach var="book" items="${books}">
        <div>
            <form action="/menu/bookInfo"><button type="submit" id="img-button" name="bookId" value="${book.id}"><img class="bookimg" src="${pageContext.request.contextPath}/images/books/${book.id}"></button></form>
            <p class="price"><c:out value="${book.price}₽"></c:out></p>
            <p class="info"><c:out value="${book.title} | ${book.authorSurname} ${book.authorName} | ${book.yearOfPublication}"></c:out>
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
