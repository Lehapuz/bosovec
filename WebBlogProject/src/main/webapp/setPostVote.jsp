<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>setPostVote</title>
</head>
<body>
<h3>Голосование за пост</h3>
<form method="post">
<label>Проголосовать за пост</label>
<br>
<select name="value">
    <option value="1">Нравится</option>
    <option value="-1">Не нравится</option>
</select>
<br>
<br>
<input type="submit" value="Save"/>
</form>
<br>
<a href="/">Назад</a>
</body>
</html>