<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <title>Videocard List</title>
    <style>
        .button {
            float: right;
            font-family: "Comic Sans MS", cursive;
        }
        body {
            background-color: ivory;
            font-family: "Comic Sans MS", cursive;
        }
        .fonts {
            font-family: "Comic Sans MS", cursive;
        }
        table {
            width: 80%; /* Устанавливает ширину таблицы */
            text-align: center;
        }
        td {
            border: 1px solid black; /* Границы для ячеек */
        }
    </style>
</head>
<body>
<form action="${pageContext.request.contextPath}/logout" method="post" style="display: inline" class="fonts">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button class="button" type="submit">Выйти из аккаунта</button>
</form>
<div class="table-container">
    <table class="fonts">
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
        </tr>
        <c:forEach var="user" items="${users}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<form method="get" action="videocards/admin">
    <button type="submit" class="fonts">Вернуться к видеокартам</button>
</form>
</body>
</html>
