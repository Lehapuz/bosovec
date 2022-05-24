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
        <button onclick="location.href='/getPost'">Просмотр всех постов</button>
        <br></br>
        <button onclick="location.href='/addPost'">Добавление поста</button>
        <br></br>
        <button onclick="location.href='/deletePost'">Удалить пост</button>
        <br></br>
        <button onclick="location.href='/updatePost'">Обновить пост</button>
        <br></br>
        <button onclick="location.href='/getComments'">Просмотр комментариев к постам</button>
        <br></br>
        <button onclick="location.href='/addComment'">Добавить комментарий</button>
        <br></br>
        <button onclick="location.href='/deleteComment'">Удалить комментарий</button>
        <br></br>
        <button onclick="location.href='/updateComment'">Обновить комментарий</button>
        <br></br>
        <button onclick="location.href='/exitUser'">Выход</button>
        <br></br>
        </div>
    </div>
</body>
</html>