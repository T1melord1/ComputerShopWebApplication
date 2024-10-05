<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Информация о видеокарте</title>
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
        <td>${videocard.videoMemory}</td>
        <td>${videocard.color}</td>
        <td>${videocard.price}</td>
    </tr>
    </c:forEach>
</table>
<a href="/videocards/">Вернуться к списку видеокарт</a>
</body>
</html>
