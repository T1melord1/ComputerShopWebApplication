<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <TITLE>Профиль</TITLE>
    <style>
        body {
            background-color: ivory;
            font-family: "Comic Sans MS", cursive;
        }

        .fonts {
            font-family: "Comic Sans MS", cursive;
        }

        .button {
            float: right;
            font-family: "Comic Sans MS", cursive;
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
<form action="/videocards" method="get" class="button">
    <button type="submit" class="fonts">Вернуться на главную страницу</button>
</form>
<table>
    <tr>
        <td>Username:<b> ${userProfile.username} </b></td>
    </tr>
    <tr>
        <td>
            Баланс:<b> ${userBalance.balance} ${userBalance.currency}</b>
            <form action="/balance/replenish" method="get" style="display: inline;">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="fonts">Пополнить</button>
            </form>
        </td>
    </tr>
    <tr>
        <td>Email:<b> ${userProfile.email} </b>
            <c:if test="${userProfile.confirmationToken == null}">
                - подтверждён
            </c:if>
            <c:if test="${userProfile.confirmationToken != null}">
                - не подтверждён
            </c:if>
        </td>
    </tr>
    <tr>
        <td>
            <form action="/password/change/{username}" method="get">
                <button type="submit" class="fonts">Сменить пароль</button>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
