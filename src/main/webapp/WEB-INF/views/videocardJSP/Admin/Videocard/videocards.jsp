<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <title>Videocard List</title>
    <style>
        .nav-links {
            position: relative; /* Относительное позиционирование для навигационных ссылок */
            margin-bottom: 20px; /* Отступ между ссылками и формой */
        }

        .logout-button {
            float: right;
            font-family: "Comic Sans MS", cursive;
        }

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
            font-family: "Comic Sans MS", cursive;
        }

        .button-container {
            justify-content: center;
            align-items: center;
        }

        .button-container button {
            width: 100%;
            padding: 5px;
            font-family: "Comic Sans MS", cursive;
        }
    </style>
</head>
<body>
<div class="nav-links">
    <form action="${pageContext.request.contextPath}/videocards/admin/add" method="get" style="display: inline;">
        <button class="fonts" type="submit">Добавить видеокарту</button>
    </form>
    <form action="${pageContext.request.contextPath}/logout" method="post" style="display: inline" class="fonts">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button class="logout-button" type="submit">Выйти из аккаунта</button>
    </form>
</div>
<table>
    <tr>
        <th>ID</th>
        <th>Manufacturer</th>
        <th>Graphic Processor</th>
        <th>Video Memory</th>
        <th>Color</th>
        <th>Price</th>
    </tr>
    <c:forEach var="videocard" items="${videocards}">
        <tr>
            <td>${videocard.id}</td>
            <td>${videocard.manufacturer}</td>
            <td class="graphicProcessor">${videocard.graphicProcessor}</td>
            <td class="videoMemory">${videocard.videoMemory} GB</td>
            <td>${videocard.color}</td>
            <td>${videocard.price}</td>
            <td>
                <form action="/videocards/admin/update/${videocard.id}" method="get">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="button-container">
                    <button class="fonts" type="submit">Обновить</button>
                    </div>
                </form>
            </td>
            <td>
                <form action="/videocards/admin/delete/${videocard.id}" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="button-container">
                    <button class="fonts" type="submit">Удалить</button>
                    </div>
                </form>
            </td>
        </tr>
    </c:forEach>
    <form action="/users" method="get">
        <button class="logout-button" type="submit">Пользователи</button>
    </form>
</table>
</body>
</html>
