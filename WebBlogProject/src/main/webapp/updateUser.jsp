<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update user</title>
</head>
<body>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="local"/>
<fmt:message bundle="${local}" key="local.updateUser" var="updateUser"/>

<h3>"${updateUser}"</h3>
<form action="Controller?command=update_user_action&action=updateUser" method="post">
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
<label>New Name</label>
<br>
<input name="name" type="string"/>
<br>
<br>
<label>New Password</label>
<br>
<input name="newPassword" type="password"/>
<br>
<br>
<input type="submit" value="Save"/>
</form>
<p>"${updateAccountUser}"</p>
<br>
<br>
<a href="/web/user.jsp">Back</a>
</body>
</html>