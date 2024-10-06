<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Обновление данных видеокарты</title>
</head>
<body>
<h1>Обновление данных видеокарты</h1>
<form:form modelAttribute="videocard" action="/videocards/update/${videocard.id}" method="post">
<table>
  <tr>
    <td>Manufacturer:</td>
    <td>
      <form:select path="manufacturer">
        <form:option value="NVIDIA" label="NVIDIA" />
        <form:option value="AMD" label="AMD" />
        <form:option value="Intel" label="Intel" />
      </form:select>
    </td>
  </tr>
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
    <td colspan="2"><input type="submit" value="Обновить"/></td>
  </tr>
</table>
</form:form>
<a href="/videocards/">Вернуться к списку видеокарт</a>

