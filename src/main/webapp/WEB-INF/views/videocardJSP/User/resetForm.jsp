<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Смена пароля</title>
    <style>
        body {
            background-color: ivory;
            font-family: "Comic Sans MS", cursive;
        }

        .fonts {
            font-family: "Comic Sans MS", cursive;
        }

        .fonts-right {
            float: right;
            font-family: "Comic Sans MS", cursive;
        }
    </style>
</head>
<body>
<form action="/user/profile" method="get">
    <button type="submit" class="fonts-right">Вернуться на главную</button>
</form>
<p>Введите свой адрес электронной почты</p>
<form action="/password/reset" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <table>
        <tr>
            <td><input type="email" id="email" name="email" required></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" class="fonts" value="Отправить адрес"/></td>
        </tr>
    </table>
</form>
</body>
</html>
