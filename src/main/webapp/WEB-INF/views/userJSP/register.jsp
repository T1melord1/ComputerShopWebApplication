<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
    <style>
        body {
            background-color: ivory;
            font-family: "Comic Sans MS", cursive;
        }

        .fonts {
            font-family: "Comic Sans MS", cursive;
        }

        .fonts-right {
            float: right;
            font-family: "Comic Sans MS", cursive;
        }

        .alert {
            color: red;
            margin-bottom: 20px;
        }

        table {
            margin-top: 20px;
        }

    </style>
</head>
<body>
<form action="/user/login" method="get">
    <button type="submit" class="fonts-right">Вернуться на главную</button>
</form>
<h2>Register</h2>
<c:if test="${not empty message}">
    <div class="alert">${message}</div>
</c:if>
<form action="${pageContext.request.contextPath}/register" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <table>
        <tr>
            <td><label for="username">Username:</label></td>
            <td><input type="text" id="username" name="username" class="fonts" required></td>
        </tr>
        <tr>
            <td><label for="email">Email:</label></td>
            <td><input type="email" id="email" name="email" class="fonts" required></td>
        </tr>
        <tr>
            <td><label for="password">Password:</label></td>
            <td><input type="password" id="password" name="password" class="fonts" required></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" class="fonts" value="Регистрация"/></td>
        </tr>
    </table>
</form>
<p>Уже есть аккаунт? <a class="fonts" href="${pageContext.request.contextPath}/login">Логин</a></p>
</body>
</html>
