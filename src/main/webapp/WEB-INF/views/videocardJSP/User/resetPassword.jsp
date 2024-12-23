<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
    <title>Сброс пароля</title>
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
<form action="/user/login" method="get">
    <button type="submit" class="fonts-right">Вернуться на главную</button>
</form>
<h2>Сброс пароля</h2>
<form:form method="post" action="/reset-password" cssClass="fonts" modelAttribute="user">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <form:hidden path="resetToken" value="${user.resetToken}"/>
    <form:hidden path="email" value="${user.email}"/>
    <table>
        <tr>
            <td><label for="newPassword">Новый пароль:</label></td>
            <td><form:input type="password" path="newPassword" required="required"/></td>
        </tr>
        <tr>
            <td><label for="confirmPassword">Подтвердите пароль:</label></td>
            <td><form:input type="password" path="confirmPassword" required="required"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" class="fonts" value="Сменить пароль"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
