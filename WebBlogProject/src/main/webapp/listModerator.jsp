<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<title>moderators</title>
</head>
<body>
<h1>Список Модераторов</h1>
<ul>
<c:forEach var="moderator" items="${moderator}">
    <li><c:out value="${moderator}"/></li>
</c:forEach>
<br></br>
<a href = "/">Назад</a>
</ul>
</body>
</html>