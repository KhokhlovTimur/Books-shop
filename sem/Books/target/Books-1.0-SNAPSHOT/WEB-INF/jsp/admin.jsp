<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body class="bb">
<div class="container">
    <div class="block1">

        <form action="/menu">
            <button>В меню</button>
        </form>
        <form method="post" action="/admin/insert">
            <button type="submit" style="background: #f65050;">Добавить книгу/автора</button>
        </form>
        <form method="get" action="/admin/books">
            <button style="background: #f65050;">Информация о книгах</button>
        </form>

    </div>

    <form method="post">
        <input type="submit" value="Подтвердить" class="update-button">
        <table class="table add-table">
            <th>id</th>
            <th>login</th>
            <th>role</th>
            <th>last session id</th>
            <c:set var="users" value="${applicationScope.usersService.findAllUsers()}"></c:set>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.id}"></c:out></td>
                    <td><c:out value="${user.login}"></c:out></td>
                    <td><c:out value="${user.role}"></c:out></td>
                    <td><c:out value="${user.email}"></c:out></td>
                    <td style="border: 0">
                        <c:if test="${user.role eq 'auth' || user.role eq 'admin'}">
                        <select name="chooseRole" class="select">
                            <option value="none">Выберите роль</option>
                            <option value="${user.id}admin">Admin</option>
                        </select>
                    </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </form>

</div>

<div id="toTop" style=" right: 10px;
    width: 100px;">^
</div>
<style>
    <%@include file="/css/admin.css" %>
    <%@include file="/css/commonFormToTop.css"%>
    <%@include file="/css/commonBlockTable.css"%>
</style>

<script src="${pageContext.request.contextPath}/js/jquery-3.6.1.js"></script>
<script src="${pageContext.request.contextPath}/js/scroll.js"></script>

</body>
</html>

