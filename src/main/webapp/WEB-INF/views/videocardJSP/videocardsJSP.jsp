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
    </style>
</head>
<body>
<form action="/videocards/find/manufacturer" method="get">
    <label for="videocardManufacturer">Выберите производителя видеокарты:</label>
    <select id="videocardManufacturer" name="manufacturer" required>
        <option value="NVIDIA">NVIDIA</option>
        <option value="AMD">AMD</option>
        <option value="Intel">Intel</option>
    </select>
    <button type="submit">Найти видеокарту</button>
</form>
<form action="/videocards/add" method="get">
    <label for="videocardManufacturer">Для добавления видеокарты нажмите кнопку:</label>
    <button type="submit">Добавить видеокарту</button>
</form>
<form style="padding-left: 1500px" action="/cart" method="get">
    <button type="submit">Корзина</button>
</form>
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
            <td class="videoMemory">${videocard.videoMemory}</td>
            <td>${videocard.color}</td>
            <td>${videocard.price}</td>
            <td>
                <form action="/videocards/update/${videocard.id}" method="get">
                    <button type="submit">Обновить</button>
                </form>
            </td>
            <td>
                <form action="/videocards/delete/${videocard.id}" method="post">
                    <button type="submit">Удалить</button>
                </form>
            </td>
            <td>
                <form action="/cart/add/${videocard.id}" method="post">
                    <button type="submit">Добавить в корзину</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
