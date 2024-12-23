<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Обновление данных видеокарты</title>
    <style>
        body {
            background-color: ivory;
            font-family: "Comic Sans MS", cursive;
        }

        .fonts {
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
<h1>Обновление данных видеокарты</h1>
<form:form modelAttribute="videocard" cssClass="fonts" action="/videocards/admin/update/${videocard.id}" method="post">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<table>
    <tr>
        <td>Manufacturer:</td>
        <td>
            <form:select path="manufacturer">
                <form:option cssClass="fonts" value="NVIDIA" label="NVIDIA"/>
                <form:option cssClass="fonts" value="AMD" label="AMD"/>
                <form:option cssClass="fonts" value="Intel" label="Intel"/>
            </form:select>
        </td>
    </tr>
    <tr>
        <td>Graphic Processor:</td>
        <td><form:input cssClass="fonts" path="graphicProcessor"/></td>
    </tr>
    <tr>
        <td>Video Memory:</td>
        <td><form:input cssClass="fonts" path="videoMemory"/></td>
    </tr>
    <tr>
        <td>Color:</td>
        <td><form:input cssClass="fonts" path="color"/></td>
    </tr>
    <tr>
        <td>Price:</td>
        <td><form:input cssClass="fonts" path="price"/></td>
    </tr>
    <tr>
        <td colspan="2"><input type="submit" class="fonts" value="Обновить"/></td>
    </tr>
</table>
</form:form>
<form action="/videocards/admin" method="get">
    <button class="fonts" type="submit">Вернуться к списку видеокарт</button>
</form>
</html>

