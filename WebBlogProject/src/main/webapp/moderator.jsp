<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Личный кабинет модератора</title>
</head>
<body>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="local"/>
<fmt:message bundle="${local}" key="local.titleUser" var="titleUser"/>
<fmt:message bundle="${local}" key="local.possibilityModerator" var="possibilityModerator"/>
<fmt:message bundle="${local}" key="local.updateUser" var="updateUser"/>
<fmt:message bundle="${local}" key="local.deleteUser" var="deleteUser"/>
<fmt:message bundle="${local}" key="local.lookAllPosts" var="lookAllPosts"/>
<fmt:message bundle="${local}" key="local.setting" var="setting"/>
<fmt:message bundle="${local}" key="local.exitUser" var="exitUser"/>
<fmt:message bundle="${local}" key="local.changeLanguage" var="changeLanguage"/>

    <div>
        <h1>"${titleUser}"</h1>
    </div>
    <div>
    <h2>"${possibilityModerator}":</h2>
        <div>
        <button onclick="location.href='/Controller?command=9'">"${updateUser}"</button>
        <br></br>
        <button onclick="location.href='/Controller?command=10'">"${deleteUser}</button>
        <br></br>
        <button onclick="location.href='/Controller?command=3'">"${lookAllPosts}"</button>
        <br></br>
        <button onclick="location.href='/Controller?command=6'">"${setting}"</button>
        <br></br>
        <button onclick="location.href='/Controller?command=5'">"${exitUser}"</button>
        <br></br>
        <button onclick="location.href='/Controller?command=1'">"${changeLanguage}"</button>
        <br></br>
        </div>
    </div>
</body>
</html>