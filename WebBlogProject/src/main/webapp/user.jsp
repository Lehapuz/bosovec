<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Личный кабинет</title>
</head>
<body>
    <div>
        <h1>Добро пожаловать в личный кабинет</h1>
    </div>
    <div>
    <h2>Список доступных команд для зарегистрированного пользователя:</h2>
        <div>
        <button onclick="location.href='/authorizationUser/updateUser'">Обновление аккаунта пользователя</button>
        <br></br>
        <button onclick="location.href='/authorizationUser/deleteUser'">Удаление аккаунта пользователя</button>
        <br></br>
        <button onclick="location.href='/getPosts'">Просмотр всех постов</button>
        <br></br>
        <button onclick="location.href='/addPost'">Добавление поста</button>
        <br></br>
        <button onclick="location.href='/getMyPosts'">Просмотр моих постов</button>
        <br></br>
        <button onclick="location.href='/getMyComments'">Просмотр моих комментариев к постам</button>
        <br></br>
        <button onclick="location.href='/exitUser'">Выход</button>
        <br></br>
        </div>
    </div>
</body>
</html>