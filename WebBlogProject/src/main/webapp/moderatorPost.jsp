<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>post</title>
    <script src="js/jquery-3.4.0.min.js"></script>
    <script type="text/javascript" src="js/script.js"></script>
</head>
<body>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="local"/>
<fmt:message bundle="${local}" key="local.lookPost" var="lookPost"/>
<fmt:message bundle="${local}" key="local.setPostStatus" var="setPostStatus"/>
<fmt:message bundle="${local}" key="local.lookComments" var="lookComments"/>
<fmt:message bundle="${local}" key="local.nothingComments" var="nothingComments"/>

<h1>"${lookPost}"</h1>
<ul>
<td>
       <p>"${text}"</p>
</td>
</ul>

<h3>"${setPostStatus}"</h3>
<form action="Controller?command=action_post_id" method="post">
<br>
<select name="status">
    <option value="OK">Approve post</option>
    <option value="NO">Reject post</option>
</select>
<br>
<br>
<input type="submit" value="Save"/>
</form>

<br>
<br>

<p>"${setStatus}"</p>

      <br>
      <br>
      <h3>Просмотр комментариев к посту</h3>
      <c:choose>
      <c:when test="${postCommentSize==0}">
      <p>"${nothingComments}"</p>
      </c:when>
      <c:when test="${postCommentSize!=0}">
      <data>
      <table id="content">
      <c:forEach var="postComment" items="${postComments}">
      <tr>
      <td>
      <c:out value="${postComment.text}"/>
      </td>
      <td>
      <c:out value=": from user - "/>
      </td>
      <td>
      <c:out value="${postComment.user.name}"/>
      </td>
      </tr>
      </c:forEach>
      </table>
      </data>
      <br></br>
      </c:when>
      </c:choose>
      <a href="/web/listModeratorPost.jsp">Back</a>
</body>
</html>



