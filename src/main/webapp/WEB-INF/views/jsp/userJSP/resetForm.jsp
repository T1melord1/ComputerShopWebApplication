<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Смена пароля</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

    <style>
        .fonts-right {
            float: right;
            font-family: "Comic Sans MS", cursive;
        }

        .form-group input {
            width: 300px; /* Ширина для поля ввода */
        }

        .submit-button {
            width: auto; /* Ширина кнопки будет зависеть от содержания */
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
<p>Введите свой адрес электронной почты:</p>
<form action="/password/reset" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div class="form-group">
        <table>
            <tr>
                <td><input type="email" id="email" name="email" placeholder="Email" class="fonts" required></td>
            </tr>
            <tr>
                <td><input type="submit" class="fonts submit-button" value="Подтвердить адрес почты"/></td>
            </tr>
        </table>
    </div>
</form>
</body>
</html>
