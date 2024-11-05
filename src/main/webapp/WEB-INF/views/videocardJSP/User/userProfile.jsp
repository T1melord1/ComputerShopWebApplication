<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <TITLE>Профиль</TITLE>
    <style>
        body {
            background-color: ivory;
            font-family: "Comic Sans MS", cursive;
        }

        .fonts {
            font-family: "Comic Sans MS", cursive;
        }
        .button {
            float: right;
            font-family: "Comic Sans MS", cursive;
        }

    </style>
</head>
<body>
<form action="/videocards" method="get" class="button">
    <button type="submit" class="fonts">Вернуться на главную страницу</button>
</form>

<table>
    <tr>
        <td>ID: ${userProfile.id}</td>
    </tr>
    <tr>
        <td>Username: ${userProfile.username}</td>
    </tr>
    <tr>
        <td>Email: ${userProfile.email}</td>
    </tr>
</table>
</body>
</html>
