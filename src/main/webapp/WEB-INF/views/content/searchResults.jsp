<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

U heeft gezocht op: ${terms}
<br><br>
Het resultaat:
<br><br>

<jsp:include page="courselisting.jsp">
    <jsp:param name="courses" value="${searchResult}"/>
</jsp:include>