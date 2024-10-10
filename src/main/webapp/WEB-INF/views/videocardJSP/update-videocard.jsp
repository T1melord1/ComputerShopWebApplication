<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Обновление данных видеокарты</title>
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
<h1>Обновление данных видеокарты</h1>
<form:form modelAttribute="videocard" cssClass="fonts" action="/videocards/update/${videocard.id}" method="post">
<table>
  <tr>
    <td>Manufacturer:</td>
    <td>
      <form:select path="manufacturer">
        <form:option cssClass="fonts" value="NVIDIA" label="NVIDIA" />
        <form:option cssClass="fonts" value="AMD" label="AMD" />
        <form:option cssClass="fonts" value="Intel" label="Intel" />
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
<form action="/videocards/" method="get">
  <button class="fonts" type="submit">Вернуться к списку видеокарт</button>
</form>
</html>

