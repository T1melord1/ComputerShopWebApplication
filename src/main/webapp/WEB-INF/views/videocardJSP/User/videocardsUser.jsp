<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <title>Videocard List</title>
    <style>
        .nav-links {
            position: relative; /* Относительное позиционирование для навигационных ссылок */
            margin-bottom: 20px; /* Отступ между ссылками и формой */
        }

        .logout-button {
            font-family: "Comic Sans MS", cursive;
        }

        .videoMemory {
            padding-left: 35px; /* Добавляет отступ слева */
        }

        .graphicProcessor {
            padding-left: 50px;
        }

        body {
            background-color: ivory;
            font-family: "Comic Sans MS", cursive;
        }

        .fonts {
            font-family: "Comic Sans MS", cursive;
        }

        .cart-icon {
            float: right;
            font-size: 36px; /* Размер значка */
            color: cornflowerblue; /* Цвет значка */
            cursor: pointer; /* Меняет курсор на указатель при наведении на значок */
            display: inline-block; /* Делает элемент блочным, но оставляет его в строке */
            transition: color 0.3s; /* Плавный переход цвета при наведении на 0.3 секунды */
        }

        .cart-icon:hover {
            color: #0056b3; /* Цвет значка при наведении */
        }
        .button-container { display: flex;
            justify-content: center;
            align-items: center; }
        .button-container button {
            width: 100%; /* Кнопка занимает всю ширину контейнера */
            padding: 5px; /* Отступ внутри кнопки */
            font-family: "Comic Sans MS", cursive; }
    </style>
</head>
<body>
<div class="nav-links">
    <form action="${pageContext.request.contextPath}/videocards/find/manufacturer" method="get"
          style="display: inline;">
        <label for="videocardManufacturer">Выберите производителя видеокарты:</label>
        <select id="videocardManufacturer" name="manufacturer" required>
            <option class="fonts" value="NVIDIA">NVIDIA</option>
            <option class="fonts" value="AMD">AMD</option>
            <option class="fonts" value="Intel">Intel</option>
            <option class="fonts" value="Apple">Apple</option>
        </select>
        <button class="fonts" type="submit">Найти видеокарту</button>
    </form>
    <form action="${pageContext.request.contextPath}/logout" method="post" style="display: inline" class="fonts">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button class="logout-button" type="submit">Выйти из аккаунта</button>
    </form>
    <a href="${pageContext.request.contextPath}/cart" class="cart-icon">
        <i class="fas fa-shopping-cart"></i>
    </a>
    <a href="${pageContext.request.contextPath}/user/profile" class="cart-icon" data-username="username">
        <i class="fas fa-user"></i>
    </a>
</div>
<table>
    <tr>
        <th>Number</th>
        <th>Manufacturer</th>
        <th>Graphic Processor</th>
        <th>Video Memory</th>
        <th>Color</th>
        <th>Price</th>
    </tr>
    <c:forEach var="videocard" items="${videocards}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${videocard.manufacturer}</td>
            <td class="graphicProcessor">${videocard.graphicProcessor}</td>
            <td class="videoMemory">${videocard.videoMemory} GB</td>
            <td>${videocard.color}</td>
            <td>${videocard.price}</td>
            <td>
                <form action="/cart/add/${videocard.id}" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="button-container">
                    <button class="fonts" type="submit">Добавить в корзину</button>
                    </div>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
