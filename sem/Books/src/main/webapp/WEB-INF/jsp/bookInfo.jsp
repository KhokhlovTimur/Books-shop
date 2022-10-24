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
    <div id="main-info">

    </div>
</div>

<style>
    <%@include file="/css/bookInfo.css" %>
</style>

</body>
</html>
