<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Блог</title>
</head>
<body>
    <div>
        <h1>Добро пожаловать на сайт</h1>
    </div>
    <div>
    <h2>Список доступных команд для незарегистрированного пользователя:</h2>
        <div>
        <button onclick="location.href='/registerModerator'">Регистрация модератора</button>
        <br></br>
        <button onclick="location.href='/getModerators'">Просмотр всех модераторов</button>
        <br></br>
        <button onclick="location.href='/authorizationModerator'">Авторизация модератора</button>
        <br></br>
        <button onclick="location.href='/registerUser'">Регистрация пользователя</button>
        <br></br>
        <button onclick="location.href='/getUsers'">Просмотр всех пользователей</button>
        <br></br>
        <button onclick="location.href='/getPosts'">Просмотр всех постов</button>
        <br></br>
        <button onclick="location.href='/votePost'">Проголосовать за пост</button>
        <br></br>
        <button onclick="location.href='/authorizationUser'">Авторизация пользователя</button>
        <br></br>
        <button onclick="location.href='/getComment'">Просмотр комментариев к постам</button>
        <br></br>
        </div>
    </div>
</body>
</html>