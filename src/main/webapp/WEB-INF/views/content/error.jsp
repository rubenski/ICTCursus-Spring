<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>${title}</h1>
<p>
    ${message}
</p>
<c:if test="${showStack}">
    <b>${errorMessage}</b>
    <br><br>
    ${stack}
</c:if>