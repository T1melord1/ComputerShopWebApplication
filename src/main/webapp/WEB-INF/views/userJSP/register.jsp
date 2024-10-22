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
    </style>
</head>
<body>
<h2>Register</h2>
<form action="${pageContext.request.contextPath}/register" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" class="fonts" required><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" class="fonts" required><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" class="fonts" required><br>

    <button type="submit" class="fonts">Регистрация</button>
</form>
<p>Уже есть аккаунт? <a class="fonts" href="${pageContext.request.contextPath}/login">Логин</a></p>
</body>
</html>
