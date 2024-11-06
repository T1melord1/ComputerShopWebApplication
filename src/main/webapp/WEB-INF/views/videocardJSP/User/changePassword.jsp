<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Смена пароля</title>
    <style>
        body {
            background-color: ivory;
            font-family: "Comic Sans MS", cursive;
        }

        .fonts {
            font-family: "Comic Sans MS", cursive;
        }
    </style>
</head>
<body>
<p>Введите новый пароль</p>
<% if (request.getAttribute("errorMessage") != null) { %>
<p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
<% } %>
<form action="/password/change/${userProfile.username}" method="post">

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <table>
        <tr>
            <td>Старый пароль:</td>
            <td><input type="password" id="oldPassword" name="oldPassword" required></td>
        </tr>
        <td>Новый пароль:</td>
        <td><input type="password" id="newPassword" name="newPassword" required></td>
        <tr>
            <td colspan="2"><input type="submit" class="fonts" value="Изменить пароль"/></td>
        </tr>
    </table>
</form>
</body>
</html>
