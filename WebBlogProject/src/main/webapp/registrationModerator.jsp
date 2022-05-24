<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create user</title>
</head>

<body>
<h3>Регистрация Модератора</h3>
<form method="post">

<label>Name</label>
<br>
<input name="name" type="string"/>

<br>
<br>

<label>Password</label>
<br>
<input name="password" type="password"/>

<br>
<br>

<label>Email</label>
<br>
<input name="email" type="email"/>

<br>
<br>

<label>Secrete code</label>
<br>
<input name="secretCode" type="string"/>

<br>
<br>

<input type="submit" value="Save"/>
</form>

<p>"${registration}"</p>

<br>
<br>

<a href="/">Назад</a>

</body>
</html>