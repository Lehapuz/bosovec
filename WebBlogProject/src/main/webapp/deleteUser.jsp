<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete user</title>
</head>
<body>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="local"/>
<fmt:message bundle="${local}" key="local.deleteUser" var="deleteUser"/>

<h3>"${deleteUser}"</h3>
<form action="Controller?command=delete_user_action&action=deleteUser" method="post">
<label>Email</label>
<br>
<input name="email" type="email"/>
<br>
<br>
<label>Password</label>
<br>
<input name="password" type="password"/>
<br>
<br>
<input type="submit" value="Save"/>
</form>
<p>"${deleteAccountUser}"</p>
<br>
<br>
<a href="/web/user.jsp">Back</a>
</body>
</html>