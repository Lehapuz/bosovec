<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Set settings</title>
</head>

<body>
<h3>Установка настройки для сайта</h3>

<form method="post">
    <select name="status">
        <option value="Yes">Разрешить добавление постов</option>
        <option value="No">Запретить добавление постов</option>
    </select>
    <br><br>
    <input type="submit" value="Save"/>
</form>

<br>
<br>

</body>
</html>