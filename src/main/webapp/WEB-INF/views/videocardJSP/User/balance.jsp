<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Пополнение баланса</title>
    <style>
        body {
            background-color: ivory;
            font-family: "Comic Sans MS", cursive;
        }

        .fonts {
            font-family: "Comic Sans MS", cursive;
        }
        .button-right{
            font-family: "Comic Sans MS", cursive;
            float: right;
        }
    </style>
</head>
<body>

<form action="/user/profile" method="get">
    <button class="button-right" type="submit">Вернуться в профиль</button>
</form>
<h1>Пополнение баланса</h1>
<c:if test="${not empty successMessage}"> <p style="color: green;" class="fonts">${successMessage}</p></c:if>
<form action="/balance/replenish" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <table>
        <tr>
            <td>Сумма пополнения:</td>
            <td><input type="text" name="amount" class="fonts" /></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" class="fonts" value="Пополнить"/></td>
        </tr>
    </table>
</form>
</body>
</html>
