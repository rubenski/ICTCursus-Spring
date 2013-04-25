<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="default-block">
    <form:label path="firstName"><spring:message code="user.genericlabel.firstname"/></form:label>
    <form:input path="firstName" cssClass="form-input" cssErrorClass="form-input-error" size="50"/>
    <form:errors path="firstName" cssClass="form-error"/>
</div>
<div class="default-block">
    <form:label path="preposition"><spring:message code="user.genericlabel.preposition"/></form:label>
    <form:input path="preposition" cssClass="form-input" cssErrorClass="form-input-error" size="50"/>
    <form:errors path="preposition" cssClass="form-error"/>
</div>
<div class="default-block">
    <form:label path="lastName"><spring:message code="user.genericlabel.lastname"/></form:label>
    <form:input path="lastName" cssClass="form-input" cssErrorClass="form-input-error" size="50"/>
    <form:errors path="lastName" cssClass="form-error"/>
</div>
<div class="default-block">
    <form:label path="email"><spring:message code="user.genericlabel.email"/></form:label>
    <form:input path="email" cssClass="form-input" cssErrorClass="form-input-error" size="50"/>
    <form:errors path="email" cssClass="form-error"/>
</div>

<div class="default-block">
    <form:label path="phone"><spring:message code="user.genericlabel.phone"/></form:label>
    <form:input path="phone" cssClass="form-input" cssErrorClass="form-input-error" size="50"/>
    <form:errors path="phone" cssClass="form-error"/>
</div>


<div class="default-block">
    <form:label path="message"><spring:message code="request.account.optionalmessage"/></form:label>
    <form:textarea path="message" cssClass="form-input" cssErrorClass="form-input-error" cols="52" rows="5"/>
    <form:errors path="message" cssClass="form-error"/>
</div>