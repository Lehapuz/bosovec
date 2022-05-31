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
<c:forEach var="post" items="${post}">
    <li>
    <c:out value="${post}"/>
    <br></br>
    <button onclick="location.href='/addComment'">Добавить комментарий к посту</button>
    </li>
</c:forEach>
<br></br>
<a href = "/user.jsp">Назад</a>
</ul>
</body>
</html>