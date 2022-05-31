<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update post</title>
</head>

<body>
<h3>Обновление поста</h3>
<form method="post">

<label>Title</label>
<br>
<input name="title" type="string"/>
<br>
<br>
<label>newTitle</label>
<br>
<input name="newTitle" type="string"/>
<br>
<br>
<label>text</label>
<br>
<input name="text" type="string"/>
<br>
<br>
<input type="submit" value="Save"/>
</form>

<p>"${update}"</p>

<br>
<br>

<a href="/user.jsp">Назад</a>

</body>
</html>