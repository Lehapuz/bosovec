<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exit</title>
</head>
<body>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="local"/>
<fmt:message bundle="${local}" key="local.sureExit" var="sureExit"/>

<h3>"${sureExit}"?</h3>
<form action="Controller?command=exit_user&action=exit" method="post">
    <select name="admit">
        <option value="Yes">Yes</option>
        <option value="No">No</option>
    </select>
    <br><br>
    <input type="submit" value="Save"/>
</form>
<br>
<br>
</body>
</html>