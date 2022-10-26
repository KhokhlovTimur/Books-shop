<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
</head>
<body>

<c:choose>
    <c:when test="${sessionScope.role ne 'noAuth'}">
        <c:set var="userRole"
               value="${applicationScope.usersService.findUserById(sessionScope.user.id).get().role}"></c:set>
    </c:when>
    <c:otherwise>
        <c:set var="userRole" value="noAuth"></c:set>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${requestScope.poisk ne null}">
        <c:set var="books" value="${applicationScope.searchService.findBook(requestScope.poisk)}"></c:set>
    </c:when>
    <c:otherwise>
        <c:set var="books" value="${applicationScope.sortService.sortBy(requestScope.sortBy)}"></c:set>
    </c:otherwise>
</c:choose>


<div class="block1">
    <c:choose>
        <c:when test="${userRole eq 'auth' || userRole eq 'admin'}">
            <form action="/cart">
                <button>Корзина</button>
            </form>
            <form action="/profile">
                <button>Профиль</button>
            </form>
        </c:when>
        <c:when test="${userRole eq 'noAuth'}">
            <form action="/login">
                <button>Войти</button>
            </form>
        </c:when>
    </c:choose>
    <c:if test="${userRole eq 'admin'}">
        <form action="/admin">
            <button style="background: #f65050;">Управление</button>
        </form>
    </c:if>
    <form>
        <button type="submit" class="invisible" name="poisk"></button>
            <input  type="text" placeholder="Поиск..." name="search" class="search">
    </form>

    <form action="/menu">
        <select name="sortBy" class="sort-selector">
            <option value="none">Сортировать по</option>
            <option value="priceAscend">возрастанию цены</option>
            <option value="priceDescend">убыванию цены</option>
            <option value="az">от а до я</option>
            <option value="za">от я до а</option>
        </select>
        <input class="ok" type="submit" value="ок" name="isSorted">
    </form>
</div>
<div class="container">
    <c:forEach var="book" items="${books}">
    <div>
        <form action="/menu/bookInfo">
            <button type="submit" id="img-button" name="bookId" value="${book.id}"><img class="bookimg"
                                                                                        src="${pageContext.request.contextPath}/images/books/${book.id}">
            </button>
        </form>
        <p class="price"><c:out value="${book.price}₽"></c:out></p>
        <p class="info"><c:out
                value="${book.title} | ${book.authorSurname} ${book.authorName} | ${book.yearOfPublication}"></c:out>
        </p>
        <c:choose>
        <c:when test="${userRole eq 'noAuth'}">
        <form method="get" action="${pageContext.request.contextPath}/login">
            </c:when>
            <c:when test="${userRole ne 'noAuth'}">
            <form method="post">
                </c:when>
                </c:choose>
                <button class="infoibutton" type="submit" name="toCart" value="${book.id}">В корзину</button>
            </form>
    </div>
    </c:forEach>

</body>
<style>
    <%@include file="/css/menu.css" %>
</style>
</html>
