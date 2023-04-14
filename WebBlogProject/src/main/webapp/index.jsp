<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Блог</title>
    <script src="js/jquery-3.4.0.min.js"></script>
    <script type="text/javascript" src="js/script.js"></script>
</head>
<body>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="local"/>
<fmt:message bundle="${local}" key="local.authorizeUser" var="authorizeUser"/>
<fmt:message bundle="${local}" key="local.lookAllPosts" var="lookAllPosts"/>
<fmt:message bundle="${local}" key="local.changeLanguage" var="changeLanguage"/>
<fmt:message bundle="${local}" key="local.titleUser" var="titleUser"/>
<fmt:message bundle="${local}" key="local.unknown" var="unknown"/>

    <div>
        <h1>"${titleUser}"</h1>
    </div>
    <div>
        <h2>"${unknown}":</h2>
            <div>
                <button onclick="location.href='/web/Controller?command=change_language'">"${changeLanguage}"</button>
                <br></br>
                <button onclick="location.href='/web/Controller?command=authorize_user'">"${authorizeUser}"</button>
                <br></br>
                <button onclick="location.href='/web/Controller?command=all_posts'" >"${lookAllPosts}"</button>
                <br></br>
            </div>
    </div>
</body>
</html>