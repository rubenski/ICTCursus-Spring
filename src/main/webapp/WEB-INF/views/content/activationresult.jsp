<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h1><spring:message code="account.activation.header"/></h1>
<c:if test="${userProfile.enabled}">
    <spring:message code="activation.success" arguments="${userProfile.email}"/>
</c:if>