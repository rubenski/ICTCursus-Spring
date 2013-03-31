<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:if test="${!userProfile.emailEditable}">
    <c:set var="disabled" value="disabled"/>
</c:if>

<form:form method="post" modelAttribute="userProfile">

    <form:hidden path="companyId"/>

    <div class="default-block">
        <form:label path="email"><spring:message code="user.genericlabel.email"/></form:label>
        <form:input path="email" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60" disabled="true"/>
        <form:errors path="email" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="firstName"><spring:message code="user.genericlabel.firstname"/></form:label>
        <form:input path="firstName" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60"/>
        <form:errors path="firstName" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="lastName"><spring:message code="user.genericlabel.lastname"/></form:label>
        <form:input path="lastName" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60"/>
        <form:errors path="lastName" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="phone"><spring:message code="user.genericlabel.phone"/></form:label>
        <form:input path="phone" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60"/>
        <form:errors path="phone" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="password1"><spring:message code="user.genericlabel.password"/></form:label>
        <form:password path="password1" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60"/>
        <form:errors path="password1" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="password2"><spring:message code="user.genericlabel.password.repeat"/></form:label>
        <form:password path="password2" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60"/>
        <form:errors path="password2" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <input type="submit" class="submitbutton" value="Verzenden"/>
    </div>

</form:form>