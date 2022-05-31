<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>setStatus</title>
</head>

<body>
<h3>Установка статуса модерации</h3>
<form method="post">

<label>Название поста</label>
<br>
<input name="postTitle" type="string"/>
<br>
<br>
<select name="status">
    <option value="OK">Одобрить пост</option>
    <option value="NO">Отклонить пост</option>
</select>
<br>
<br>
<input type="submit" value="Save"/>
</form>

<br>
<br>

<p>"${setStatus}"</p>

<br>
<br>

<a href="/moderator.jsp">Назад</a>

</body>
</html>