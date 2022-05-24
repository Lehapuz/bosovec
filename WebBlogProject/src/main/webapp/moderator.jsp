<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Личный кабинет модератора</title>
</head>
<body>
    <div>
        <h1>Добро пожаловать в личный кабинет</h1>
    </div>
    <div>
    <h2>Список доступных команд для зарегистрированного модератора:</h2>
        <div>
        <button onclick="location.href='/authorizationModerator/updateModerator'">Обновление аккаунта модератора</button>
        <br></br>
        <button onclick="location.href='/authorizationModerator/deleteModerator'">Удаление аккаунта модератора</button>
        <br></br>
        <button onclick="location.href='/authorizationModerator/setSettings'">Установка настройки сайта</button>
        <br></br>
        <button onclick="location.href='/authorizationModerator/moderatorPost'">Модерировать новый пост</button>
        <br></br>
        <button onclick="location.href='/getPost'">Просмотр всех постов</button>
        <br></br>
        <button onclick="location.href='/getComments'">Просмотр комментариев к постам</button>
        <br></br>
        <button onclick="location.href='/authorizationModerator/showSettings'">Показать настройки сайта</button>
        <br></br>
        <button onclick="location.href='/exitModerator'">Выход</button>
        <br></br>
        </div>
    </div>
</body>
</html>