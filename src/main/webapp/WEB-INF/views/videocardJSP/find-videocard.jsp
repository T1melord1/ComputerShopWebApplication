<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Информация о видеокарте</title>
    <style>
        body {
            background-color: ivory;
            font-family: "Comic Sans MS", cursive;
        }

        .fonts {
            font-family: "Comic Sans MS", cursive;
        }
        .videoMemory {
            padding-left: 35px; /* Добавляет отступ слева */
        }

    </style>
</head>
<body>
<h2>Найденная видеокарта</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Manufacturer</th>
        <th>Graphic Processor</th>
        <th>Video Memory</th>
        <th>Color</th>
        <th>Price</th>
    </tr>
    <c:forEach var="videocard" items="${findVideocards}">
        <tr>
            <td>${videocard.id}</td>
            <td>${videocard.manufacturer}</td>
            <td>${videocard.graphicProcessor}</td>
            <td class="videoMemory">${videocard.videoMemory}</td>
            <td>${videocard.color}</td>
            <td>${videocard.price}</td>
        </tr>
    </c:forEach>
</table>
<form action="/videocards/" method="get">
    <button class="fonts" type="submit">Вернуться к списку видеокарт</button>
</form>
</body>
</html>
