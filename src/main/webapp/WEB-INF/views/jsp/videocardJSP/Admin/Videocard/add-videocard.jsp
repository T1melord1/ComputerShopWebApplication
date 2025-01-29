<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Добавить видеокарту</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <style>
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
<h1>Добавить видеокарту</h1>
<form:form modelAttribute="videocard" action="/videocards/admin/add" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <%--   тег <form:form> для создания формы. Атрибут modelAttribute указывает на объект модели,
    который будет использоваться для связывания данных формы. Атрибут action указывает URL,
    на который будет отправлена форма, а method указывает метод HTTP (POST).--%>
    <table>
        <tr>
            <td>Manufacturer:</td>
            <td>
                <form:select path="manufacturer">
                    <form:option value="NVIDIA" cssClass="fonts" label="NVIDIA"/>
                    <form:option value="AMD" cssClass="fonts" label="AMD"/>
                    <form:option value="Intel" cssClass="fonts" label="Intel"/>
                    <form:option value="Apple" cssClass="fonts" label="Apple"/>
                </form:select>
            </td>
        </tr>
            <%--   Каждые поля видеокарты (производитель, графический процессор, видеопамять, цвет, цена)
            занимают соответствующие теги <form:input> с атрибутом path, который указывает на свойство объекта модели. --%>
        <tr>
            <td>Graphic Processor:</td>
            <td><form:input path="graphicProcessor" cssClass="fonts"/></td>
        </tr>
        <tr>
            <td>Video Memory:</td>
            <td><form:input path="videoMemory" cssClass="fonts"/></td>
        </tr>
        <tr>
            <td>Color:</td>
            <td><form:input path="color" cssClass="fonts"/></td>
        </tr>
        <tr>
            <td>Price:</td>
            <td>
                <form:input path="price" cssClass="fonts"/>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" class="fonts" value="Добавить"/></td>
        </tr>
    </table>
</form:form>
<form action="/videocards/admin" method="get">
    <button class="fonts" type="submit">Вернуться к списку видеокарт</button>
</form>
</body>
</html>
