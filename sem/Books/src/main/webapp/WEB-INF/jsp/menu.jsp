<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 13.10.2022
  Time: 8:19
  To change this template use File | Settings | File Templates.
--%>
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
            <form action="/profile">
                <button>Profile</button>
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
            <button style="left: 0">Управление</button>
        </form>
    </c:if>

</div>
</body>
<style>
    <%@include file="/css/menu.css" %>
</style>
</html>
