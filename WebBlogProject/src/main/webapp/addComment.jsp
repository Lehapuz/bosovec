<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create comment</title>
</head>

<body>
<h3>Добавление комментария</h3>
<form method="post">

<label>Title</label>
<br>
<input name="title" type="string"/>

<br>
<br>

<label>Email</label>
<br>
<input name="email" type="email"/>

<br>
<br>

<label>TextComment</label>
<br>
<input name="textComment" type="string"/>

<br>
<br>

<input type="submit" value="Save"/>
</form>

<p>"${correct}"</p>

<br>
<br>

<a href="/user.jsp">Назад</a>

</body>
</html>