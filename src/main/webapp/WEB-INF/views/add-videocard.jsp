<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавить видеокарту</title>
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
      <td><form:input path="manufacturer"/></td>
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
      <td colspan="2"><input type="submit" value="Добавить"/></td>
    </tr>
  </table>
</form:form>
<a href="/videocards/">Вернуться к списку видеокарт</a>
</body>
</html>
