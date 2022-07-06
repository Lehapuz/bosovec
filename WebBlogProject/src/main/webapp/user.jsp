<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Личный кабинет</title>
</head>
<body>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="local"/>
<fmt:message bundle="${local}" key="local.titleUser" var="titleUser"/>
<fmt:message bundle="${local}" key="local.possibility" var="possibility"/>
<fmt:message bundle="${local}" key="local.updateUser" var="updateUser"/>
<fmt:message bundle="${local}" key="local.deleteUser" var="deleteUser"/>
<fmt:message bundle="${local}" key="local.lookAllPosts" var="lookAllPosts"/>
<fmt:message bundle="${local}" key="local.lookMyPosts" var="lookMyPosts"/>
<fmt:message bundle="${local}" key="local.lookMyComments" var="lookMyComments"/>
<fmt:message bundle="${local}" key="local.exitUser" var="exitUser"/>
<fmt:message bundle="${local}" key="local.changeLanguage" var="changeLanguage"/>

    <div>
        <h1>"${titleUser}"</h1>
    </div>
    <div>
    <h2>"${possibility}":</h2>
        <div>
        <button onclick="location.href='updateUser.jsp'">"${updateUser}"</button>
        <br></br>
        <button onclick="location.href='deleteUser.jsp'">"${deleteUser}"</button>
        <br></br>
        <button onclick="location.href='/Controller?command=3'">"${lookAllPosts}"</button>
        <br></br>
        <button onclick="location.href='/Controller?command=4'">"${lookMyPosts}"</button>
        <br></br>
        <button onclick="location.href='/Controller?command=8'">"${lookMyComments}"</button>
        <br></br>
        <button onclick="location.href='/Controller?command=5'">"${exitUser}"</button>
        <br></br>
        <button onclick="location.href='/Controller?command=1'">"${changeLanguage}"</button>
        <br></br>
        </div>
    </div>
</body>
</html>