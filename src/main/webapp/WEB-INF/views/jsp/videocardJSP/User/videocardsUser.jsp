<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

    <title>Videocard List</title>
    <style>
        /* Общие стили */

        .cart-icon {
            float: right;
            font-size: 36px;
            color: cornflowerblue;
            cursor: pointer;
            display: inline-block;
            transition: color 0.3s;
        }

        .cart-icon:hover {
            color: #0056b3;
        }

        .nav-links {
            position: relative;
            margin-bottom: 20px;
        }

        .logout-button {
            font-family: "Comic Sans MS", cursive;
        }

        /* Таблица */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f4f4f4;
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .button-container {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .button-container button {
            width: 100%;
            padding: 5px;
            font-family: "Comic Sans MS", cursive;
        }

        /* Адаптивный дизайн */
        @media only screen and (max-width: 600px) {
            body {
                font-family: Palatino, sans-serif; /* Шрифт Palatino для мобильных устройств */
                font-size: 18px; /* Увеличенный размер шрифта для мобильных устройств */
            }

            .nav-links {
                display: flex;
                flex-direction: column;
                align-items: center;
                margin-bottom: 10px;
            }

            .cart-icon {
                float: none;
                font-size: 30px; /* Увеличенный размер значка корзины */
                margin-bottom: 10px;
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

            .button-container {
                width: 100%;
                margin-bottom: 10px;
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

<div class="nav-links">
    <form action="${pageContext.request.contextPath}/videocards/find/manufacturer" method="get"
          style="display: inline;">
        <label for="videocardManufacturer">Выберите производителя видеокарты:</label>
        <select id="videocardManufacturer" name="manufacturer" required>
            <option class="fonts" value="NVIDIA">NVIDIA</option>
            <option class="fonts" value="AMD">AMD</option>
            <option class="fonts" value="Intel">Intel</option>
            <option class="fonts" value="Apple">Apple</option>
        </select>
        <button class="fonts" type="submit">Найти видеокарту</button>
    </form>
    <form action="${pageContext.request.contextPath}/logout" method="post" style="display: inline" class="fonts">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button class="logout-button" type="submit">Выйти из аккаунта</button>
    </form>

    <a href="${pageContext.request.contextPath}/cart" class="cart-icon" style="display: inline">
        <i class="fas fa-shopping-cart"></i>
    </a>
    <a href="${pageContext.request.contextPath}/user/profile" class="cart-icon" data-username="username"
       style="display: inline">
        <i class="fas fa-user"></i>
    </a>
    <c:if test="${not empty message}">
        <p style="${messageType == 'success' ? 'color: green;' : 'color: red;'}" class="fonts">${message}</p>
    </c:if>
</div>
<table>
    <tr>
        <th>Number</th>
        <th>Manufacturer</th>
        <th>Graphic Processor</th>
        <th>Video Memory</th>
        <th>Color</th>
        <th>Price</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="videocard" items="${videocards}" varStatus="status">
        <tr>
            <td class="number">${status.index + 1}</td>
            <td class="manufacturer">${videocard.manufacturer}</td>
            <td class="graphicProcessor">${videocard.graphicProcessor}</td>
            <td class="videoMemory">${videocard.videoMemory} GB</td>
            <td class="color">${videocard.color}</td>
            <td class="price">${videocard.price}</td>
            <td>
                <form action="/cart/add/${videocard.id}" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="button-container">
                        <button class="fonts" type="submit">Добавить в корзину</button>
                    </div>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
