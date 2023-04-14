<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>posts</title>
    <script src="js/jquery-3.4.0.min.js"></script>
    <script type="text/javascript" src="js/script.js"></script>
</head>
<body>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="local"/>
<fmt:message bundle="${local}" key="local.posts" var="lookPosts"/>
<fmt:message bundle="${local}" key="local.nothing" var="nothing"/>

<h1>"${lookPosts}" (<span>"${size}"</span>)</h1>
<ul>
    <c:choose>
        <c:when test="${size==0}">
            <p>"${nothing}"</p>
        </c:when>
        <c:when test="${size!=0}">
            <c:forEach var="post" items="${posts}">
                <li>
                    <a href='<c:url value="/Controller?postId=${post.id}&command=post_id"/>'>
                        <c:out value="${post}"/>
                        <br></br>
                    </a>
                </li>
            </c:forEach>
        </c:when>
    </c:choose>
    <br></br>
    <a href="/web/index.jsp">Back</a>
</ul>
</body>
</html>



