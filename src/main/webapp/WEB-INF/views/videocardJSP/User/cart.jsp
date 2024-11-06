<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Videocard List</title>
    <style>
        .videoMemory {
            padding-left: 35px; /* Добавляет отступ слева */
        }

        .graphicProcessor {
            padding-left: 50px;
        }

        body {
            background-color: ivory;
            font-family: "Comic Sans MS", cursive;
        }

        .fonts {
            float: right;
            font-family: "Comic Sans MS", cursive;
        }
    </style>
</head>

<body>
<form action="/videocards" method="get">
    <button class="fonts" type="submit">Вернуться на главную страницу</button>
</form>
<table>
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
            <td>${number.index + 1}</td>
            <td>${videocard.manufacturer}</td>
            <td class="graphicProcessor">${videocard.graphicProcessor}</td>
            <td class="videoMemory">${videocard.videoMemory}</td>
            <td>${videocard.color}</td>
            <td>${videocard.price}</td>
            <td>
                <form action="/cart/delete/${videocard.id}" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <button class="fonts" type="submit">Удалить</button>
                </form>
            </td>
        </tr>
        <c:set var="totalPrice" value="${totalPrice + videocard.price}"/>
    </c:forEach>
</table>
<b>Общая стоимость: ${totalPrice}</b>
</body>
</html>
