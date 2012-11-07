<%@ page import="java.util.Enumeration" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${requestScope.error == 'true'}">
    <span class="error"><spring:message code="login.form.error"/></span>
</c:if>

<form:form method="post" commandName="forgotPassword">
    <div id="data-entry-form">
        <div class="form-entry">
            <form:label path="email"><spring:message code="login.form.label.email"/></form:label>
            <form:input path="email" cssClass="form-input" cssErrorClass="form-input-error"/>
            <form:errors path="email" cssClass="form-error" />
        </div>
        <div class="form-entry">
            <input type="submit" value="<spring:message code="form.send" /> "/>
        </div>
    </div>
</form:form>
