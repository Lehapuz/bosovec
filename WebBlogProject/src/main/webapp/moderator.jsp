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
        <button onclick="location.href='/web/Controller?command=update_user'">"${updateUser}"</button>
        <br></br>
        <button onclick="location.href='/web/Controller?command=delete_user'">"${deleteUser}</button>
        <br></br>
        <button onclick="location.href='/web/Controller?command=all_posts'">"${lookAllPosts}"</button>
        <br></br>
        <button onclick="location.href='/web/Controller?command=get_setting'">"${setting}"</button>
        <br></br>
        <button onclick="location.href='/web/Controller?command=exit_user_prepare'">"${exitUser}"</button>
        <br></br>
        <button onclick="location.href='/web/Controller?command=change_language'">"${changeLanguage}"</button>
        <br></br>
        </div>
    </div>
</body>
</html>