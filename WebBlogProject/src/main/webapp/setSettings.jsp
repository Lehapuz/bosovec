<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Set settings</title>
</head>
<body>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="local"/>
<fmt:message bundle="${local}" key="local.getWebSetting" var="getWebSetting"/>
<fmt:message bundle="${local}" key="local.setWebSetting" var="setWebSetting"/>

<h3>"${getWebSetting}"</h3>
<p>"${settings}"</p>
<br>
<h3>"${setWebSetting}"</h3>
<form action="Controller?command=set_setting" method="post">
    <select name="status">
        <option value="Yes">Allow posting</option>
        <option value="No">Disable posting</option>
    </select>
    <br><br>
    <input type="submit" value="Save"/>
</form>
<br>
<br>
<a href="/web/moderator.jsp">Назад</a>
</body>
</html>