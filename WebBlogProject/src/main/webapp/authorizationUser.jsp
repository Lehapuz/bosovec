<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Authorization User</title>
</head>
<body>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="local"/>
<fmt:message bundle="${local}" key="local.enter" var="enter"/>
<fmt:message bundle="${local}" key="local.register" var="register"/>

<h3>"${enter}"</h3>
<form action="Controller?command=log_in&action=logIn" method="post">
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
<input type="submit" value="LogIn"/>
</form>
<p>"${authorization}"</p>
<br>
<br>

<h3>"${register}"</h3>
<form action="Controller?command=log_in&action=registration" method="post">
<label>Name</label>
<br>
<input name="name" type="string"/>
<br>
<br>
<label>Password</label>
<br>
<input name="password" type="password"/>
<br>
<br>
<label>Email</label>
<br>
<input name="email" type="email"/>
<br>
<br>
<label>SecreteCode</label>
<br>
<input name="secreteCode" type="string"/>
<br>
<br>
<input type="submit" value="Save"/>
</form>
<p>"${registrationAccountUser}"</p>
<br>
<br>

<a href="/web/index.jsp">Back</a>
</body>
</html>