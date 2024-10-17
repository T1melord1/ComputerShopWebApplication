<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User login</title>
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
<form:form modelAttribute="user" action="/user/login" method="post">
    <table>
        <tr>
            <td>Username:</td>
            <td><form:input path="username"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><form:input path="password" type="password"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" class="fonts" value="Логин"/>
                <c:if test="${not empty param.error}">
                    <div style="color: red;">Неверный логин или пароль.</div>
                </c:if>
                <c:if test="${not empty param.logout}">
                    <div style="color: green;">Вы вышли из системы.</div>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
