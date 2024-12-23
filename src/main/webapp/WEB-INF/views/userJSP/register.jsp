<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
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

        @media only screen and (max-width: 600px) {
            body {
                font-family: Palatino, sans-serif; /* Шрифт Palatino для мобильных устройств */
                font-size: 18px; /* Увеличенный размер шрифта для мобильных устройств */
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            th, td {
                padding: 10px;
                text-align: center;
                font-size: 16px; /* Увеличенный размер шрифта для таблицы */
            }

            .button-container button {
                width: 100%;
                font-size: 16px; /* Увеличенный размер шрифта для кнопок */
                font-family: Palatino, sans-serif; /* Шрифт Palatino для мобильных устройств */
            }

            .nav-links form, .nav-links a {
                width: 100%;
                text-align: center;
                margin-bottom: 10px;
            }

            .nav-links form select, .nav-links form button, .nav-links .logout-button {
                width: 100%;
                font-size: 16px; /* Увеличенный размер шрифта для элементов формы */
                font-family: Palatino, sans-serif; /* Шрифт Palatino для мобильных устройств */
            }

            label {
                font-size: 16px; /* Увеличенный размер шрифта для меток */
                font-family: Palatino, sans-serif; /* Шрифт Palatino для меток на мобильных устройствах */
            }
        }

    </style>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="get">
    <button type="submit" class="fonts-right">Вернуться на главную</button>
</form>
<h2>Регистрация</h2>
<c:if test="${not empty message}">
    <div class="alert">${message}</div>
</c:if>
<form action="${pageContext.request.contextPath}/register" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <table>
        <tr>
            <td><label for="username">Имя пользователя:</label></td>
            <td><input type="text" id="username" name="username" class="fonts" placeholder="Username" required></td>
        </tr>
        <tr>
            <td><label for="email">Почта:</label></td>
            <td><input type="email" id="email" name="email" class="fonts" placeholder="Email" required></td>
        </tr>
        <tr>
            <td><label for="password">Пароль:</label></td>
            <td><input type="password" id="password" name="password" class="fonts" placeholder="Password" required></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" class="fonts" value="Регистрация"/></td>
        </tr>
    </table>
</form>
<p>Уже есть аккаунт? <a class="fonts" href="${pageContext.request.contextPath}/login">Логин</a></p>
</body>
</html>
