<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1><spring:message code="account.activation.header"/></h1>


<c:choose>
    <c:when test="${accountForEmailExists == 1}">
        <spring:message code="account.already.exists"/> <b>${userProfile.email}</b>. <spring:message code="acount.already.exists.rest"/>
    </c:when>
    <c:when test="${activationSucceeded == 1}">
        <spring:message code="account.activation.success"/>
    </c:when>
    <c:otherwise>
        <spring:message code="account.activation.mainmessage"/>
        <jsp:include page="userprofile.jsp"/>
    </c:otherwise>
</c:choose>
