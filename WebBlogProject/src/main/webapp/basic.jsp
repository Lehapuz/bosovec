<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Application</title>
</head>
<body>

<c:if test="${register == null}">
    <p>Visible</p>
</c:if>

<c:choose>
    <c:when test="${user == null}">
        <p>Equals to 1</p>
    </c:when>
</c:choose>



<p>User: ${user}</p>
<ul>
<c:forEach var="user" items="${user}">
<li><c:out value="${user}"/></li>
</c:forEach>
</ul>
</body>
</html>