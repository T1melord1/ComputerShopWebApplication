<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Videocard List</title>
</head>
<body>
<a href="/videocards/find/manufacturer/NVIDIA" style="margin-right: 20px">Найти видеокарту</a>
<a href="/videocards/add">Добавить видеокарту</a>
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
