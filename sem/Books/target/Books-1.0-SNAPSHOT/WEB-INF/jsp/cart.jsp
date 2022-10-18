<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${sessionScope.user.id ne null}">

<table style="border: 1px solid red; width: 100%;">
    <form method="post">
        <input type="submit" value="Удалить из корзины">
    <c:set var="books" value="${applicationScope.mapService.convertToBookDto(sessionScope.user.id)}"></c:set>
    <c:forEach var="book" items="${books}">
        <tr style="bottom: 1px;">
            <td><input type="checkbox" name="deleteFromCart" value="${book.id}"></td>
            <td style="border: 1px;"><c:out value="${book.tittle}"></c:out></td>
            <td><c:out value="${book.authorName}"></c:out></td>
            <td><c:out value="${book.authorSurname}"></c:out></td>
            <td><c:out value="${book.price}"></c:out></td>
        </tr>
        </c:forEach>
        </c:if>
    </form>
    <form action="/cart/confirmation">
        <button type="submit" id="buybutton">Купить</button>
    </form>
    <form action="/menu">
        <button type="submit">В меню</button>
    </form>
</table>
</body>
<script>
    // $(document).ready(function () {
    // $("#buybutton").on('click', function (){
    //
    // });
    // })
</script>
<style>
    <%@include file="/css/cart.css" %>
</style>
</html>
