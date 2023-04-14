<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<title>posts</title>
</head>
<body>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="local"/>
<fmt:message bundle="${local}" key="local.posts" var="lookPosts"/>

<h1>"${lookPosts}" (<span>"${size}"</span>)</h1>
<ul>
<c:forEach var="post" items="${posts}">
    <li>
    <a href='<c:url value="/Controller?postId=${post.id}&command=post_id"/>'>
    <c:out value="${post}"/>
    </a>
    <td>
          <c:out value=": Статус модерации - "/>
    </td>
    <td>
          <c:out value="${post.moderatorStatus}"/>
    </td>
    <br></br>
    </li>
</c:forEach>
<br></br>
<a href = "/web/moderator.jsp">Back</a>
</ul>
</body>
</html>