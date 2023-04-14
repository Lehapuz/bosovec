<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<title>my posts</title>
</head>
<body>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="local"/>
<fmt:message bundle="${local}" key="local.listMyPosts" var="listMyPosts"/>
<fmt:message bundle="${local}" key="local.addMyPost" var="addMyPost"/>
<fmt:message bundle="${local}" key="local.updateMyPost" var="updateMyPost"/>
<fmt:message bundle="${local}" key="local.deleteMyPost" var="deleteMyPost"/>


<h1>"${listMyPosts}"</h1>
<ul>
<c:forEach var="post" items="${myPosts}">
    <li>
    <c:out value="${post.title}"/>
    <br></br>
    <c:out value="${post.text}"/>
    <br></br>
    </li>
</c:forEach>
<br></br>
<h3>"${addMyPost}"</h3>
<form action="Controller?command=my_post&action=addPost" method="post">
<label>Email</label>
<br>
<input name="email" type="email"/>
<br>
<br>
<label>Title</label>
<br>
<input name="title" type="string"/>
<br>
<br>
<label>Text</label>
<br>
<input name="text" type="string"/>
<br>
<br>
<input type="submit" value="Save"/>
</form>
<p>"${correctEmail}"</p>
<br>
<br>

<h3>"${updateMyPost}"</h3>
    <form action="Controller?command=my_post&action=updatePost" method="post">
    <label>Title</label>
    <br>
    <input name="updateTitle" type="string"/>
    <br>
    <br>
    <label>newTitle</label>
    <br>
    <input name="newTitle" type="string"/>
    <br>
    <br>
    <label>text</label>
    <br>
    <input name="updateText" type="string"/>
    <br>
    <br>
    <input type="submit" value="Save"/>
    </form>
    <p>"${updatePostText}"</p>
    <br>
    <br>
    <h3>"${deleteMyPost}"</h3>
    <form action="Controller?command=my_post&action=deletePost" method="post">
    <label>Title</label>
    <br>
    <input name="deleteTitle" type="string"/>
    <br>
    <br>
    <input type="submit" value="Save"/>
    </form>
    <p>"${deletePostText}"</p>
    <br>
    <br>
<a href="/web/user.jsp">Back</a>
</ul>
</body>
</html>