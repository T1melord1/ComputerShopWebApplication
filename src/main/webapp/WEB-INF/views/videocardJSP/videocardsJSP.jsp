<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        .nav-link {
            font-size: 24px; /* Размер текста */
            color: cornflowerblue; /* Цвет текста */
            cursor: pointer; /* Указатель при наведении */
            position: absolute; /* Абсолютное позиционирование */
            transition: color 0.3s; /* Плавный переход цвета */
        }
        .nav-link.register {
            right: 150px; /* Расположение справа */
        }
        .nav-link.login {
            right: 50px; /* Расположение справа */
        }
        .nav-link:hover {
            color: firebrick; /* Цвет при наведении */
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
            font-size: 36px; /* Размер значка */
            color: cornflowerblue; /* Цвет значка */
            cursor: pointer; /* Меняет курсор на указатель при наведении на значок */
            display: inline-block; /* Делает элемент блочным, но оставляет его в строке */
            transition: color 0.3s; /* Плавный переход цвета при наведении на 0.3 секунды */
        }
        .cart-icon:hover {
            color: #0056b3; /* Цвет значка при наведении */
        }
    </style>
</head>
<body>
<div class="nav-links">
    <form action="/videocards/find/manufacturer" method="get" style="display: inline;">
        <label for="videocardManufacturer">Выберите производителя видеокарты:</label>
        <select id="videocardManufacturer" name="manufacturer" required>
            <option class="fonts" value="NVIDIA">NVIDIA</option>
            <option class="fonts" value="AMD">AMD</option>
            <option class="fonts" value="Intel">Intel</option>
        </select>
        <button class="fonts" type="submit">Найти видеокарту</button>
    </form>
    <form action="/videocards/add" method="get" style="display: inline;">
        <button class="fonts" type="submit">Добавить видеокарту</button>
    </form>
    <a href="/cart" class="cart-icon">
        <i class="fas fa-shopping-cart"></i>
    </a>
</div>
<table>
    <tr>
        <th>ID</th>
        <th>Manufacturer</th>
        <th>Graphic Processor</th>
        <th>Video Memory</th>
        <th>Color</th>
        <th>Price</th>
    </tr>
    <c:forEach var="videocard" items="${videocards}">
        <tr>
            <td>${videocard.id}</td>
            <td>${videocard.manufacturer}</td>
            <td class="graphicProcessor">${videocard.graphicProcessor}</td>
            <td class="videoMemory">${videocard.videoMemory}</td>
            <td>${videocard.color}</td>
            <td>${videocard.price}</td>
            <td>
                <form action="/videocards/update/${videocard.id}" method="get">
                    <button class="fonts" type="submit">Обновить</button>
                </form>
            </td>
            <td>
                <form action="/videocards/delete/${videocard.id}" method="post">
                    <button class="fonts" type="submit">Удалить</button>
                </form>
            </td>
            <td>
                <form action="/cart/add/${videocard.id}" method="post">
                    <button class="fonts" type="submit">Добавить в корзину</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
