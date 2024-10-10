<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить видеокарту</title>
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
<h1>Добавить видеокарту</h1>
<form:form modelAttribute="videocard" action="/videocards/add" method="post">
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
                </form:select>
            </td>
        </tr>
            <%--   Каждые поля видеокарты (производитель, графический процессор, видеопамять, цвет, цена)
            занимают соответствующие теги <form:input> с атрибутом path, который указывает на свойство объекта модели. --%>
        <tr>
            <td>Graphic Processor:</td>
            <td><form:input path="graphicProcessor"/></td>
        </tr>
        <tr>
            <td>Video Memory:</td>
            <td><form:input path="videoMemory"/></td>
        </tr>
        <tr>
            <td>Color:</td>
            <td><form:input path="color"/></td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><form:input path="price"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" class="fonts" value="Добавить"/></td>
        </tr>
    </table>
</form:form>
<form action="/videocards/" method="get">
    <button class="fonts" type="submit">Вернуться к списку видеокарт</button>
</form>
</body>
</html>
