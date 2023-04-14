<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<fmt:message bundle="${local}" key="local.vote" var="vote"/>
<fmt:message bundle="${local}" key="local.addNewComment" var="addNewComment"/>
<fmt:message bundle="${local}" key="local.lookComments" var="lookComments"/>
<fmt:message bundle="${local}" key="local.nothingComments" var="nothingComments"/>

<h1>"${lookPost}"</h1>
<ul>
    <p>"${text}"</p>
</ul>
      <form action="Controller?command=25" method="post">
      <label>"${vote}"</label>
      <br>
      <select name="value">
      <option value="1">Like</option>
      <option value="-1">Not like</option>
      </select>
      <br>
      <br>
      <input type="submit" value="Save"/>
      </form>
      <h3>"${addNewComment}"</h3>
      <form action="Controller?command=action_post_id" method="post">
      <br>
      <label>TextComment</label>
      <br>
      <input name="textComment" type="string"/>
      <br>
      <br>
      <input type="submit" value="Save"/>
      </form>
      <p>"${addComment}"</p>
      <br>
      <br>
      <h3>"${lookComments}"</h3>
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
      <a href="/web/listUserPost.jsp">Back</a>
</body>
</html>



