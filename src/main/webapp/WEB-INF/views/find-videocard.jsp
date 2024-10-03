<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>Manufacturer</th>
        <th>Graphic Processor</th>
        <th>Video Memory</th>
        <th>Color</th>
        <th>Price</th>
    </tr>

    <tr>
        <td>${findVideocards.id}</td>
        <td>${findVideocards.manufacturer}</td>
        <td>${findVideocards.graphicProcessor}</td>
        <td>${findVideocards.videoMemory}</td>
        <td>${findVideocards.color}</td>
        <td>${findVideocards.price}</td>
    </tr>

<a href="/videocards/">Вернуться к списку видеокарт</a>
</table>
</body>
</html>
