<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h1><spring:message code="generic.userMessage.login"/> Wordt dit gebruikt?</h1>

<form:form method="post" commandName="userProfile">

    <form:hidden path="id"/>
    <div class="form-entry">
        <form:label path="email">Email</form:label>
        <form:input path="email" cssClass="form-input" cssErrorClass="form-input-error"/>
        <form:errors path="email" cssClass="form-error"/>
    </div>
    <div class="form-entry">
        <form:label path="password">Wachtwoord</form:label>
        <form:input path="password" cssClass="form-input" cssErrorClass="form-input-error"/>
        <form:errors path="password" cssClass="form-error"/>
    </div>
    <div class="form-entry">
        <input type="submit" value="Verzenden"/>
    </div>

</form:form>


