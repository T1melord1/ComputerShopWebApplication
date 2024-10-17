<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User register</title>
    <style>
        body {
            background-color: ivory;
            font-family: "Comic Sans MS", cursive;
        }

        .fonts {
            font-family: "Comic Sans MS", cursive;
        }
    </style>
</head>
<body>
<form:form modelAttribute="user" action="/user/register" method="post">
    <table>
        <tr>
            <td>Username:</td>
            <td><form:input path="username"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><form:input path="password"/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td>Role:</td>
            <td><form:input path="role"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" class="fonts" value="Регистрация"/></td>
        </tr>
    </table>
</form:form>
<form action="/user/login" method="get">
    <button class="fonts" type="submit">Я уже зарегистрирован</button>
</form>
</body>
</html>
