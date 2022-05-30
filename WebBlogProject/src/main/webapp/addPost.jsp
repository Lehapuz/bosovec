<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create post</title>
</head>

<body>
<h3>Добавление поста</h3>
<form method="post">

<label>Email</label>
<br>
<input name="email" type="email"/>

<br>
<br>

<label>Title</label>
<br>
<input name="title" type="string"/>

<br>
<br>

<label>Text</label>
<br>
<input name="text" type="string"/>

<br>
<br>

<input type="submit" value="Save"/>
</form>

<p>"${correctEmail}"</p>

<br>
<br>

<a href="/user.jsp">Назад</a>

</body>
</html>