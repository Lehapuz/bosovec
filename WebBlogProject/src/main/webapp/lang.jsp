<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="local"/>
<fmt:message bundle="${local}" key="local.button.name.ru" var="ru_button"/>
<fmt:message bundle="${local}" key="local.button.name.en" var="en_button"/>
<form action="Controller?command=set_language" method="post"/>
<input type="hidden" name="local" value="ru"/> <input type="submit" value="${ru_button}"/>
</form>
<form action="Controller?command=set_language" method="post"/>
<input type="hidden" name="local" value="en"/> <input type="submit" value="${en_button}"/>
</form>
<br></br>






