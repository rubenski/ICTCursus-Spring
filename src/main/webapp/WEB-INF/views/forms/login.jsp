<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h1><spring:message code="generic.userMessage.login"/></h1>
<div class="whitebox">
    <form:form method="post" commandName="userProfile">
        <div id="data-entry-form">
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
        </div>
    </form:form>
</div>

