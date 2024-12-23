`<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <title>Videocard List</title>
    <style>
        .button {
            float: right;
            font-family: "Comic Sans MS", cursive;
        }

        body {
            background-color: ivory;
            font-family: "Comic Sans MS", cursive;
        }

        .fonts {
            font-family: "Comic Sans MS", cursive;
        }

        table {
            width: 80%; /* Устанавливает ширину таблицы */
            text-align: center;
        }

        td {
            border: 1px solid black; /* Границы для ячеек */
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
<form action="${pageContext.request.contextPath}/logout" method="post" style="display: inline" class="fonts">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button class="button" type="submit">Выйти из аккаунта</button>
</form>
<div class="table-container">
    <table class="fonts">
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Confirmation Token</th>
            <th>Reset Token</th>
        </tr>
        <c:forEach var="user" items="${users}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.confirmationToken}</td>
                <td>${user.resetToken}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<form method="get" action="videocards/admin">
    <button type="submit" class="fonts">Вернуться к видеокартам</button>
</form>
</body>
</html>
`