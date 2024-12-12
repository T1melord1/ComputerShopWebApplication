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
        <td>Username:<b> ${userProfile.username} </b></td>
    </tr> <tr>
        <td>Balance:<b> ${userBalance.balance} </b></td>
    </tr>
    <tr>
        <td>Email:<b> ${userProfile.email} </b>
            <c:if test="${userProfile.confirmationToken == null}">
                - подтверждён
            </c:if>
            <c:if test="${userProfile.confirmationToken != null}">
                - не подтверждён
            </c:if>

        </td>

    </tr>
    <tr>
        <td>
            <form action="/password/change/{username}" method="get">
                <button type="submit" class="fonts">Сменить пароль</button>
            </form>
         </td>
    </tr>
</table>
</body>
</html>
