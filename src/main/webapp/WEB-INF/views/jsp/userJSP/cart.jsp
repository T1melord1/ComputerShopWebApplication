<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Videocard List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .videoMemory {
            padding-left: 35px; /* Добавляет отступ слева */
        }

        .graphicProcessor {
            padding-left: 50px;
        }
        .number{
            padding-left: 50px;
        }


        .button-order {
            padding: 2px;
            font-family: "Comic Sans MS", cursive;
            margin-top: 10px;
        }

        .button-videocard {
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
<form action="/videocards" method="get">
    <button class="button-videocard" type="submit">Вернуться на главную страницу</button>
</form>
<table>
    <tr>
        <td>Баланс: <b> ${userBalance.balance} ${userBalance.currency}</b></td>
    </tr>
    <tr></tr>
    <c:if test="${not empty errorMessage}"> <p style="color: red;" class="fonts">${errorMessage}</p>
    </c:if><c:if test="${not empty successMessage}"> <p style="color: green;" class="fonts">${successMessage}</p> </c:if>
    <tr>
        <th>Number</th>
        <th>Manufacturer</th>
        <th>Graphic Processor</th>
        <th>Video Memory</th>
        <th>Color</th>
        <th>Price</th>
    </tr>

    <c:set var="totalPrice" value="0"/>
    <c:forEach var="videocard" items="${cart}" varStatus="number">
        <tr>
            <td class="number">${number.index + 1}</td>
            <td>${videocard.manufacturer}</td>
            <td class="graphicProcessor">${videocard.graphicProcessor}</td>
            <td class="videoMemory">${videocard.videoMemory}</td>
            <td>${videocard.color}</td>
            <td>${videocard.price}</td>
            <td>
                <form action="/cart/delete/${videocard.id}" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button class="fonts" type="submit">Удалить</button>
                </form>
            </td>
        </tr>
        <c:set var="totalPrice" value="${totalPrice + videocard.price}"/>
    </c:forEach>
</table>
<b>Общая стоимость: ${totalPrice}</b>
<form action="/cart/order" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button class="button-order" type="submit">Заказать</button>
</form>
</body>
</html>
