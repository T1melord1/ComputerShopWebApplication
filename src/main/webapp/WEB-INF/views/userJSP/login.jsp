<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
            color: green; /* Или другой цвет, который ты хочешь */
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<h2>Login</h2>
<!-- Блок для сообщения об успешной регистрации -->
<c:if test="${not empty successMessage}">
    <div class="alert">${successMessage}</div>
</c:if>
<c:if test="${param.error != null}">
    <div class="alert">Неправильное имя пользователя или пароль. Попробуйте снова.</div>
</c:if>
<form:form action="${pageContext.request.contextPath}/login" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" class="fonts" required><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" class="fonts" required><br>
    <button type="submit" class="fonts">Логин</button>
</form:form>
<p>Нет аккаунта? <a class="fonts" href="${pageContext.request.contextPath}/register">Зарегистрироваться</a></p>
</body>
</html>
