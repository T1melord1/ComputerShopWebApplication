<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Логин</title>
    <style>
        body {
            background-color: ivory;
            font-family: "Comic Sans MS", cursive;
        }

        .fonts {
            font-family: "Comic Sans MS", cursive;
        }

        .alert {
            color: red;
            margin-bottom: 20px;
        }

        .alert-success {
            color: green;
            margin-bottom: 20px;
        }

        table {
            margin-top: 20px;
        }

        @media only screen and (max-width: 600px) {
            body {
                font-family: Palatino, sans-serif; /* Шрифт Palatino для мобильных устройств */
                font-size: 18px; /* Увеличенный размер шрифта для мобильных устройств */
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            th, td {
                padding: 10px;
                text-align: center;
                font-size: 16px; /* Увеличенный размер шрифта для таблицы */
            }

            .button-container button {
                width: 100%;
                font-size: 16px; /* Увеличенный размер шрифта для кнопок */
                font-family: Palatino, sans-serif; /* Шрифт Palatino для мобильных устройств */
            }

            .nav-links form, .nav-links a {
                width: 100%;
                text-align: center;
                margin-bottom: 10px;
            }

            .nav-links form select, .nav-links form button, .nav-links .logout-button {
                width: 100%;
                font-size: 16px; /* Увеличенный размер шрифта для элементов формы */
                font-family: Palatino, sans-serif; /* Шрифт Palatino для мобильных устройств */
            }

            label {
                font-size: 16px; /* Увеличенный размер шрифта для меток */
                font-family: Palatino, sans-serif; /* Шрифт Palatino для меток на мобильных устройствах */
            }
        }
    </style>
</head>

<body>
<h2>Логин</h2>
<!-- Блок для сообщения об успешной регистрации -->
<c:if test="${not empty successMessage}">
    <div class="alert-success">${successMessage}</div>
</c:if>
<c:if test="${param.error != null}">
    <div class="alert">Неправильное имя пользователя или пароль. Попробуйте снова.</div>
</c:if>
<form:form action="${pageContext.request.contextPath}/login" method="post" cssClass="fonts">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <table>
        <tr>
            <td><label for="username">Имя пользователя:</label></td>
            <td><input type="text" id="username" name="username" class="fonts" placeholder="Username" required></td>
        </tr>
        <tr>
            <td><label for="password">Пароль:</label></td>
            <td><input type="password" id="password" name="password" class="fonts" placeholder="Password" required></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" class="fonts" value="Логин"/></td>
        </tr>
    </table>
</form:form>
<p>Нет аккаунта? <a class="fonts" href="${pageContext.request.contextPath}/register">Зарегистрироваться</a></p>
<p>Забыли пароль? <a class="fonts" href="${pageContext.request.contextPath}/password/reset">Сброс пароля</a></p>
</body>
</html>
