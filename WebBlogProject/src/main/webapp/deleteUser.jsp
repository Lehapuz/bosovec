<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete user</title>
</head>

<body>
<h3>Удаление аккаунта пользователя</h3>
<form method="post">

<label>Email</label>
<br>
<input name="email" type="email"/>

<br>
<br>

<label>Password</label>
<br>
<input name="password" type="password"/>

<br>
<br>

<input type="submit" value="Save"/>
</form>

<p>"${delete}"</p>

<br>
<br>

<a href="/user.jsp">Назад</a>

</body>
</html>