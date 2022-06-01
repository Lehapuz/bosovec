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
    <c:out value="${post.id}"/>
    <td>${post.id}</td>
    <br></br>
    <c:out value="${post.title}"/>
    <br></br>
    <a href='<c:url value="/post/getComments?title=${post.title}" />'>Просмотр комментариев</a>
    <br></br>
    <a href='<c:url value="/post/vote?title=${post.title}" />'>Проголосовать за пост</a>
    <br></br>
    </li>
</c:forEach>
<br></br>
<a href = "/">Назад</a>
</ul>
</body>
</html>