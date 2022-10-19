<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Cart</title>
</head>
<c:if test="${sessionScope.user.id ne null}">
    <form action="/menu">
        <button type="submit">В меню</button>
    </form>
    <c:set var="books" value="${applicationScope.mapService.convertToBookDtoFromCart(sessionScope.user.id)}"></c:set>
    <c:choose>
        <c:when test="${applicationScope.mapService.convertToBookDtoFromCart(sessionScope.user.id).size() > 0}">
            <table style="border: 1px solid red; width: 100%;">
                <form method="post">
                    <input type="submit" value="Удалить из корзины">
                    <c:forEach var="book" items="${books}">
                        <tr style="bottom: 1px;">
                            <td><input type="checkbox" name="deleteFromCart" value="${book.id}"></td>
                            <td style="border: 1px;"><c:out value="${book.title}"></c:out></td>
                            <td><c:out value="${book.authorName}"></c:out></td>
                            <td><c:out value="${book.authorSurname}"></c:out></td>
                            <td><c:out value="${book.price}"></c:out></td>
                        </tr>
                    </c:forEach>
                    Итого: ${applicationScope.cartSumService.getCartSumByUserId(sessionScope.user.id)}
                    <%System.out.println(request.getSession().getAttribute("cartSum")); %>
                </form>
            </table>
            <form action="/cart/confirmation">
                <button type="submit" id="buybutton">Купить</button>
            </form>
        </c:when>
        <c:when test="${applicationScope.mapService.convertToBookDtoFromCart(sessionScope.user.id).size() < 1}">
<%--            <form action="/menu">--%>
<%--                <button type="submit">В меню</button>--%>
<%--            </form>--%>
            <div>Корзина пустая</div>
        </c:when>
    </c:choose>
</c:if>
</body>
<script>
</script>
<style>
    <%@include file="/css/cart.css" %>
</style>
</html>
