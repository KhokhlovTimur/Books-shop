<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>

</head>
<body>

<div class="container">

    <div class="block1">
        <form action="/menu">
            <button>В меню</button>
        </form>
        <form method="post" action="/admin/insert">
            <button type="submit" style="background: #f65050;">Добавить книгу/Автора</button>
        </form>
        <form method="get" action="/admin">
            <button type="submit" style="background: #f65050;">Назад</button>
        </form>
        <button type="button" class="hide-form admin-butt" onclick="clickHide()" style="background: #f65050;">Скрыть форму</button>
        <button type="button" class="show-form admin-butt" onclick="clickShow()" style="background: #f65050;">Показать форму
        </button>

        <form>
            <button type="submit" class="invisible" name="poiskBook"></button>
            <input type="text" placeholder="Поиск..." name="searchBook" class="search">
        </form>

    </div>

    <form class="form upd" method="post" action="${pageContext.request.contextPath}/admin/books"
          enctype="multipart/form-data">
        <h3>Изменить информацию о книге</h3>
        <input type="text" name="id" placeholder="id" class="w100" id="bookUpdateId" required>
        <input type="text" name="title" placeholder="title" class="w100">
        <input type="text" name="authorId" placeholder="authorId" id="authorUpdateId">
        <input type="text" name="year" placeholder="year of publication" id="updateYear">
        <input type="text" name="price" placeholder="price" id="updatePrice">
        <input type="file" name="image" placeholder="image" class="file">
        <textarea name="descriptionBook" class="text-area"></textarea>
        <button type="submit" class="insertbutton" id="saveBook">Сохранить</button>
    </form>

    <table class="table fx-table">
        <thead>
        <tr>
            <th>Sort by -></th>
            <th>
                <form>
                    <button type="submit" class="sort-button" name="sortBook" value="byId">book id</button>
                </form>
            </th>
            <th>
                <form>
                    <button type="submit" class="sort-button" name="sortBook" value="byTitle">title</button>
                </form>
            </th>
            <th>
                <form>
                    <button type="submit" class="sort-button" name="sortBook" value="byName">author name</button>
                </form>
            </th>
            <th>
                <form>
                    <button type="submit" class="sort-button" name="sortBook" value="bySurname">surname</button>
                </form>
            </th>
            <th>
                <form>
                    <button type="submit" class="sort-button" name="sortBook" value="byPrice">price</button>
                </form>
            </th>
            <th>
                <form>
                    <button type="submit" class="sort-button" name="sortBook" value="byYear">year of publ</button>
                </form>
            </th>
        </tr>
        </thead>
        <tbody>

        <c:choose>
            <c:when test="${requestScope.poiskBook ne null}">
                <c:set var="books" value="${applicationScope.searchService.findBook(requestScope.poiskBook)}"></c:set>
            </c:when>
            <c:when test="${requestScope.sortBook ne null}">
                <c:set var="books"
                       value="${applicationScope.sortService.sortBooksAdminPage(requestScope.sortBook)}"></c:set>
            </c:when>
            <c:otherwise>
                <c:set var="books"
                       value="${applicationScope.mapService.convertAllBooksToBooksDtoFromBooksSortById()}"></c:set>
            </c:otherwise>
        </c:choose>

        <form method="post" action="/admin/books">
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>
                        <label class="label">
                            <input type="checkbox" name="checkBookId" value="${book.id}" class="checkbox">
                        </label>
                    </td>
                    <td><c:out value="${book.id}"></c:out></td>
                    <td><c:out value="${book.title}"></c:out></td>
                    <td><c:out value="${book.authorName}"></c:out></td>
                    <td><c:out value="${book.authorSurname}"></c:out></td>
                    <td><c:out value="${book.price}"></c:out></td>
                    <td><c:out value="${book.yearOfPublication}"></c:out></td>
                </tr>
            </c:forEach>
            <input value="Удалить" type="submit" class="delete-button">
        </form>
        </tbody>
    </table>
    <div id="toTop">Наверх</div>
</div>

<script src="${pageContext.request.contextPath}/js/jquery-3.6.1.js"></script>
<script src="${pageContext.request.contextPath}/js/books.js"></script>

<style>
    <%@include file="/css/commonBlockTable.css" %>
    <%@include file="/css/commonFormToTop.css"%>
    <%@include file="/css/books.css"%>
</style>

</body>
</html>
