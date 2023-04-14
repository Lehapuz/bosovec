<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<title>postComments</title>
</head>
<body>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="local"/>
<fmt:message bundle="${local}" key="local.myPostComments" var="myPostComments"/>
<fmt:message bundle="${local}" key="local.updateComment" var="updateComment"/>
<fmt:message bundle="${local}" key="local.deleteComment" var="deleteComment"/>

<h1>"${myPostComments}"</h1>
<ul>
<c:forEach var="postComment" items="${postComment}">
    <li>
    <c:out value="${postComment}"/>
    <br></br>
    </li>
</c:forEach>
<br></br>
<h3>"${updateComment}"</h3>
<form action="Controller?command=my_comment&action=updateComment" method="post">
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
<label>newTextComment</label>
<br>
<input name="newTextComment" type="string"/>
<br>
<br>
<input type="submit" value="Save"/>
</form>
<p>"${updateTextComment}"</p>
<br>
<br>
<h3>"${deleteComment}"</h3>
<form action="Controller?command=my_comment&action=deleteComment" method="post">
<label>textComment</label>
<br>
<input name="deleteTextComment" type="string"/>
<br>
<br>
<label>email</label>
<br>
<input name="deleteEmail" type="email"/>
<br>
<br>
<input type="submit" value="Save"/>
</form>
<p>"${deleteTextComment}"</p>
<br>
<br>
<a href="/web/user.jsp">Back</a>
</ul>
</body>
</html>