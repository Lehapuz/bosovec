<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete comment</title>
</head>

<body>
<h3>Удаление комментария</h3>
<form method="post">

<label>textComment</label>
<br>
<input name="textComment" type="string"/>
<br>
<br>
<label>email</label>
<br>
<input name="email" type="email"/>
<br>
<br>
<input type="submit" value="Save"/>
</form>

<p>"${вудуеу}"</p>

<br>
<br>

<a href="/user.jsp">Назад</a>

</body>
</html>