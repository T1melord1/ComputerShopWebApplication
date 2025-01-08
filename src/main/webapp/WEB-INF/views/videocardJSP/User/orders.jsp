<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Список заказов</title>
    <style>
        body {
            background-color: ivory;
            font-family: "Comic Sans MS", cursive;
        }

        .vertical-line {
            border-left: 2px solid black;
            height: 100%;
            padding-left: 10px;
            padding-right: 10px;
        }

        .button-right {
            font-family: "Comic Sans MS", cursive;
            float: right;
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
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            document.querySelectorAll('.order-date').forEach(td => {
                if (td.innerText.includes('T')) {
                    td.innerText = td.innerText.replace('T', ' ');
                }
            });
        });
    </script>
</head>
<body>

<form action="/user/profile" method="get">
    <button class="button-right" type="submit">Вернуться в профиль</button>
</form>
<h2>Список заказов</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Order Date</th>
        <th>Total Price</th>
        <th>Videocard Name</th>
    </tr>
    <c:set var="totalPrice" value="0"/>
    <c:forEach var="order" items="${orders}" varStatus="number">
        <tr>
            <td>${number.index + 1}</td>
            <td class="order-date">${order.orderDate}</td>
            <td><b>${order.totalPrice} BYN</b></td>
            <td class="vertical-line">${order.videocards}</td>
        </tr>
        <c:set var="totalPrice" value="${totalPrice + order.totalPrice}"/>

    </c:forEach>
</table>
<b>Общая стоимость всех заказов: ${totalPrice}</b>
</body>
</html>
