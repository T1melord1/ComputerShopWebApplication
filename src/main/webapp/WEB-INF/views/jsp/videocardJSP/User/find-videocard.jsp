<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Информация о видеокарте</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <style>

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

        .videoMemory {
            padding-left: 35px;
        }

        .fonts-button {
            float: right;
            font-family: "Comic Sans MS", cursive;
            margin-bottom: 20px;
        }

        @media only screen and (max-width: 600px) {
            body {
                font-family: Palatino, sans-serif;
                font-size: 18px;
            }

            table {
                width: 100%;
            }

            th, td {
                padding: 8px;
                font-size: 16px;
            }
        }
    </style>
</head>
<body>
<!-- Кнопка для возврата -->
<form action="/videocards" method="get">
    <button class="fonts-button" type="submit">Вернуться к списку видеокарт</button>
</form>

<!-- Проверка на наличие видеокарт -->
<c:choose>
    <c:when test="${not empty findVideocards}">
        <h2>Список найденных видеокарт:</h2>
        <table>
            <tr>
                <th>№</th>
                <th>Производитель</th>
                <th>Графический процессор</th>
                <th>Видеопамять</th>
                <th>Цвет</th>
                <th>Цена</th>
            </tr>
            <c:forEach var="videocard" items="${findVideocards}" varStatus="number">
                <tr>
                    <td>${number.index + 1}</td>
                    <td>${videocard.manufacturer}</td>
                    <td>${videocard.graphicProcessor}</td>
                    <td class="videoMemory">${videocard.videoMemory}</td>
                    <td>${videocard.color}</td>
                    <td>${videocard.price}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <h2>Видеокарты не найдены.</h2>
    </c:otherwise>
</c:choose>
</body>
</html>
