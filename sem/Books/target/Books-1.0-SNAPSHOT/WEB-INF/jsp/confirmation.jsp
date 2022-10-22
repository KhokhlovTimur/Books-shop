<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 18.10.2022
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirmation</title>
</head>
<body>
<form method="post">
    <div class="form">
    <input placeholder="confirm password" type="text" name="confirmPassword">
    </div>
    <button type="submit" class="conf">Подтвердить</button>
    <style>
        <%@include file="/css/confirm.css" %>
    </style>
</form>
</body>
</html>
