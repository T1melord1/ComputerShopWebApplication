<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Доступ запрещен</title>
    <style>
        body {
            font-family: "Comic Sans MS", cursive;
            background-color: ivory;
            text-align: center;
            margin-top: 50px;
        }
        h1 {
            color: red;
        }
    </style>
</head>
<body>
<h1>Доступ запрещен</h1>
<p>У вас нет прав доступа к этой странице.</p>
<a href="<c:url value='/videocards' />">Вернуться на главную</a>
</body>
</html>
