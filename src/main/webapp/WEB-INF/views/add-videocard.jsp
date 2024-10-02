<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавить видеокарту</title>
</head>
<body>
<h1>Добавить видеокарту</h1>
<form:form modelAttribute="videocard" action="/videocards/add" method="post">
  <table>
    <tr>
      <td>Manufacturer:</td>
      <td><form:input path="manufacturer"/></td>
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
      <td colspan="2"><input type="submit" value="Добавить"/></td>
    </tr>
  </table>
</form:form>
</body>
</html>
