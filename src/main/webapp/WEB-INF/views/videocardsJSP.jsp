<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Videocard List</title>
</head>
<body>
<form action="/videocards/find/manufacturer" method="get">
    <label for="manufacturer">Введите производителя видеокарты:</label>
    <input type="text" id="manufacturer" name="manufacturer" required>
    <button type="submit">Найти видеокарту</button>
</form>
    <form action="/videocards/add" method="get">
        <button type="submit">Добавить видеокарту</button>
    </form>
<table>
    <tr>
        <th>ID</th>
        <th>Manufacturer</th>
        <th>Graphic Processor</th>
        <th>Video Memory</th>
        <th>Color</th>
        <th>Price</th>
        <th>Actions</th>
    </tr>

    <c:forEach var="videocard" items="${videocards}">
        <tr>
            <td>${videocard.id}</td>
            <td>${videocard.manufacturer}</td>
            <td>${videocard.graphicProcessor}</td>
            <td>${videocard.videoMemory}</td>
            <td>${videocard.color}</td>
            <td>${videocard.price}</td>
            <td>
                <form action="/videocards/delete/${videocard.id}" method="post">
                    <button type="submit">Удалить</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
