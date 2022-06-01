<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<title>postComments</title>
</head>
<body>
<h1>Комментарии к посту</h1>
<ul>
<c:forEach var="postComment" items="${postComment}">
    <li>
    <c:out value="${postComment}"/>
    </li>
</c:forEach>
<br></br>
</ul>
</body>
</html>