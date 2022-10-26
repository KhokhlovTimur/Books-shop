<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
</head>
<body>

<c:set var="book"
       value="${applicationScope.mapService.convertBookToBookDto(applicationScope.booksService.findBookById(requestScope.bookId).get())}"></c:set>
<div id="container">
    <div id="line">
        <h1><c:out value="${book.title} | ${book.authorName} ${book.authorSurname}"></c:out></h1>
    </div>
    <img src="${pageContext.request.contextPath}/images/books/${book.id}" id="img">

    </form>
    <form action="/menu">
        <button type="submit" class="to-menu">В меню</button>
    </form>
    <div id="main-info">
        <p>
            <span class="span-title text">Автор </span>
            <span class="span-info text">${book.authorName} ${book.authorSurname}</span>
        </p>
        <p>
            <span class="span-title text">Год написания</span>
            <span class="span-info text">${book.yearOfPublication}</span>
        </p>
        <p>
            <span class="span-title text">Цена</span>
            <span class="span-info text">${book.price}₽</span>
        </p>
        <p class="span-title text">Описание:</p>
        <div class="descript">${book.description}</div>
    </div>

</div>

<style>
    <%@include file="/css/bookInfo.css" %>
</style>

</body>
</html>
