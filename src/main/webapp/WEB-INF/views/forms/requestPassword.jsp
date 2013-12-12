<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h1>Wachtwoord vergeten?</h1>

<p>Voer hieronder uw emailadres in. U ontvangt dan een email waarmee u uw wachtwoord kunt wijzigen.</p>
<c:if test="${requestScope.error == 'true'}">
    <span class="error"><spring:message code="login.form.error"/></span>
</c:if>

<form:form method="post" commandName="forgotPassword">
    <div id="data-entry-form">
        <div class="form-entry">
            <form:label path="email"><spring:message code="user.genericlabel.email"/></form:label>
            <form:input path="email" cssClass="form-input" cssErrorClass="form-input-error"/>
            <input type="submit" value="<spring:message code="form.send" /> "/>
            <form:errors path="email" cssClass="form-error"/>
        </div>
    </div>
</form:form>
