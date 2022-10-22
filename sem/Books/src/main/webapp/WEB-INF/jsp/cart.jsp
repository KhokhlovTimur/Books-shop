<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>

<form action="/menu">
    <button type="submit" class="menu">В меню</button>
</form>

<c:if test="${sessionScope.user.id ne null}">
    <div class="container">
    <c:set var="books"
           value="${applicationScope.mapService.convertToBookDtoFromCart(sessionScope.user.id)}"></c:set>
    <c:choose>
        <c:when test="${applicationScope.mapService.convertToBookDtoFromCart(sessionScope.user.id).size() > 0}">
            <form method="post">
                <c:forEach var="book" items="${books}">
                    <div class="block">
                        <img class="img" src="">
                        <span class="text price"><c:out value="${book.price} ₽"></c:out></span>
                        <span class="text title"><c:out
                                value="${book.title} | ${book.authorName} ${book.authorSurname}"></c:out></span>
                        <div class="line">
                            <button type="submit" name="deleteFromCart" value="${book.id}" class="delete-button">
                                Удалить
                            </button>
                        </div>
                    </div>
                </c:forEach>
            </form>
            <div id="top"></div>
            </div>
            <div class="buy-line">
                <form action="/cart/confirmation">
                    <span class="all-price">Итого: ${applicationScope.cartSumService.getCartSumByUserId(sessionScope.user.id)}</span>
                    <button type="submit" class="buybutton" name="isTransactValid" value="true">Купить</button>
                </form>

            </div>
        </c:when>
        <c:when test="${applicationScope.mapService.convertToBookDtoFromCart(sessionScope.user.id).size() < 1}">
            <span class="empty">В корзине пусто :(</span>
        </c:when>
    </c:choose>

</c:if>
<script src="${pageContext.request.contextPath}/js/jquery-3.6.1.js"></script>
<style>
    <%@include file="/css/cart.css" %>
</style>

</body>
</html>
<script>
    $(function () {
        $('.container').scroll(function () {
            if ($('.container').scrollTop() !== 0) {
                $('#top').fadeIn();
            } else {
                $('#top').fadeOut();
            }
        });
        $('#top').click(function () {
            $('.container').animate({scrollTop: 0}, 800);
        });
    });
</script>