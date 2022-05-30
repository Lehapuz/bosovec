<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<title>posts</title>
</head>
<body>
<h1>Посты</h1>
<ul>
<a href = "/post/id">
<c:forEach var="post" items="${post}">
    <li><c:out value="${post}"/></li>
</c:forEach>
</a>
<br></br>
<a href = "/">Назад</a>
</ul>
</body>
</html>