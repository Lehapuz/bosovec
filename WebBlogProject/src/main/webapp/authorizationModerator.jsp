<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Authorization User</title>
</head>

<body>
<h3>Вход на сайт</h3>
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

<input type="submit" value="LogIn"/>
</form>

<p>"${authorization}"</p>

<br>
<br>

<a href="/">Назад</a>

</body>
</html>